package com.biang.www.dao;

import java.sql.SQLException;
import java.util.List;

public interface IAnnexDao {
    boolean insert(int demandId, String annexFile) throws SQLException;

    List<Object[]> queryByDemandId(int demandId) throws SQLException;
}
