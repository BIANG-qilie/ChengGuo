package com.biang.www.dao;

import com.biang.www.po.Demand;

import java.sql.SQLException;
import java.util.List;

/**
 * @author BIANG
 */
public interface IDemandDao {
    List<Demand> queryPagingDemandFromAllDemand(int pageNumber) throws Exception;

    List<Demand> queryByEnterpriseId(int enterpriseId) throws Exception;

    List<Demand> queryPagingDemandByConditionsOfCertification(int conditionsOfCertification, int pageNumber) throws Exception;

    Demand queryByDemandId(int demandId) throws Exception;

    List<Demand> queryPagingDemandFromAllDemand(String queryContent, int pageNumber) throws Exception;

    List<Demand> queryPagingDemandFromEnterpriseId(int enterpriseId, int conditionOfCertification, String queryContent, int pageNumber) throws Exception;

    List<Demand> queryPagingDemandFromPassCertificationDemand(int conditionsOfCertification, String queryContent, int pageNumber) throws Exception;

    List<Demand> queryPagingDemandByEnterpriseIdOrConditionOfCertification(int enterpriseId, int conditionOfCertification, int pageNumber) throws Exception;

    Demand insert(Demand demand) throws Exception;

    List<Demand> queryByEnterpriseIdAndConditionOfCertification(int enterpriseId, int conditionOfCertification) throws Exception;

    boolean updateConditionsOfCertification(Demand demand, int conditionsOfCertification) throws Exception;

    boolean updateConditionOfDemand(int demandId, int conditionOfDemand) throws SQLException;

    Object[] querySizeOfAllDemands() throws SQLException;
}
