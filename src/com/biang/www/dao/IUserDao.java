package com.biang.www.dao;

import com.biang.www.po.User;

import java.sql.SQLException;

/**
 * @author BIANG
 */
public interface IUserDao {
    boolean insert(User user) throws Exception;
    User queryByUserName(String userName) throws Exception;
    User queryByUserNameAndPassword(User user) throws Exception;
    User queryByUserNameAndEmail(User user) throws Exception;

    boolean updatePassword(User user, String newPassword) throws Exception;

    User queryByEmail(String email) throws Exception;

    User queryByUserId(int loginUserId) throws Exception;
}
