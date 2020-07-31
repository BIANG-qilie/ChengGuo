package com.biang.www.dao.impl;

import com.biang.www.dao.IUserDao;
import com.biang.www.po.User;
import com.biang.www.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @author Biang
 */
public class UserDaoImpl implements IUserDao {
    @Override
    public boolean insert(User user) throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSourceWIthDBCPByProperties());
        String sql="INSERT INTO user VALUES(default,?,?,?)";
        if(queryRunner.update(sql, user.getUserName(),user.getPassword(),user.getLevel())>0) {
            return true;
        }
        return false;
    }
    @Override
    public User queryByUserName(String userName) throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSourceWIthDBCPByProperties());
        String sql="select * from user where user_name=?";
        return queryRunner.query(sql,new BeanHandler<User>(User.class),userName);
    }
    @Override
    public User queryByUser(User user) throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSourceWIthDBCPByProperties());
        String sql="select * from user where user_name=? and password=?";
        return queryRunner.query(sql,new BeanHandler<User>(User.class),user.getUserName(),user.getPassword());
    }
}
