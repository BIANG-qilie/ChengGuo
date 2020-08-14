package com.biang.www.dao.impl;

import com.biang.www.dao.IUserDao;
import com.biang.www.po.Demand;
import com.biang.www.po.User;
import com.biang.www.util.JDBCUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author Biang
 */
public class UserDaoImpl implements IUserDao {
    @Override
    public boolean insert(User user) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="INSERT INTO user VALUES(default,?,?,default,?)";
            return queryRunner.update(sql, user.getUserName(), user.getPassword(), user.getEmail()) > 0;
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
    public User queryByUserName(String userName) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from user where userName=?";
            return queryRunner.query(sql,new BeanHandler<>(User.class),userName);
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
    public User queryByUserNameAndPassword(User user) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from user where userName=? and password=?";
            return queryRunner.query(sql,new BeanHandler<>(User.class),user.getUserName(),user.getPassword());
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
    public User queryByUserNameAndEmail(User user) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from user where userName=? and email=?";
            return queryRunner.query(sql,new BeanHandler<>(User.class),user.getUserName(),user.getEmail());
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
    public boolean updatePassword(User user, String newPassword) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="UPDATE user SET password = ? WHERE userId=?";
            return queryRunner.update(sql, newPassword,user.getUserId())>0;
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
    public User queryByEmail(String email) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from user where email=?";
            return queryRunner.query(sql,new BeanHandler<>(User.class),email);
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
    public User queryByUserId(int loginUserId) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="select * from user where userId=?";
            return queryRunner.query(sql,new BeanHandler<>(User.class),loginUserId);
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
    public boolean updateLevel(User user, int level) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="UPDATE user SET level = ? WHERE userId=?";
            return queryRunner.update(sql, level,user.getUserId())>0;
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
    public boolean updateHeadImage(User user, String fileName) throws SQLException {
        DataSource dataSource = null;
        try {
            dataSource=JDBCUtils.getDataSourceWIthDBCPByProperties();
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String sql="UPDATE user SET headImage = ? WHERE userId=?";
            return queryRunner.update(sql, fileName,user.getUserId())>0;
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
