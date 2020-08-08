package com.biang.www.service.impl;

import com.biang.www.dao.IDemandUserDao;
import com.biang.www.dao.impl.DemandUserDaoImpl;
import com.biang.www.po.User;
import com.biang.www.service.IDemandUserService;

public class DemandUserServiceImpl implements IDemandUserService {
    IDemandUserDao demandUserDao=new DemandUserDaoImpl();
    @Override
    public boolean applyDemand(User user, String demandId) throws Exception {
        return demandUserDao.insert(user.getUserId(),demandId);
    }

    @Override
    public boolean isApplied(String loginUserId, String demandId) throws Exception {
        return demandUserDao.queryByDemandIdAndUserId(Integer.parseInt(loginUserId),demandId);
    }
}
