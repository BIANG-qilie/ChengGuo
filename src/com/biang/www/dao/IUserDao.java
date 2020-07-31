package com.biang.www.dao;

import com.biang.www.po.User;

/**
 * @author BIANG
 */
public interface IUserDao {
    public boolean insert(User user) throws Exception;
    public User queryByUserName(String userName) throws Exception;
    public User queryByUser(User user) throws Exception;
}
