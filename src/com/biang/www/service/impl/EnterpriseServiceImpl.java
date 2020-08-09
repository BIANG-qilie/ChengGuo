package com.biang.www.service.impl;

import com.biang.www.dao.IEnterpriseDao;
import com.biang.www.dao.impl.EnterpriseDaoImpl;
import com.biang.www.po.Enterprise;
import com.biang.www.po.User;
import com.biang.www.service.IEnterpriseService;

public class EnterpriseServiceImpl implements IEnterpriseService {
    IEnterpriseDao enterpriseDao=new EnterpriseDaoImpl();
    @Override
    public Enterprise getEnterpriseByEnterpriseId(int enterpriseId) throws Exception {
        return enterpriseDao.queryByEnterpriseId(enterpriseId);
    }

    @Override
    public Enterprise getEnterpriseByUser(User user) throws Exception {
        return enterpriseDao.queryByUserId(user.getUserId());
    }
}
