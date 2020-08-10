package com.biang.www.util;

import org.apache.commons.dbcp2.BasicDataSourceFactory;


import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author BIANG
 */
public class JDBCUtils  {
    public static DataSource getDataSourceWIthDBCPByProperties() throws Exception{
        DataSource dbcp = null ;
        Properties props = new Properties();
        InputStream input = new JDBCUtils().getClass().getClassLoader().getResourceAsStream("dbcpconfig.properties");
        props.load( input );
        dbcp = BasicDataSourceFactory.createDataSource(props) ;
        return dbcp;
    }
}
