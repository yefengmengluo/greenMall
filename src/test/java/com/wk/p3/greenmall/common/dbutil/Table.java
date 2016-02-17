package com.wk.p3.greenmall.common.dbutil;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.After;
import org.junit.Before;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by cc on 15-12-2.
 */

public class Table {
    //	@Autowired
    //	private ApplicationContext appplicationContext; //自动注入applicationContext，这样就可以使用appli*.getBean("beanName")
    @Resource//会自动注入 default by type
    public DruidDataSource dataSource;
    /*
     *测试表
     create table users(
         id int primary key auto_increment,
         name varchar(40),
         password varchar(40),
         email varchar(60),
         birthday date
     );
     */

    @Before
    public void before() throws SQLException {
        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "     create table users(\n" +
                "               id int primary key auto_increment,\n" +
                "               name varchar(40),\n" +
                "               password varchar(40),\n" +
                "               email varchar(60),\n" +
                "               birthday date\n" +
                "     );";
        qr.update(sql);
    }

    @After
    public void after() throws SQLException {
        QueryRunner qr = new QueryRunner(dataSource);
        qr.update("drop table users;");
    }


}
