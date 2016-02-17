package com.wk.p3.greenmall.common.dbutil;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  Created by cc on 15-12-2.
 *      测试dbutils各种类型的处理器
 *
 * ResultSetHandler接口的实现类
 *    ArrayHandler：
 *           把结果集中的第一行数据转成对象数组。
 *    ArrayListHandler：
 *           把结果集中的每一行数据都转成一个数组，再存放到List中。
 *    BeanHandler：
 *           将结果集中的第一行数据封装到一个对应的JavaBean实例中。
 *    BeanListHandler：
 *           将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
 *    ColumnListHandler：
 *           将结果集中某一列的数据存放到List中。
 *    KeyedHandler(name)：
 *           将结果集中的每一行数据都封装到一个Map里，再把这些map再存到一个map里，其key为指定的key。
 *    MapHandler：
 *           将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值。
 *    MapListHandler：
 *           将结果集中的每一行数据都封装到一个Map里，然后再存放到List
 */
@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration({"/spring-context.xml"}) //指定Spring的配置文件 /为classpath下
@Transactional //对所有的测试方法都使用事务，并在测试完成后回滚事务
public class ResultSetHandlerTest extends Table{

    @Before
    public void before() throws SQLException {
        super.before();
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
    public void testArrayHandler() throws SQLException {
        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "select * from users";
        Object result[] =  qr.query(sql, new ArrayHandler());
        System.out.println(Arrays.asList(result));  //list  toString()
    }

    @Test
    public void testArrayListHandler() throws SQLException {
        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "select * from users";
        List<Object[]> list = (List) qr.query(sql, new ArrayListHandler());
        for (Object[] o : list) {
            System.out.println(Arrays.asList(o));
        }
    }

    @Test
    public void testColumnListHandler() throws SQLException {
        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "select * from users";
        List list = (List) qr.query(sql, new ColumnListHandler("id"));
        System.out.println(list);
    }

    @Test
    public void testKeyedHandler() throws Exception {
        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "select * from users";

        Map<Integer, Map> map = (Map) qr.query(sql, new KeyedHandler("id"));
        for (Map.Entry<Integer, Map> me : map.entrySet()) {
            int id = me.getKey();
            Map<String, Object> innermap = me.getValue();
            for (Map.Entry<String, Object> innerme : innermap.entrySet()) {
                String columnName = innerme.getKey();
                Object value = innerme.getValue();
                System.out.println(columnName + "=" + value);
            }
            System.out.println(""+map.toString());
        }
    }

    @Test
    public void testMapHandler() throws SQLException {

        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "select * from users";

        Map<String, Object> map = (Map) qr.query(sql, new MapHandler());
        for (Map.Entry<String, Object> me : map.entrySet()) {
            System.out.println(me.getKey() + "=" + me.getValue());
        }
    }


    @Test
    public void testMapListHandler() throws SQLException {
        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "select * from users";
        List<Map> list = (List) qr.query(sql, new MapListHandler());
        for (Map<String, Object> map : list) {
            for (Map.Entry<String, Object> me : map.entrySet()) {
                System.out.println(me.getKey() + "=" + me.getValue());
            }
        }
    }

    @Test
    public void testScalarHandler() throws SQLException {
        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "select count(*) from users";  //[13]  list[13]
        int count = ((Long) qr.query(sql, new ScalarHandler(1))).intValue();
        System.out.println(count);
    }
}
