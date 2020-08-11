package com.biang.www.dao;

import com.biang.www.po.Enterprise;

import java.util.List;

public interface IEnterpriseDao {
    Enterprise queryByEnterpriseId(int enterpriseId) throws Exception;

    Enterprise queryByUserId(int userId) throws Exception;

    boolean insert(Enterprise enterprise) throws Exception;

    List<Enterprise> queryAllEnterprise()throws Exception;

    boolean updateConditionsOfCertification(Enterprise enterprise, int conditionsOfCertification) throws Exception;
}
