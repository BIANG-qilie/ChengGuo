package com.biang.www.service;

import com.biang.www.po.Enterprise;

public interface IEnterpriseService {
    Enterprise getEnterpriseByEnterpriseId(int enterpriseId) throws Exception;
}
