package com.biang.www.service;

import com.biang.www.po.User;

/**
 * @author BIANG
 */
public interface IUserService {
    User login(User user) throws Exception;

    boolean register(User user) throws Exception;

    User isUserNameExist(User user) throws Exception;

    User verifyEmail(User user) throws Exception;

    boolean changePassword(User forgetPasswordUser, String newPassword) throws Exception;

    User isEmailExist(User user) throws Exception;

    User getUserByUserId(int loginUserId) throws Exception;

    boolean upgrade(User user) throws Exception;

    boolean changeHeadImage(User user, String fileName) throws Exception;
}
