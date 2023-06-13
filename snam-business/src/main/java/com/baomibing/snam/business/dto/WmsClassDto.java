package com.baomibing.snam.business.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * WmsClassDto
 *
 * @author frog 2023/6/4 21:58
 * @version 1.0.0
 **/
@Data
@Accessors(chain = true)
public class WmsClassDto {

    private String id;

    private String classCode;

    private String className;

    private String parentId;

    private String note;

    private String groupId;

    private Date createTime;
}
