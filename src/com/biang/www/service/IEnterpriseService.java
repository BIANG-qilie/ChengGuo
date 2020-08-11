package com.biang.www.service;

import com.biang.www.po.Enterprise;
import com.biang.www.po.User;

import java.util.List;

public interface IEnterpriseService {
    Enterprise getEnterpriseByEnterpriseId(int enterpriseId) throws Exception;

    Enterprise getEnterpriseByUser(User user) throws Exception;

    boolean addEnterprise(Enterprise enterprise) throws Exception;

    List<Enterprise> getAllEnterprise()throws Exception;

    boolean changeEnterpriseCertification(Enterprise enterprise, int conditionsOfCertification) throws Exception;
}
