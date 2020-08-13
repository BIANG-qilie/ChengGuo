package com.biang.www.service.impl;

import com.biang.www.dao.IAnnexDao;
import com.biang.www.dao.impl.AnnexDaoImpl;
import com.biang.www.service.IAnnexService;

import java.sql.SQLException;
import java.util.List;

public class AnnexServiceImpl implements IAnnexService {
    IAnnexDao annexDao=new AnnexDaoImpl();
    @Override
    public boolean addAnnex(int demandId, String annexFile) throws SQLException {
        return annexDao.insert(demandId,annexFile);
    }

    @Override
    public List<Object[]> getAnnexesByDemandId(int demandId) throws SQLException {
        return annexDao.queryByDemandId(demandId);
    }
}
