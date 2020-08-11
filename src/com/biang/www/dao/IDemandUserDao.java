package com.biang.www.dao;

import com.biang.www.po.Demand;

import java.sql.SQLException;
import java.util.List;

public interface IDemandUserDao {

    boolean insert(int userId, String demandId) throws Exception;

    boolean queryByDemandIdAndUserId(int userId, String demandId) throws Exception;

    List<Demand> queryByUserId(int userId) throws Exception;

    List<Object[]> queryDemandIdAndConditionOfApplyByUserId(int userId) throws Exception;

    List<Object[]> queryDemandIdAndConditionOfApplyByDemandId(int demandId) throws SQLException;

    boolean updateConditionOfApplyByUserIdAndDemandId(int userId, int demandId, int conditionOfApply) throws SQLException;
}
