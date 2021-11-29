package com.westlakefinancial.technology.entity;

import lombok.Data;

import java.util.Date;

/**
 * Business entity
 *
 * @author jiapeng.wu
 */
@Data
public class WorkInfo {
    private String workId;
    private String workDetail;
    private Date createDate;
    private Date lastUpdateDate;
}
