package com.biang.www.dao;

import com.biang.www.po.Enterprise;

public interface IEnterpriseDao {
    Enterprise queryByEnterpriseId(int enterpriseId) throws Exception;

    Enterprise queryByUserId(int userId) throws Exception;
}
