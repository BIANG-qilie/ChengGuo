package com.biang.www.dao.impl;

import com.biang.www.dao.IDemandUserDao;
import com.biang.www.po.Demand;
import com.biang.www.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class DemandUserDaoImpl implements IDemandUserDao {
    @Override
    public boolean insert(int userId, String demandId) throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSourceWIthDBCPByProperties());
        String sql="INSERT INTO demand_user VALUES(?,?,default)";
        return queryRunner.update(sql, userId, demandId) > 0;
    }

    @Override
    public boolean queryByDemandIdAndUserId(int userId, String demandId) throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSourceWIthDBCPByProperties());
        String sql="SELECT * from demand_user where userId=? AND demandId=?";
        return (queryRunner.query(sql,new ArrayHandler(),userId,demandId).length!=0);
    }

    @Override
    public List<Demand> queryByUserId(int userId) throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSourceWIthDBCPByProperties());
        String sql="SELECT * FROM demand WHERE demandId=(SELECT demandId from demand_user where userId=? )";
        return queryRunner.query(sql,new BeanListHandler<>(Demand.class),userId);
    }

    @Override
    public List<Object[]> queryConditionOfApplyByUserByUserId(int userId) throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSourceWIthDBCPByProperties());
        String sql="SELECT demandId,conditionOfApply from demand_user where userId=? ";
        return queryRunner.query(sql,new ArrayListHandler(),userId);
    }

}
