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
        return userDao.queryByUserNameAndPassword(user);
    }

    @Override
    public boolean register(User user) throws Exception {
        if(userDao.queryByUserName(user.getUserName())!=null) {
            return false;
        }
        return userDao.insert(user);
    }

    @Override
    public User isExist(User user) {
        try {
            return userDao.queryByUserName(user.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User verifyEmail(User user) throws Exception {
        return userDao.queryByUserNameAndEmail(user);
    }

    @Override
    public boolean changePassword(User user, String newPassword) throws Exception {
        return userDao.updatePassword(user,newPassword);
    }
}

