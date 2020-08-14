package com.biang.www.dao.impl;

import com.biang.www.controller.DemandServlet;
import com.biang.www.dao.IDemandDao;
import com.biang.www.po.Demand;
import com.biang.www.util.JDBCUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class DemandDaompl implements IDemandDao {
    @Override
    public List<Demand> queryPagingDemandFromAllDemand(int pageNumber) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource = JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql = "select * from demand limit ?,?";
            return queryRunner.query(sql, new BeanListHandler<>(Demand.class),(pageNumber-1)* DemandServlet.MAX_NUMBER_OF_MESSAGES,DemandServlet.MAX_NUMBER_OF_MESSAGES);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(dataSource.getConnection());
        }
        return null;
    }

    @Override
    public List<Demand> queryByEnterpriseId(int enterpriseId) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from demand WHERE enterpriseId=?";
            return queryRunner.query(sql,new BeanListHandler<>(Demand.class),enterpriseId);
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
    public List<Demand> queryPagingDemandByConditionsOfCertification(int conditionsOfCertification, int pageNumber) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from demand WHERE conditionOfCertification=? limit ?,?";
            return queryRunner.query(sql,new BeanListHandler<>(Demand.class),conditionsOfCertification,(pageNumber-1)* DemandServlet.MAX_NUMBER_OF_MESSAGES,DemandServlet.MAX_NUMBER_OF_MESSAGES);
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
    public Demand queryByDemandId(int demandId) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from demand where demandId=?";
            return queryRunner.query(sql,new BeanHandler<>(Demand.class),demandId);
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
    public List<Demand> queryPagingDemandFromAllDemand(String queryContent, int pageNumber) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from demand WHERE \n" +
                    "demandId REGEXP ? \n" +
                    "OR title REGEXP ? \n" +
                    "OR introduction REGEXP ? \n" +
                    "OR specificContent REGEXP ? \n" +
                    "OR demandUnits REGEXP ? \n" +
                    "OR budget REGEXP ? \n" +
                    "OR timeRequirement REGEXP ? \n" +
                    "OR enterpriseId REGEXP ? \n" +
                    "OR conditionOfCertification REGEXP ? \n" +
                    "OR conditionOfDemand REGEXP ? " +
                    "limit ?,?";
            return queryRunner.query(sql,new BeanListHandler<>(Demand.class),queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,(pageNumber-1)* DemandServlet.MAX_NUMBER_OF_MESSAGES,DemandServlet.MAX_NUMBER_OF_MESSAGES);

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
    public List<Demand> queryPagingDemandFromEnterpriseId(int enterpriseId, int conditionOfCertification, String queryContent, int pageNumber) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from demand WHERE (enterpriseId=? OR conditionOfCertification=? ) AND( \n"+
                    "demandId REGEXP ? \n" +
                    "OR title REGEXP ? \n" +
                    "OR introduction REGEXP ? \n" +
                    "OR specificContent REGEXP ? \n" +
                    "OR demandUnits REGEXP ? \n" +
                    "OR budget REGEXP ? \n" +
                    "OR timeRequirement REGEXP ? \n" +
                    "OR conditionOfDemand REGEXP ? )" +
                    "limit ?,?";
            return queryRunner.query(sql,new BeanListHandler<>(Demand.class),enterpriseId,conditionOfCertification,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,(pageNumber-1)* DemandServlet.MAX_NUMBER_OF_MESSAGES,DemandServlet.MAX_NUMBER_OF_MESSAGES);

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
    public List<Demand> queryPagingDemandFromPassCertificationDemand(int conditionsOfCertification, String queryContent, int pageNumber) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from demand WHERE conditionOfCertification=? AND (" +
                    "demandId REGEXP ? \n" +
                    "OR title REGEXP ? \n" +
                    "OR introduction REGEXP ? \n" +
                    "OR specificContent REGEXP ? \n" +
                    "OR demandUnits REGEXP ? \n" +
                    "OR budget REGEXP ? \n" +
                    "OR timeRequirement REGEXP ? \n" +
                    "OR enterpriseId REGEXP ? \n" +
                    "OR conditionOfDemand REGEXP ? )" +
                    "limit ?,?";
            return queryRunner.query(sql,new BeanListHandler<>(Demand.class),conditionsOfCertification,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,(pageNumber-1)* DemandServlet.MAX_NUMBER_OF_MESSAGES,DemandServlet.MAX_NUMBER_OF_MESSAGES);
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
    public List<Demand> queryPagingDemandByEnterpriseIdOrConditionOfCertification(int enterpriseId, int conditionOfCertification, int pageNumber) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from demand WHERE enterpriseId=? OR conditionOfCertification=? limit ?,?";
            return queryRunner.query(sql,new BeanListHandler<>(Demand.class),enterpriseId,conditionOfCertification,(pageNumber-1)* DemandServlet.MAX_NUMBER_OF_MESSAGES,DemandServlet.MAX_NUMBER_OF_MESSAGES);
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
    public Demand insert(Demand demand) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="INSERT INTO demand VALUES(default,?,?,?,?,?,?,?,default,default)";
            queryRunner.update(sql,demand.getTitle(),demand.getIntroduction(),demand.getSpecificContent(),demand.getDemandUnits(),demand.getBudget(),demand.getTimeRequirement(),demand.getEnterpriseId());
            sql="SELECT LAST_INSERT_ID()";
            return queryByDemandId(Integer.parseInt(String.valueOf(queryRunner.query(sql,new ArrayHandler())[0])));
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
    public List<Demand> queryByEnterpriseIdAndConditionOfCertification(int enterpriseId, int conditionOfCertification) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from demand WHERE enterpriseId=? AND conditionOfCertification=?";
            return queryRunner.query(sql,new BeanListHandler<>(Demand.class),enterpriseId,conditionOfCertification);
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
    public boolean updateConditionsOfCertification(Demand demand, int conditionOfCertification) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="UPDATE demand SET conditionOfCertification = ? WHERE demandId=?";
            return queryRunner.update(sql, conditionOfCertification,demand.getDemandId())>0;
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
    public boolean updateConditionOfDemand(int demandId, int conditionOfDemand) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="UPDATE demand SET conditionOfDemand = ? WHERE demandId=?";
            return queryRunner.update(sql, conditionOfDemand,demandId)>0;
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
    public Object[] querySizeOfAllDemands() throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select COUNT(*) from demand";
            return queryRunner.query(sql,new ArrayHandler());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(dataSource!=null){
                DbUtils.close(dataSource.getConnection());
            }
        }
        return new Object[]{0};
    }
}
