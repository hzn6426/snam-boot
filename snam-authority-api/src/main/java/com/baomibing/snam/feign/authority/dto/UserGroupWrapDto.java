package com.baomibing.snam.feign.authority.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * UserGroupWrapDto
 *
 * @author frog 2023/7/16 21:57
 * @version 1.0.0
 **/
@Data
@Accessors(chain = true)
public class UserGroupWrapDto {

    private  String id;
    private String userId;
    private String groupId;
}
