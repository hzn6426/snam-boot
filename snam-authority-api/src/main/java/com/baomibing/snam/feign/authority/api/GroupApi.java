package com.baomibing.snam.feign.authority.api;

import com.baomibing.snam.feign.authority.dto.GroupWrapDto;
import com.baomibing.snam.feign.authority.dto.UserGroupWrapDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 用户服务远程调用
 *
 * @Author: zhenyang 2021/4/6 15:09
 * @version: 1.0.0
 */
@FeignClient(name = "snapper-gateway", path = "/feign/authority/group", url = "${feign.client.url}")
public interface GroupApi {

    @GetMapping("getGroupIdByUserNo/{userNo}")
    String getGroupIdByUserNo(@PathVariable("userNo") String userNo);

    @GetMapping("listBranchCompanines")
    List<GroupWrapDto> listBranchCompanines();
    @GetMapping("getParentCompanyById")
    GroupWrapDto getParentCompanyById(@RequestParam String groupId);

    @PostMapping("listUserGroupByUserNos")
    public List<UserGroupWrapDto> listUserGroupByUserNos(@RequestBody Set<String> userNos);

}
