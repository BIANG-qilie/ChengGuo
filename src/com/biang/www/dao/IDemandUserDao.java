package com.biang.www.dao;

import com.biang.www.po.Demand;

import java.util.List;

public interface IDemandUserDao {

    boolean insert(int userId, String demandId) throws Exception;

    boolean queryByDemandIdAndUserId(int userId, String demandId) throws Exception;

    List<Demand> queryByUserId(int userId) throws Exception;

    List<Object[]> queryConditionOfApplyByUserByUserId(int userId) throws Exception;
}
