package com.biang.www.service.impl;

import com.biang.www.dao.IEnterpriseDao;
import com.biang.www.dao.impl.EnterpriseDaoImpl;
import com.biang.www.po.Enterprise;
import com.biang.www.po.User;
import com.biang.www.service.IEnterpriseService;

import java.util.List;

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

    @Override
    public boolean addEnterprise(Enterprise enterprise) throws Exception {
        return enterpriseDao.insert(enterprise);
    }

    @Override
    public List<Enterprise> getAllEnterprise() throws Exception {
        return enterpriseDao.queryAllEnterprise();
    }

    @Override
    public boolean changeEnterpriseCertification(Enterprise enterprise, int conditionsOfCertification) throws Exception {
        return enterpriseDao.updateConditionsOfCertification(enterprise,conditionsOfCertification);
    }
}
