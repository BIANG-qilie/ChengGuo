package com.biang.www.dao.impl;

import com.biang.www.dao.IDemandUserDao;
import com.biang.www.po.Demand;
import com.biang.www.util.JDBCUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class DemandUserDaoImpl implements IDemandUserDao {
    @Override
    public boolean insert(int userId, String demandId) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="INSERT INTO demand_user VALUES(?,?,default)";
            return queryRunner.update(sql, demandId, userId) > 0;
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
    public Object[] queryByDemandIdAndUserId(int demandId, int userId) throws Exception {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="SELECT * from demand_user where demandId=? AND userId=?  ";
            return queryRunner.query(sql,new ArrayHandler(),demandId,userId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(dataSource!=null){
                DbUtils.close(dataSource.getConnection());
            }
        }
        return null;
    }


    @Override
    public List<Demand> queryDemandByUserId(int userId) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="SELECT * FROM demand WHERE demandId=any(SELECT demandId from demand_user where userId=? )";
            return queryRunner.query(sql,new BeanListHandler<>(Demand.class),userId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(dataSource!=null){
                DbUtils.close(dataSource.getConnection());
            }
        }
        return null;
    }

    @Override
    public List<Object[]> queryByUserId(int userId) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="SELECT demandId,userId,conditionOfApply from demand_user where userId=? ";
            return queryRunner.query(sql,new ArrayListHandler(),userId);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(dataSource!=null){
                DbUtils.close(dataSource.getConnection());
            }
        }
        return null;
    }

    @Override
    public List<Object[]> queryByDemandId(int demandId) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="SELECT demandId,userId,conditionOfApply from demand_user where demandId=? ";
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

    @Override
    public boolean updateConditionOfApplyByUserIdAndDemandId(int userId, int demandId, int conditionOfApply) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="UPDATE demand_user SET conditionOfApply = ? WHERE userId=? AND demandId=?";
            return queryRunner.update(sql, conditionOfApply,userId,demandId)>0;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(dataSource!=null){
                DbUtils.close(dataSource.getConnection());
            }
        }
        return false;
    }
}
