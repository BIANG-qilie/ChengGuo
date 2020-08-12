package com.biang.www.service.impl;

import com.biang.www.dao.IDemandUserDao;
import com.biang.www.dao.impl.DemandUserDaoImpl;
import com.biang.www.po.Demand;
import com.biang.www.po.User;
import com.biang.www.service.IDemandUserService;

import java.sql.SQLException;
import java.util.List;

public class DemandUserServiceImpl implements IDemandUserService {
    IDemandUserDao demandUserDao=new DemandUserDaoImpl();
    @Override
    public boolean applyDemand(User user, String demandId) throws Exception {
        return demandUserDao.insert(user.getUserId(),demandId);
    }

    @Override
    public boolean isApplied(int demandId, int userId) throws Exception {
        Object[] demandCondition=demandUserDao.queryByDemandIdAndUserId(demandId, userId);
        return (demandCondition!=null&&demandCondition.length!=0) ;
    }

    @Override
    public List<Demand> getDemandByUser(User user) throws Exception {
        return demandUserDao.queryDemandByUserId(user.getUserId());
    }

    @Override
    public List<Object[]> getConditionOfApplyByUser(User user) throws Exception {
        return demandUserDao.queryByUserId(user.getUserId());
    }

    @Override
    public List<Object[]> getConditionOfApplyByDemand(Demand demand) throws Exception {
        return demandUserDao.queryByDemandId(demand.getDemandId());
    }

    @Override
    public boolean changeConditionOfApplyByUserIdAndDemandId(int userId, int demandId, int conditionOfApply) throws SQLException {
        return demandUserDao.updateConditionOfApplyByUserIdAndDemandId(userId,demandId,conditionOfApply);
    }


}
