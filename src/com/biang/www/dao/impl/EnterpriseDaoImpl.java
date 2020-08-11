package com.biang.www.dao.impl;

import com.biang.www.dao.IEnterpriseDao;
import com.biang.www.po.Demand;
import com.biang.www.po.Enterprise;
import com.biang.www.po.User;
import com.biang.www.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class EnterpriseDaoImpl implements IEnterpriseDao {
    @Override
    public Enterprise queryByEnterpriseId(int enterpriseId) throws SQLException {
        System.out.println("queryByEnterpriseId("+enterpriseId+")");
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from enterprise where enterpriseId=?";
            return queryRunner.query(sql,new BeanHandler<>(Enterprise.class),enterpriseId);
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
    public Enterprise queryByUserId(int userId) throws SQLException {
        System.out.println("queryByUserId("+userId+")");
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from enterprise where userId=?";
            return queryRunner.query(sql,new BeanHandler<>(Enterprise.class),userId);
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
    public boolean insert(Enterprise enterprise) throws SQLException {
        System.out.println("insert("+enterprise.getEnterpriseName()+","+enterprise.getInformation()+","+enterprise.getContactPerson()+","+enterprise.getUserId()+")");
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="INSERT INTO enterprise VALUES(default,?,?,?,?,default)";
            return queryRunner.update(sql, enterprise.getEnterpriseName(),enterprise.getInformation(),enterprise.getContactPerson(),enterprise.getUserId()) > 0;
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
    public List<Enterprise> queryAllEnterprise() throws SQLException {
        System.out.println("queryAllEnterprise()");
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from enterprise";
            return queryRunner.query(sql,new BeanListHandler<>(Enterprise.class));
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
    public boolean updateConditionsOfCertification(Enterprise enterprise, int conditionsOfCertification) throws SQLException {
        System.out.println("updateConditionsOfCertification("+conditionsOfCertification+","+enterprise.getEnterpriseId()+")");
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="UPDATE enterprise SET conditionsOfCertification = ? WHERE enterpriseId=?";
            return queryRunner.update(sql, conditionsOfCertification,enterprise.getEnterpriseId())>0;
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
