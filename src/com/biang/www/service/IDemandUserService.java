package com.biang.www.service;

import com.biang.www.po.User;

public interface IDemandUserService {
    boolean applyDemand(User user, String demandId) throws Exception;

    boolean isApplied(String loginUserId, String demandId) throws Exception;
}
