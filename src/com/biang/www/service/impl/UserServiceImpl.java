package com.biang.www.service.impl;

import com.biang.www.dao.IUserDao;
import com.biang.www.dao.impl.UserDaoImpl;
import com.biang.www.po.User;
import com.biang.www.service.IUserService;

/**
 * @author dell
 */
public class UserServiceImpl implements IUserService {
    IUserDao userDao=new UserDaoImpl();
    @Override
    public User login(User user) throws Exception {
        return userDao.queryByUser(user);
    }

    @Override
    public boolean register(User user) throws Exception {
        if(userDao.queryByUserName(user.getUserName())!=null) {
            return false;
        }
        user.setLevel(1);
        return userDao.insert(user);
    }
}

