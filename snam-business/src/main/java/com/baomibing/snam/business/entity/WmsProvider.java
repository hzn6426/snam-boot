package com.baomibing.snam.business.entity;

import com.baomibing.orm.base.MBaseModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * WmsProvider
 *
 * @author frog 2023/6/4 21:54
 * @version 1.0.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("wms_provider")
public class WmsProvider extends MBaseModel {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String name;
    private String code;
    private String simpleName;
    private String linkMan;
    private String linkPhone;
    private String groupId;
    private String note;
}
