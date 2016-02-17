package com.wk.p3.greenmall.common.cache;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.wk.p3.greenmall.modules.sys.entity.User;

/**
 * Created by cc on 15-12-3.
 *
 * 注释介绍
 *      @Cacheable 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
 *      @CachePut 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用
 *      @CachEvict 主要针对方法配置，能够根据一定的条件对缓存进行清空
 */
@Service
public class CacheServiceForTest {
    private final Logger logger = LoggerFactory.getLogger(CacheServiceForTest.class);

    @Cacheable(value="test2",key = "#user.name",condition = "#user.name.length()==10")
    public List<User> testGetUserByNameAndId(User user){
        List lists = new ArrayList();
        lists.add(user);
        logger.info("查询userlist============="+user);
        return lists;
    }
    @Cacheable(value="test2",key = "#u.getId()")
    public User getUserById(User u){
        logger.info("==========queryUser=========="+u);

        return u;
    }
    @Cacheable(value="test2",key = "#u.getName()")
    public User getUserByName(User u){
        logger.info("==========queryUser=========="+u);

        return u;
    }
    /**
     * @Cacheable(value = "accountCache")
     * 当调用这个方法的时候，会从一个名叫 accountCache 的缓存中查询，
     * 如果没有，则执行实际的方法（即查询数据库），并将执行的结果存入缓存中，
     * 否则返回缓存中的对象。这里的缓存中的 key 就是参数 accountName，value 就是 Account 对象。
     * “accountCache”缓存是在 spring*.xml 中定义的名称。我们还需要一个 spring 的配置文件来支持基于注释的缓存
     */
//    @Cacheable(value = "redis")       //测试redis缓存
    //@Cacheable(value = "userCache")   //测试ehcache缓存
    @Cacheable(value = "accountCache")  //测试ConcurrentMapCacheFactoryBean缓存
    public Account getAccountByName(String a) {
        // 方法内部实现不考虑缓存逻辑，直接实现业务
        logger.info("real querying account88888888888888... {}", a);
        Optional<Account> accountOptional = getFromDB(a);
        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name : [%s]", a));
        }
        return accountOptional.get();
    }

    /**
     * condition=”#accountName.length() <=4”，这里使用了 SpEL 表达式访问了参数 accountName 对象的 length() 方法，
     * 条件表达式返回一个布尔值，true/false，当条件为 true，则进行缓存操作，否则直接调用方法执行的返回结果
     */
    @Cacheable(value="accountCache",condition="#accountName.length() <= 4")// 缓存名叫 accountCache
    public Account getAccountByNameLength(String accountName) {
        // 方法内部实现不考虑缓存逻辑，直接实现业务
        Optional<Account> accountOptional = getFromDB(accountName);
        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name : [%s]", accountName));
        }
        return accountOptional.get();
    }

    /**
     * 这里我们需要根据账号名、密码对账号对象进行缓存，而第三个参数“是否发送日志”对缓存没有任何影响。
     * 所以，我们可以利用 SpEL 表达式对缓存 key 进行设计
     * key 属性中引用了方法的两个参数 accountName 和 password，而 sendLog 属性没有考虑，因为其对缓存没有影响
     */
    @Cacheable(value="accountCache",key="#accountName.concat(#password)")
    public Account getAccountByNamePassword(String accountName,String password,boolean sendLog) {
        // 方法内部实现不考虑缓存逻辑，直接实现业务
        Optional<Account> accountOptional = getFromDB(accountName,password);
        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name,password : [%s][%s]", accountName,password));
        }
        return accountOptional.get();
    }

    @CacheEvict(value="accountCache",key="#account.getName()")
    public void updateAccount(Account account) {
        updateDB(account);
    }

    @CacheEvict(value="accountCache",allEntries=true)
    public void reload() {
        logger.info("清空缓存");
    }

    /**
     * @CacheEvict 注释有一个属性 beforeInvocation，缺省为 false，即缺省情况下，
     * 都是在实际的方法执行完成后，才对缓存进行清空操作。期间如果执行方法出现异常，则会导致缓存清空不被执行
     */
    @CacheEvict(value="accountCache",allEntries=true)
    public void reloadException() {
        throw new RuntimeException();
    }

    /**
     * @CachePut 注释，这个注释可以确保方法被执行，同时方法的返回值也被记录到缓存中
     */
    @CachePut(value="accountCache",key="#account.getName()")
    public void updateAccountByCachePut(Account account) {
        updateDB(account);
    }

    /**
     * 上面介绍过 spring cache 的原理，即它是基于动态生成的 proxy 代理机制来对方法的调用进行切面，这里关键点是对象的引用问题.
     * 如果对象的方法是内部调用（即 this 引用）而不是外部引用，则会导致 proxy 失效，
     * 那么我们的切面就失效，也就是说上面定义的各种注释包括 @Cacheable、@CachePut 和 @CacheEvict 都会失效
     * 要避免这个问题，就是要避免对缓存方法的内部调用，或者避免使用基于 proxy 的 AOP 模式，可以使用基于 aspectJ 的 AOP 模式来解决这个问题
     *
     * 另外：和内部调用问题类似，非 public 方法如果想实现基于注释的缓存，必须采用基于 AspectJ 的 AOP 机制
     */
    public Account getAccountByName2(String accountName) {
        return this.getAccountByName(accountName);
    }


    private Optional<Account> getFromDB(String accountName,String password) {
        logger.info("real querying db and password {}", accountName, password);
        //Todo query data from database
        return Optional.fromNullable(new Account(accountName,password));
    }
    private void updateDB(Account account) {
        logger.info("real update db...{}", account.getName());
    }
    private Optional<Account> getFromDB(String accountName) {
        logger.info("real querying db... {}", accountName);
        //Todo query data from database
        return Optional.fromNullable(new Account(accountName));
    }
}
