package com.biang.www.service;

import com.biang.www.po.Demand;
import com.biang.www.po.User;

import java.util.List;

public interface IDemandService {
    List<Demand> getAllDemand() throws Exception;

    List<Demand> getDemandByEnterpriseUser(User loginUser) throws Exception;

    List<Demand> getPassCertificationDemand() throws Exception;

    Demand getDemandByDemandId(int demandId) throws Exception;
}
