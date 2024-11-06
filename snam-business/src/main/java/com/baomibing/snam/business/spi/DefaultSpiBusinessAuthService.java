package com.baomibing.snam.business.spi;

import com.alibaba.fastjson.JSONObject;
import com.baomibing.core.common.ApplicationContextHandler;
import com.baomibing.core.spi.BusinessAuthService;
import com.baomibing.core.wrap.EntrustWarpper;
import com.baomibing.snam.feign.authority.api.BusinessAuthApi;
import com.baomibing.snam.feign.authority.dto.UserWrapDto;
import com.baomibing.tool.user.User;
import com.google.auto.service.AutoService;

/**
 *  com.baomibing.snam.business.spi.DefaultSpiBusinessAuthService
 *
 * @author frog 2023/6/5 20:46
 * @version 1.0.0
 **/
 @AutoService(value = BusinessAuthService.class)
public class DefaultSpiBusinessAuthService implements BusinessAuthService {

    private  BusinessAuthApi getBusinessAuthApi() {
        return ApplicationContextHandler.getBean(BusinessAuthApi.class);
    }

    @Override
    public EntrustWarpper getEntrustBusinessPerm(User user, String permId, String scope, boolean beIgnoreUserScope, boolean beIgnoreGroupScope) {

        UserWrapDto userWrap = new UserWrapDto().setUserName(user.getUserName()).setId(user.getId()).setCurrentGroupId(user.getCurrentGroupId())
                .setCurrentPositionId(user.getCurrentPositionId()).setPermId(permId).setPermScope(scope).setCompanyId(user.getCompanyId())
                .setBeIgnoreGroupScope(beIgnoreGroupScope).setBeIgnoreUserScope(beIgnoreUserScope);
        return JSONObject.parseObject(getBusinessAuthApi().getEntrustBusinessPerm(userWrap), EntrustWarpper.class);
    }

    @Override
    public String getPermActionByUrlAndMethod(String url, String method) {
        return getBusinessAuthApi().getPermIdByUrlAndMethod(url, method);
    }

    @Override
    public String getPermIdByUrlAndMethod(String url, String method) {
        return getBusinessAuthApi().getPermIdByUrlAndMethod(url, method);
    }

    @Override
    public String getPermIdByAction(String action) {
        BusinessAuthApi api = getBusinessAuthApi();
        return api.getPermIdByAction(action);
    }
}
