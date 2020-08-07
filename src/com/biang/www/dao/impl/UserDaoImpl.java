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
        String sql="INSERT INTO user VALUES(default,?,?,default,?)";
        if(queryRunner.update(sql, user.getUserName(),user.getPassword(),user.getEmail())>0) {
            return true;
        }
        return false;
    }
    @Override
    public User queryByUserName(String userName) throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSourceWIthDBCPByProperties());
        String sql="select * from user where userName=?";
        return queryRunner.query(sql,new BeanHandler<User>(User.class),userName);
    }
    @Override
    public User queryByUserNameAndPassword(User user) throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSourceWIthDBCPByProperties());
        String sql="select * from user where userName=? and password=?";
        return queryRunner.query(sql,new BeanHandler<User>(User.class),user.getUserName(),user.getPassword());
    }

    @Override
    public User queryByUserNameAndEmail(User user) throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSourceWIthDBCPByProperties());
        String sql="select * from user where userName=? and email=?";
        return queryRunner.query(sql,new BeanHandler<User>(User.class),user.getUserName(),user.getEmail());
    }

    @Override
    public boolean updatePassword(User user, String newPassword) throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSourceWIthDBCPByProperties());
        String sql="UPDATE user SET password = ? WHERE userId=?";
        if(queryRunner.update(sql, newPassword,user.getUserId())>0) {
            return true;
        }
        return false;
    }
}
