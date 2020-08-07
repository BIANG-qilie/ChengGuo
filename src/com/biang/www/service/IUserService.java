package com.biang.www.service;

import com.biang.www.po.User;

/**
 * @author BIANG
 */
public interface IUserService {
    public User login(User user) throws Exception;

    public boolean register(User user) throws Exception;

    public User isExist(User user);

    public User verifyEmail(User user) throws Exception;

    public boolean changePassword(User forgetPasswordUser, String newPassword) throws Exception;
}
