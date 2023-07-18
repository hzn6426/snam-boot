package com.baomibing.snam.business.entity;

import com.baomibing.orm.base.MBaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * WmsOrder
 *
 * @author frog 2023/7/12 17:05
 * @version 1.0.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wms_order")
public class WmsOrder extends MBaseModel {

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



}
