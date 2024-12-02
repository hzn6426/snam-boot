/**
 * Copyright (c) 2018-2023, zening (316279828@qq.com).
 * <p>
 * Any unauthorised copying, selling, transferring, distributing, transmitting, renting,
 * or modifying of the Software is considered an infringement.
 */
package com.baomibing.snam.business.config;

import com.baomibing.web.filter.MultiRequestWrapFilter;
import com.baomibing.web.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;


@Configuration
public class WrapFilterConfiguration {
	

	@Bean
    public FilterRegistrationBean<MultiRequestWrapFilter> multiRequestFilter() {
		FilterRegistrationBean<MultiRequestWrapFilter> filterRegistration = new FilterRegistrationBean<>(new MultiRequestWrapFilter());
        filterRegistration.addUrlPatterns("/*");
		filterRegistration.setOrder(-1);
        return filterRegistration;
	}
	
	@Bean
	public FilterRegistrationBean<XssFilter> xssFilter() {
		FilterRegistrationBean<XssFilter> filterRegistration = new FilterRegistrationBean<>(new XssFilter());
      filterRegistration.addUrlPatterns("/*");
      String ignorePaths = new StringJoiner(",")
          .add("/favicon.ico")
          .add("/doc.html")
          .add("/swagger-ui.html")
          .add("/csrf")
          .add("/webjars/*")
          .add("/v2/*")
          .add("/swagger-resources/*")
          .add("/resources/*")
          .add("/static/*")
          .add("/public/*")
          .add("/classpath:*")
          .add("/actuator/*")
          .add("/**/noxss/**")
          .add("/druid/*")
          .toString();
	  Map<String, String> initParameters = new HashMap<>(1);
	  initParameters.put("exclusions", ignorePaths);
	  filterRegistration.setInitParameters(initParameters);
	  filterRegistration.setOrder(-2);
	  return filterRegistration;
	}
	
	
}
