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

        DataSource dbcp;
        Properties properties = new Properties();
        InputStream input = JDBCUtils.class.getClassLoader().getResourceAsStream("dbcpConfig.properties");
        properties.load( input );
        dbcp = BasicDataSourceFactory.createDataSource(properties) ;
        return dbcp;
    }

}
