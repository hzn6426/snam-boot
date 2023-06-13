package com.baomibing.snam.feign.authority.api;

import com.baomibing.snam.feign.authority.dto.UserWrapDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 用户服务远程调用
 *
 * @Author: zhenyang 2021/4/6 15:09
 * @version: 1.0.0
 */
@FeignClient(name = "snapper-gateway", path = "/feign/authority/user", url = "${feign.client.url}")
public interface UserApi {

	/**
	 * 通过工号列表获取用户列表
	 *
	 * @param userNos 工号列表
	 * @Return: java.util.List<com.baomibing.authority.dto.UserWrapDto>
	 */
	@PostMapping("listUserByUserNos")
	List<UserWrapDto> listUserByUserNos(@RequestBody List<String> userNos);

	/**
	 * 根据职位获取用户
	 * @param positionId
	 * @return
	 */
	@GetMapping("listUserByPosition/{positionId}")
	List<String> listUserByPosition(@PathVariable("positionId") String positionId);


}
