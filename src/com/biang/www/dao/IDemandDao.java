package com.biang.www.dao;

import com.biang.www.po.Demand;

import java.util.List;

public interface IDemandDao {
    List<Demand> queryAllDemand() throws Exception;

    List<Demand> queryByEnterpriseId(int enterpriseId) throws Exception;

    List<Demand> queryByConditionsOfCertification(int conditionsOfCertification) throws Exception;

    Demand queryByDemandid(int demandId) throws Exception;

    List<Demand> queryFromAllDemand(String queryContent) throws Exception;

    List<Demand> queryFromEnterpriseId(int enterpriseId, int conditionOfCertification,String queryContent) throws Exception;

    List<Demand> queryFromPassCertificationDemand(int conditionsOfCertification,String queryContent) throws Exception;

    List<Demand> queryByEnterpriseIdAndConditionOfCertification(int enterpriseId, int conditionOfCertification) throws Exception;
}
