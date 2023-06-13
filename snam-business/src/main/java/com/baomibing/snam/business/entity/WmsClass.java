package com.baomibing.snam.business.entity;

import com.baomibing.orm.base.MBaseModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * WmsClass
 *
 * @author frog 2023/6/4 21:51
 * @version 1.0.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("wms_class")
public class WmsClass extends MBaseModel {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String classCode;

    private String className;

    private String parentId;

    private String note;

    private String groupId;

}
