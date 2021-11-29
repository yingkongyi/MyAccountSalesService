package com.westlakefinancial.technology.service.impl;

import com.westlakefinancial.technology.dao.WorkDetailDao;
import com.westlakefinancial.technology.entity.WorkInfo;
import com.westlakefinancial.technology.service.WorkDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Business Class Service
 *
 * @author jiapeng.wu
 */
@Service
public class WorkDetailServiceImpl implements WorkDetailService {

    @Autowired
    private WorkDetailDao workDetailDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addWork(WorkInfo workInfo) {
        return workDetailDao.addWork(workInfo) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean modifyWork(WorkInfo workInfo) {
        return workDetailDao.modifyWork(workInfo) > 0;
    }
}
