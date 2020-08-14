package com.biang.www.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;


import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author BIANG
 */
public class JDBCUtils  {
    public static DataSource getDataSourceWIthDBCPByProperties() throws Exception{
        DataSource dataSource;
        Properties properties = new Properties();
        InputStream input = JDBCUtils.class.getClassLoader().getResourceAsStream("dbcpConfig.properties");
        properties.load( input );
        dataSource = BasicDataSourceFactory.createDataSource(properties) ;
        /*BasicDataSource dataSource=new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/cheng_guo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("BIANG0216");
        dataSource.setInitialSize(100);
        dataSource.setMaxIdle(30);
        dataSource.setMinIdle(2);
        dataSource.setMaxWaitMillis(5000);
        dataSource.setDefaultAutoCommit(true);
        dataSource.setRemoveAbandonedTimeout(30);
        dataSource.setLogAbandoned(true);*/
        return dataSource;
    }

}
