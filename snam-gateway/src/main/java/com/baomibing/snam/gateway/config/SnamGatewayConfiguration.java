package com.baomibing.snam.gateway.config;

import com.baomibing.gateway.filter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SnamGatewayConfiguration
 *
 * @author frog 2023/5/17 20:11
 * @version 1.0.0
 **/
@Configuration
public class SnamGatewayConfiguration {

    @Bean
	public BlackListFilter blackListFilter() {
		return new BlackListFilter();
	}

	@Bean
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();
	}

	@Bean
	public RateLimitFilter rateLimiterFilter() {
		return new RateLimitFilter();
	}

	@Bean
	public LogTraceFilter logTraceFilter() {
		return new LogTraceFilter();
	}


	@Bean
	public AuthorizationFilter authorizationFilter() {
		return new AuthorizationFilter();
	}

	@Bean
	public HmacFilter hmacFilter() {
		return new HmacFilter();
	}

	@Bean
	public HmacAuthorizationFilter hmacAuthorizationFilter() {
		return new HmacAuthorizationFilter();
	}

	@Bean
	public ThirdPartFilter thirdPartFilter() {
		return new ThirdPartFilter();
	}

	@Bean
	public TenantJwtTokenFilter tenantJwtTokenFilter() {
		return new TenantJwtTokenFilter();
	}

	@Bean
	public TenantAuthorizationFilter tenantAuthorizationFilter() {
		return new TenantAuthorizationFilter();
	}


}
