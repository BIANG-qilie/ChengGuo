package com.biang.www.dao;

import com.biang.www.po.User;

import java.sql.SQLException;

/**
 * @author BIANG
 */
public interface IUserDao {
    public boolean insert(User user) throws Exception;
    public User queryByUserName(String userName) throws Exception;
    public User queryByUserNameAndPassword(User user) throws Exception;
    public User queryByUserNameAndEmail(User user) throws Exception;

    public boolean updatePassword(User user, String newPassword) throws Exception;
}
