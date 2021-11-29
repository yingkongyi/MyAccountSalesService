package com.westlakefinancial.technology.dao;

import com.westlakefinancial.technology.entity.WorkInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Business Class Dao
 *
 * @author jiapeng.wu
 */
@Mapper
@Repository
public interface WorkDetailDao {

    int addWork(@Param("workInfo") WorkInfo workInfo);

    int modifyWork(@Param("workInfo") WorkInfo workInfo);
}
