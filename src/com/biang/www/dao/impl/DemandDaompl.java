package com.biang.www.dao.impl;

import com.biang.www.dao.IDemandDao;
import com.biang.www.po.Demand;
import com.biang.www.po.User;
import com.biang.www.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class DemandDaompl implements IDemandDao {
    @Override
    public List<Demand> queryAllDemand() throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSourceWIthDBCPByProperties());
        String sql="select * from demand";
        return queryRunner.query(sql,new BeanListHandler<>(Demand.class));
    }

    @Override
    public List<Demand> queryByEnterpriseId(int enterpriseId) throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSourceWIthDBCPByProperties());
        String sql="select * from demand WHERE enterpriseId=?";
        return queryRunner.query(sql,new BeanListHandler<>(Demand.class),enterpriseId);
    }

    @Override
    public List<Demand> queryByConditionsOfCertification(int conditionsOfCertification) throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSourceWIthDBCPByProperties());
        String sql="select * from demand WHERE conditionsOfCertification=?";
        return queryRunner.query(sql,new BeanListHandler<>(Demand.class),conditionsOfCertification);
    }

    @Override
    public Demand queryByDemandid(int demandId) throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSourceWIthDBCPByProperties());
        String sql="select * from demand where demandId=?";
        return queryRunner.query(sql,new BeanHandler<>(Demand.class),demandId);
    }
}
