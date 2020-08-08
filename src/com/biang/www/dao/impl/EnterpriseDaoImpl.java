package com.biang.www.dao.impl;

import com.biang.www.dao.IEnterpriseDao;
import com.biang.www.po.Enterprise;
import com.biang.www.po.User;
import com.biang.www.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class EnterpriseDaoImpl implements IEnterpriseDao {
    @Override
    public Enterprise queryByEnterpriseId(int enterpriseId) throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSourceWIthDBCPByProperties());
        String sql="select * from enterprise where enterpriseId=?";
        return queryRunner.query(sql,new BeanHandler<>(Enterprise.class),enterpriseId);
    }

    @Override
    public Enterprise queryByUserId(int userId) throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSourceWIthDBCPByProperties());
        String sql="select * from enterprise where userId=?";
        return queryRunner.query(sql,new BeanHandler<>(Enterprise.class),userId);
    }
}
