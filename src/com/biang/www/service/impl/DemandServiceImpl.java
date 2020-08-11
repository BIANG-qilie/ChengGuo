package com.biang.www.service.impl;

import com.biang.www.dao.IDemandDao;
import com.biang.www.dao.IEnterpriseDao;
import com.biang.www.dao.impl.DemandDaompl;
import com.biang.www.dao.impl.EnterpriseDaoImpl;
import com.biang.www.po.Demand;
import com.biang.www.po.Enterprise;
import com.biang.www.po.User;
import com.biang.www.service.IDemandService;
import com.biang.www.util.Certification;

import java.sql.SQLException;
import java.util.List;

public class DemandServiceImpl implements IDemandService {
    IDemandDao demandDao=new DemandDaompl();
    IEnterpriseDao enterpriseDao=new EnterpriseDaoImpl();
    @Override
    public List<Demand> getAllDemand() throws Exception {
        return demandDao.queryAllDemand();
    }

    @Override
    public List<Demand> getDemandByEnterpriseUser(User loginUser) throws Exception {
        return demandDao.queryByEnterpriseIdOrConditionOfCertification(enterpriseDao.queryByUserId(loginUser.getUserId()).getEnterpriseId(),Certification.PASS_CERTIFICATION);
    }

    @Override
    public List<Demand> getPassCertificationDemand() throws Exception {
        return demandDao.queryByConditionsOfCertification(Certification.PASS_CERTIFICATION);
    }

    @Override
    public Demand getDemandByDemandId(int demandId) throws Exception {
        return demandDao.queryByDemandid(demandId);
    }

    @Override
    public List<Demand> queryFromAllDemand(String queryContent) throws Exception {
        return demandDao.queryFromAllDemand(queryContent);
    }

    @Override
    public List<Demand> queryFromEnterpriseUser(User loginUser,String queryContent) throws Exception {
        return demandDao.queryFromEnterpriseId(enterpriseDao.queryByUserId(loginUser.getUserId()).getEnterpriseId(),Certification.PASS_CERTIFICATION,queryContent);
    }

    @Override
    public List<Demand> queryFromPassCertificationDemand(String queryContent) throws Exception {
        return demandDao.queryFromPassCertificationDemand(Certification.PASS_CERTIFICATION,queryContent);
    }

    @Override
    public List<Demand> getDemandByEnterprise(Enterprise enterprise) throws Exception {
        return demandDao.queryByEnterpriseId(enterprise.getEnterpriseId());
    }

    @Override
    public boolean addDemand(Demand demand) throws Exception {
        return demandDao.insert(demand);
    }

    @Override
    public List<Demand> getNotYetPassedDemandByEnterprise(Enterprise enterprise) throws Exception {
        return demandDao.queryByEnterpriseIdAndConditionOfCertification(enterprise.getEnterpriseId(),Certification.CERTIFICATION_AUDITING);
    }

    @Override
    public boolean changeDemandCertification(Demand demand, int conditionOfCertification) throws Exception {
        return demandDao.updateConditionsOfCertification(demand,conditionOfCertification);
    }

    @Override
    public boolean changeDemandConditionOfDemand(int demandId, int conditionOfDemand) throws SQLException {
        return demandDao.updateConditionOfDemand(demandId,conditionOfDemand);
    }
}
