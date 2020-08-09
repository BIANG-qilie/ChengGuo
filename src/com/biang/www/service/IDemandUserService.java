package com.biang.www.service;

import com.biang.www.po.Demand;
import com.biang.www.po.User;

import java.util.List;


public interface IDemandUserService {
    boolean applyDemand(User user, String demandId) throws Exception;

    boolean isApplied(String loginUserId, String demandId) throws Exception;

    List<Demand> getDemandByUser(User user) throws Exception;

    List<Object[]> getConditionOfApplyByUser(User user) throws Exception;
}
