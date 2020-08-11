package com.biang.www.dao.impl;

import com.biang.www.dao.IDemandUserDao;
import com.biang.www.po.Demand;
import com.biang.www.util.JDBCUtils;
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
        System.out.println("insert("+userId+","+demandId+")");
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="INSERT INTO demand_user VALUES(?,?,default)";
            return queryRunner.update(sql, userId, demandId) > 0;
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
    public boolean queryByDemandIdAndUserId(int userId, String demandId) throws SQLException {
        System.out.println("queryByDemandIdAndUserId("+userId+","+demandId+")");
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="SELECT * from demand_user where userId=? AND demandId=?";
            return (queryRunner.query(sql,new ArrayHandler(),userId,demandId).length!=0);
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
    public List<Demand> queryByUserId(int userId) throws SQLException {
        System.out.println("queryByUserId("+userId+")");
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="SELECT * FROM demand WHERE demandId=(SELECT demandId from demand_user where userId=? )";
            return queryRunner.query(sql,new BeanListHandler<>(Demand.class),userId);
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

    @Override
    public List<Object[]> queryDemandIdAndConditionOfApplyByUserId(int userId) throws SQLException {
        System.out.println("queryConditionOfApplyByUserByUserId("+userId+")");
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
                dataSource.getConnection().close();
                System.out.println("clone()");
            }
        }
        return null;
    }

    @Override
    public List<Object[]> queryDemandIdAndConditionOfApplyByDemandId(int demandId) throws SQLException {
        System.out.println("queryDemandIdAndConditionOfApplyByDemandId("+demandId+")");
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
                dataSource.getConnection().close();
                System.out.println("clone()");
            }
        }
        return null;
    }

    @Override
    public boolean updateConditionOfApplyByUserIdAndDemandId(int userId, int demandId, int conditionOfApply) throws SQLException {
        System.out.println("updateConditionOfApplyByUserIdAndDemandId("+userId+","+demandId+","+conditionOfApply+")");
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
                dataSource.getConnection().close();
                System.out.println("clone()");
            }
        }
        return false;
    }
}
