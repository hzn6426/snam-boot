package com.baomibing.snam.feign.authority.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserWrapDto {

	private String id;
	private String userName;
	private String userNo;
	private String userRealCnName;
	private String userRealEnName;
	private String currentGroupId;
	private String currentPositionId;
	private String permScope;
	private String companyId;
	
	private boolean beIgnoreUserScope = false;
	private boolean beIgnoreGroupScope = false;

	private String groupId;

	private String permId;

	private String userMobile;
	private String userEmail;
	private String userTag;
}
