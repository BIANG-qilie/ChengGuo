package com.biang.www.service;

import java.sql.SQLException;
import java.util.List;

public interface IAnnexService {
    boolean addAnnex(int demandId, String fileName) throws SQLException;

    List<Object[]> getAnnexesByDemandId(int demandId) throws SQLException;
}
