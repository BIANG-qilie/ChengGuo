package com.biang.www.service.impl;

import com.biang.www.dao.IDemandUserDao;
import com.biang.www.dao.impl.DemandUserDaoImpl;
import com.biang.www.po.Demand;
import com.biang.www.po.User;
import com.biang.www.service.IDemandUserService;

import java.util.List;

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

    @Override
    public List<Demand> getDemandByUser(User user) throws Exception {
        return demandUserDao.queryByUserId(user.getUserId());
    }

    @Override
    public List<Object[]> getConditionOfApplyByUser(User user) throws Exception {
        return demandUserDao.queryConditionOfApplyByUserByUserId(user.getUserId());
    }
}
