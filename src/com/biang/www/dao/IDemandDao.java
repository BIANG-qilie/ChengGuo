package com.biang.www.dao;

import com.biang.www.po.Demand;

import java.sql.SQLException;
import java.util.List;

/**
 * @author BIANG
 */
public interface IDemandDao {
    List<Demand> queryAllDemand() throws Exception;

    List<Demand> queryByEnterpriseId(int enterpriseId) throws Exception;

    List<Demand> queryByConditionsOfCertification(int conditionsOfCertification) throws Exception;

    Demand queryByDemandid(int demandId) throws Exception;

    List<Demand> queryFromAllDemand(String queryContent) throws Exception;

    List<Demand> queryFromEnterpriseId(int enterpriseId, int conditionOfCertification,String queryContent) throws Exception;

    List<Demand> queryFromPassCertificationDemand(int conditionsOfCertification,String queryContent) throws Exception;

    List<Demand> queryByEnterpriseIdOrConditionOfCertification(int enterpriseId, int conditionOfCertification) throws Exception;

    boolean insert(Demand demand) throws Exception;

    List<Demand> queryByEnterpriseIdAndConditionOfCertification(int enterpriseId, int conditionOfCertification) throws Exception;

    boolean updateConditionsOfCertification(Demand demand, int conditionsOfCertification) throws Exception;

    boolean updateConditionOfDemand(int demandId, int conditionOfDemand) throws SQLException;
}
