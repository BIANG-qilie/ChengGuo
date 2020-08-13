package com.biang.www.dao.impl;

import com.biang.www.dao.IAnnexDao;
import com.biang.www.po.User;
import com.biang.www.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class AnnexDaoImpl implements IAnnexDao {
    @Override
    public boolean insert(int demandId, String annexName) throws SQLException {
        System.out.println("insert("+demandId+","+annexName+")");
        DataSource dataSource = null;
        try {
            dataSource= JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="INSERT INTO annex VALUES(?,?)";
            return queryRunner.update(sql, demandId,annexName) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(dataSource!=null){
                dataSource.getConnection().close();
                System.out.println("clone()");
            }
        }
        return false;
    }

    @Override
    public List<Object[]> queryByDemandId(int demandId) throws SQLException {
        System.out.println("queryByDemandId("+demandId+")");
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from annex where demandId=?";
            return queryRunner.query(sql,new ArrayListHandler(),demandId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(dataSource!=null){
                dataSource.getConnection().close();
                System.out.println("clone()");
            }
        }
        return null;
    }
}
