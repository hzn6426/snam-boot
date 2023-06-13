package com.baomibing.snam.feign.authority.api;


import com.baomibing.snam.feign.authority.dto.UserWrapDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

;


@FeignClient(name = "snapper-gateway", path = "/feign/authority/userAuth", url = "${feign.client.url}")
public interface BusinessAuthApi {

	@GetMapping("getPermIdByUrlAndMethod")
	String getPermIdByUrlAndMethod(@RequestParam("url") String url, @RequestParam("method") String method);

	@GetMapping("getPermIdByAction")
	String getPermIdByAction(@RequestParam("action") String action);

	@GetMapping("getEntrustBusinessPerm")
    String getEntrustBusinessPerm(@SpringQueryMap UserWrapDto userWrap);

}
