package com.baomibing.snam.business.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * WmsProviderDto
 *
 * @author frog 2023/6/4 22:00
 * @version 1.0.0
 **/
@Data
@Accessors(chain = true)
public class WmsProviderDto {

    private String id;
    private String name;
    private String code;
    private String simpleName;
    private String linkMan;
    private String linkPhone;
    private String groupId;
    private String note;
}
