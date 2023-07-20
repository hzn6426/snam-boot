package com.baomibing.snam.business.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * WmsOrderDto
 *
 * @author frog 2023/7/13 10:12
 * @version 1.0.0
 **/
@Data
@Accessors(chain = true)
public class WmsOrderDto {

    private String id;

    private String code;

    private String sellerCode;

    private String seller;

    private String serviceCode;

    private String service;

    private String itemId;

    private String itemName;

    private BigDecimal quantity;

    private BigDecimal price;

    private BigDecimal money;

    private String buyer;

    private String buyerAddress;

    private String buyerTel;

    private String note;

    private String groupId;

    private String unit;

    private Date createTime;


    private Date start;

    private Date end;
}
