package com.biang.www.dao;

import com.biang.www.po.User;

public interface IDemandUserDao {

    boolean insert(int userId, String demandId) throws Exception;

    boolean queryByDemandIdAndUserId(int userId, String demandId) throws Exception;
}
