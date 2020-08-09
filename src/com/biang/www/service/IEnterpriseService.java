package com.biang.www.service;

import com.biang.www.po.Enterprise;
import com.biang.www.po.User;

public interface IEnterpriseService {
    Enterprise getEnterpriseByEnterpriseId(int enterpriseId) throws Exception;

    Enterprise getEnterpriseByUser(User user) throws Exception;
}
