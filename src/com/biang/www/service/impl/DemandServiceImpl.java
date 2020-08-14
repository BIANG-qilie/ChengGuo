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
    public List<Demand> getPagingDemandFromAllDemand(int pageNumber) throws Exception {
        return demandDao.queryPagingDemandFromAllDemand(pageNumber);
    }

    @Override
    public List<Demand> getPagingDemandByEnterpriseUser(User loginUser, int pageNumber) throws Exception {
        return demandDao.queryPagingDemandByEnterpriseIdOrConditionOfCertification(enterpriseDao.queryByUserId(loginUser.getUserId()).getEnterpriseId(),Certification.PASS_CERTIFICATION,pageNumber);
    }

    @Override
    public List<Demand> getPassCertificationDemand(int pageNumber) throws Exception {
        return demandDao.queryPagingDemandByConditionsOfCertification(Certification.PASS_CERTIFICATION,pageNumber);
    }

    @Override
    public Demand getDemandByDemandId(int demandId) throws Exception {
        return demandDao.queryByDemandId(demandId);
    }

    @Override
    public List<Demand> queryFromAllDemand(String queryContent, int pageNumber) throws Exception {
        return demandDao.queryPagingDemandFromAllDemand(queryContent,pageNumber);
    }

    @Override
    public List<Demand> queryFromEnterpriseUser(User loginUser, String queryContent, int pageNumber) throws Exception {
        return demandDao.queryPagingDemandFromEnterpriseId(enterpriseDao.queryByUserId(loginUser.getUserId()).getEnterpriseId(),Certification.PASS_CERTIFICATION,queryContent,pageNumber);
    }

    @Override
    public List<Demand> queryFromPassCertificationDemand(String queryContent, int pageNumber) throws Exception {
        return demandDao.queryPagingDemandFromPassCertificationDemand(Certification.PASS_CERTIFICATION,queryContent,pageNumber);
    }

    @Override
    public List<Demand> getDemandByEnterprise(Enterprise enterprise) throws Exception {
        return demandDao.queryByEnterpriseId(enterprise.getEnterpriseId());
    }

    @Override
    public Demand addDemand(Demand demand) throws Exception {
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

    @Override
    public int getSizeOfAllDemands() throws SQLException {
        return Integer.parseInt(String.valueOf(demandDao.querySizeOfAllDemands()[0]));
    }
}
