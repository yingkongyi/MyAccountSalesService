package com.westlakefinancial.technology.service;

import com.westlakefinancial.technology.entity.WorkInfo;

public interface WorkDetailService {
    boolean addWork(WorkInfo workInfo);

    boolean modifyWork(WorkInfo workInfo);
}
