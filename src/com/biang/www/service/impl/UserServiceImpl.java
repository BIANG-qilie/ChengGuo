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
        return userDao.insert(user);
    }

    @Override
    public User isUserNameExist(User user) throws Exception {
            return userDao.queryByUserName(user.getUserName());
    }

    @Override
    public User verifyEmail(User user) throws Exception {
        return userDao.queryByUserNameAndEmail(user);
    }

    @Override
    public boolean changePassword(User user, String newPassword) throws Exception {
        return userDao.updatePassword(user,newPassword);
    }

    @Override
    public User isEmailExist(User user) throws Exception {
        return userDao.queryByEmail(user.getEmail());
    }

    @Override
    public User getUserByUserId(int loginUserId) throws Exception {
        return userDao.queryByUserId(loginUserId);
    }

    @Override
    public boolean upgrade(User user) throws Exception {
        return userDao.updateLevel(user,User.ENTERPRISE_USER);
    }

    @Override
    public boolean changeHeadImage(User user, String fileName) throws Exception {
        return userDao.updateHeadImage(user, fileName);
    }
}

