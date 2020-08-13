package com.biang.www.dao.impl;

import com.biang.www.dao.IDemandDao;
import com.biang.www.po.Demand;
import com.biang.www.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

public class DemandDaompl implements IDemandDao {
    @Override
    public List<Demand> queryAllDemand() throws SQLException {
        System.out.println("queryAllDemand()");
        DataSource dataSource = null;
        try {
            dataSource = JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql = "select * from demand";
            return queryRunner.query(sql, new BeanListHandler<>(Demand.class));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dataSource.getConnection().close();
            System.out.println("clone()");
        }
        return null;
    }

    @Override
    public List<Demand> queryByEnterpriseId(int enterpriseId) throws SQLException {
        System.out.println("queryByEnterpriseId("+enterpriseId+")");
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
                dataSource.getConnection().close();
                System.out.println("clone()");
            }
        }
        return null;
    }

    @Override
    public List<Demand> queryByConditionsOfCertification(int conditionsOfCertification) throws SQLException {
        System.out.println("queryByConditionsOfCertification("+conditionsOfCertification+")");
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from demand WHERE conditionOfCertification=?";
            return queryRunner.query(sql,new BeanListHandler<>(Demand.class),conditionsOfCertification);
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
    public Demand queryByDemandid(int demandId) throws SQLException {
        System.out.println("queryByDemandid("+demandId+")");
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
                dataSource.getConnection().close();
                System.out.println("clone()");
            }
        }
        return null;
    }

    @Override
    public List<Demand> queryFromAllDemand(String queryContent) throws SQLException {
        System.out.println("queryFromAllDemand("+queryContent+")");
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
                    "OR conditionOfDemand REGEXP ? ;";
            return queryRunner.query(sql,new BeanListHandler<>(Demand.class),queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent);

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
    public List<Demand> queryFromEnterpriseId(int enterpriseId,int conditionOfCertification,String queryContent) throws SQLException {
        System.out.println("queryFromEnterpriseId("+enterpriseId+","+queryContent+")");
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
                    "OR conditionOfDemand REGEXP ? );";
            return queryRunner.query(sql,new BeanListHandler<>(Demand.class),enterpriseId,conditionOfCertification,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent);

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
    public List<Demand> queryFromPassCertificationDemand(int conditionsOfCertification,String queryContent) throws SQLException {
        System.out.println("queryFromPassCertificationDemand("+conditionsOfCertification+","+queryContent+")");
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
                    "OR conditionOfDemand REGEXP ? );";
            return queryRunner.query(sql,new BeanListHandler<>(Demand.class),conditionsOfCertification,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent,queryContent);
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
    public List<Demand> queryByEnterpriseIdOrConditionOfCertification(int enterpriseId, int conditionOfCertification) throws SQLException {
        System.out.println("queryByEnterpriseIdOrConditionOfCertification("+enterpriseId+","+conditionOfCertification+")");
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from demand WHERE enterpriseId=? OR conditionOfCertification=?";
            return queryRunner.query(sql,new BeanListHandler<>(Demand.class),enterpriseId,conditionOfCertification);
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
    public Demand insert(Demand demand) throws SQLException {
        System.out.println("insert("+demand.getTitle()+""+demand.getIntroduction()+""+demand.getSpecificContent()+""+demand.getDemandUnits()+""+demand.getBudget()+""+demand.getTimeRequirement()+""+demand.getEnterpriseId()+")");
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="INSERT INTO demand VALUES(default,?,?,?,?,?,?,?,default,default)";
            queryRunner.update(sql,demand.getTitle(),demand.getIntroduction(),demand.getSpecificContent(),demand.getDemandUnits(),demand.getBudget(),demand.getTimeRequirement(),demand.getEnterpriseId());
            sql="SELECT LAST_INSERT_ID()";
            return queryByDemandid(Integer.parseInt(String.valueOf(queryRunner.query(sql,new ArrayHandler())[0])));
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
    public List<Demand> queryByEnterpriseIdAndConditionOfCertification(int enterpriseId, int conditionOfCertification) throws SQLException {
        System.out.println("queryByEnterpriseIdAndConditionOfCertification("+enterpriseId+","+conditionOfCertification+")");
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
                dataSource.getConnection().close();
                System.out.println("clone()");
            }
        }
        return null;
    }

    @Override
    public boolean updateConditionsOfCertification(Demand demand, int conditionOfCertification) throws SQLException {
        System.out.println("insert("+demand.getTitle()+","+demand.getIntroduction()+","+demand.getSpecificContent()+","+demand.getDemandUnits()+","+demand.getBudget()+","+demand.getTimeRequirement()+","+demand.getEnterpriseId()+")");
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
                dataSource.getConnection().close();
                System.out.println("clone()");
            }
        }
        return false;
    }

    @Override
    public boolean updateConditionOfDemand(int demandId, int conditionOfDemand) throws SQLException {
        System.out.println("updateConditionOfDemand("+demandId+","+conditionOfDemand+")");
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
                dataSource.getConnection().close();
                System.out.println("clone()");
            }
        }
        return false;
    }
}
