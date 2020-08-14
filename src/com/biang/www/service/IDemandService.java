package com.biang.www.service;

import com.biang.www.po.Demand;
import com.biang.www.po.Enterprise;
import com.biang.www.po.User;

import java.sql.SQLException;
import java.util.List;

public interface IDemandService {
    List<Demand> getPagingDemandFromAllDemand(int pageNumber) throws Exception;

    List<Demand> getPagingDemandByEnterpriseUser(User loginUser, int pageNumber) throws Exception;

    List<Demand> getPassCertificationDemand(int pageNumber) throws Exception;

    Demand getDemandByDemandId(int demandId) throws Exception;

    List<Demand> queryFromAllDemand(String queryContent, int pageNumber) throws Exception;

    List<Demand> queryFromEnterpriseUser(User loginUser, String queryContent, int pageNumber) throws Exception;

    List<Demand> queryFromPassCertificationDemand(String queryContent, int pageNumber) throws Exception;

    List<Demand> getDemandByEnterprise(Enterprise enterprise) throws Exception;

    Demand addDemand(Demand demand) throws Exception;

    List<Demand> getNotYetPassedDemandByEnterprise(Enterprise enterprise) throws Exception;

    boolean changeDemandCertification(Demand demand, int conditionOfCertification) throws Exception;

    boolean changeDemandConditionOfDemand(int demandId, int conditionOfDemand) throws SQLException;

    int getSizeOfAllDemands() throws SQLException;
}
