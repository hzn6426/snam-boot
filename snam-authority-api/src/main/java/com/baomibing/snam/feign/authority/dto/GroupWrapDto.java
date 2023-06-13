package com.baomibing.snam.feign.authority.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GroupWrapDto {

	private String id;

	private String parentId;

	private String groupName;
}
