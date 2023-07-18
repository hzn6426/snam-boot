package com.baomibing.snam.business.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * WmsItemDto
 *
 * @author frog 2023/6/4 21:58
 * @version 1.0.0
 **/
@Data
@Accessors(chain = true)
public class WmsItemDto {

    private String id;

    private String code;

    private String name;

    private String classId;

    private String className;

    private String providerId;

    private String providerName;

    private BigDecimal quantity;

    private Date productTime;

    private Date expireTime;

    private String keeperCode;

    private String keeper;

    private String groupId;

    private String createUserCnName;

    private Date createTime;

    private String companyName;
}
