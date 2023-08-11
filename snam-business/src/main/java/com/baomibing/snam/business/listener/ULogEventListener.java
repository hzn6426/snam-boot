package com.baomibing.snam.business.listener;

import com.baomibing.snam.feign.authority.api.UserLogApi;
import com.baomibing.snam.feign.authority.dto.UserLogWrapDto;
import com.baomibing.web.event.UserLogEvent;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * 用户日志监听，监听日志，写入日志
 *
 * @author : zening
 * @version: 1.0.0
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class ULogEventListener {
	
	@Autowired private UserLogApi userLogApi;
	@Autowired private Mapper mapper;

    @Async
    @EventListener
    public void handleUserLogEvent(UserLogEvent event) {
    	UserLogWrapDto ul = mapper.map(event, UserLogWrapDto.class);
    	userLogApi.saveUserLog(ul);
    }
}
