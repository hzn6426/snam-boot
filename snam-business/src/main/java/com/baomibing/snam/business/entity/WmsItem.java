package com.baomibing.snam.business.entity;

import com.baomibing.orm.base.MBaseModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * WmsItem
 *
 * @author frog 2023/6/4 21:39
 * @version 1.0.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wms_item")
public class WmsItem extends MBaseModel {

    @TableId(type = IdType.ASSIGN_ID)
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

    private String sellerCode;

    private String seller;

    private String groupId;

    private String note;
}
