package com.wk.p3.greenmall.common.dbutil;//http://www.cnblogs.com/xdp-gacl/p/4007225.html


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * DBUtil测试类
 *
 * QueryRunner类的主要方法
 *
 *　　public Object query(Connection conn, String sql, Object[] params, ResultSetHandler rsh) throws SQLException：
 *           执行一个查询操作，在这个查询中，对象数组中的每个元素值被用来作为查询语句的置换参数。该方法会自行处理 PreparedStatement 和 ResultSet 的创建和关闭。
 *　　public Object query(String sql, Object[] params, ResultSetHandler rsh) throws SQLException:　
 *           几乎与第一种方法一样；唯一的不同在于它不将数据库连接提供给方法，并且它是从提供给构造方法的数据源(DataSource) 或使用的setDataSource 方法中重新获得 Connection。
 *　　public Object query(Connection conn, String sql, ResultSetHandler rsh) throws SQLException :
 *           执行一个不需要置换参数的查询操作。
 *　　public int update(Connection conn, String sql, Object[] params) throws SQLException:
 *           用来执行一个更新（插入、更新或删除）操作。
 *　　public int update(Connection conn, String sql) throws SQLException：
 *           用来执行一个不需要置换参数的更新操作。
 */

@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({"/spring-context.xml"}) //指定Spring的配置文件 /为classpath下
@Transactional //对所有的测试方法都使用事务，并在测试完成后回滚事务
public class QueryRunnerCRUDTest extends Table{

    @Test
    //@Transactional  //使用该注释会使用事务，而且在测试完成之后会回滚事务，也就是说在该方法中做出的一切操作都不会对数据库中的数据产生任何影响
    //@Rollback(false) //这里设置为false，就让事务不回滚
    public void add() throws SQLException {
        Assert.assertNotNull(dataSource);
        //将数据源传递给QueryRunner，QueryRunner内部通过数据源获取数据库连接
        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "insert into users(name,password,email,birthday) values(?,?,?,?)";
        Object params1[] = {"孤傲苍狼","123", "gacl@sina.com", new Date()};
        Object params2[] = {"白虎神皇","123", "gacl@sina.com", "1988-05-07"};
        qr.update(sql, params1);
        qr.update(sql, params2);
    }

    @Test
    public void delete() throws SQLException {

        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "delete from users where id=?";
        qr.update(sql, 1);

    }

    @Test
    public void update() throws SQLException {
        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "update users set name=? where id=?";
        Object params[] = { "ddd", 5};
        qr.update(sql, params);
    }

    @Test
    public void find() throws SQLException {
        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "select * from users where id=?";
        Object params[] = {2};
        User user = (User) qr.query(sql, params, new BeanHandler(User.class));
        System.out.println(user==null?"":user.getBirthday());
    }

    @Test
    public void getAll() throws SQLException {
        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "select * from users";
        List list = (List) qr.query(sql, new BeanListHandler(User.class));
        System.out.println(list.size());
    }

    @Test
    public void testBatch() throws SQLException {
        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "insert into users(name,password,email,birthday) values(?,?,?,?)";
        Object params[][] = new Object[10][];
        for (int i = 0; i < 10; i++) {
            params[i] = new Object[] { "aa" + i, "123", "aa@sina.com",
                    new Date() };
        }
        qr.batch(sql, params);
    }

    //用dbutils完成大数据（不建议用）
    /***************************************************************************
     create table testclob
     (
     id int primary key auto_increment,
     resume text
     );
     **************************************************************************/
//    @Test
//    public void testclob() throws SQLException, IOException{
//        QueryRunner runner = new QueryRunner(dataSource);
//        String sql = "insert into testclob(resume) values(?)";  //clob
//        //这种方式获取的路径，其中的空格会被使用“%20”代替
//        String path  = QueryRunnerCRUDTest.class.getClassLoader().getResource("data.txt").getPath();
//        //将“%20”替换回空格
//        path = path.replaceAll("%20", " ");
//        FileReader in = new FileReader(path);
//        char[] buffer = new char[(int) new File(path).length()];
//        in.read(buffer);
//        SerialClob clob = new SerialClob(buffer);
//        Object params[] = {clob};
//        runner.update(sql, params);
//    }
}



