package com.biang.www.dao;

import com.biang.www.po.User;

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

    boolean updateLevel(User user, int level) throws Exception;
}
