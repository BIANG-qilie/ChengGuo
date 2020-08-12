package com.biang.www.dao;

import com.biang.www.po.Demand;

import java.sql.SQLException;
import java.util.List;

public interface IDemandUserDao {

    boolean insert(int userId, String demandId) throws Exception;

    Object[] queryByDemandIdAndUserId(int userId, int demandId) throws Exception;

    List<Demand> queryDemandByUserId(int userId) throws Exception;

    List<Object[]> queryByUserId(int userId) throws Exception;

    List<Object[]> queryByDemandId(int demandId) throws SQLException;

    boolean updateConditionOfApplyByUserIdAndDemandId(int userId, int demandId, int conditionOfApply) throws SQLException;
}
