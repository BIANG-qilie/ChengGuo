package com.biang.www.dao.impl;

import com.biang.www.dao.IAnnexDao;
import com.biang.www.po.User;
import com.biang.www.util.JDBCUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author BIANG
 */
public class AnnexDaoImpl implements IAnnexDao {
    @Override
    public boolean insert(int demandId, String annexName) throws SQLException {
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
                DbUtils.close(dataSource.getConnection());
            }
        }
        return false;
    }

    @Override
    public List<Object[]> queryByDemandId(int demandId) throws SQLException {
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
                DbUtils.close(dataSource.getConnection());
            }
        }
        return null;
    }
}
