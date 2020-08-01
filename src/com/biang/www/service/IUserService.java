package com.biang.www.service;

import com.biang.www.po.User;

/**
 * @author BIANG
 */
public interface IUserService {
    public User login(User user) throws Exception;

    public boolean register(User user) throws Exception;
}
