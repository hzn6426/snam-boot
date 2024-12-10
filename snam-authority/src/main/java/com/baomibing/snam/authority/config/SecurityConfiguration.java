package com.baomibing.snam.authority.config;

import com.baomibing.authority.runner.AuthorizationCacheWarmUpRunner;
import com.baomibing.authority.service.SystemService;
import com.baomibing.authority.service.SystemTenantService;
import com.baomibing.security.exception.AuthorityWebExceptionHandler;
import com.baomibing.security.filter.CommonAccessDeniedHandler;
import com.baomibing.security.filter.CommonAuthencaitonEntryPointHandler;
import com.baomibing.security.filter.CommonCrosFilter;
import com.baomibing.security.filter.CommonUserDetailManager;
import com.baomibing.security.service.SystemServiceImpl;
import com.baomibing.security.service.SystemTenantServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import java.util.Collections;
import java.util.List;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(authProvider());
	}

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
        impl.setUserDetailsService(userDetailService());
        impl.setPasswordEncoder(passwordEncoder());
        impl.setHideUserNotFoundExceptions(false) ;
        return impl;
    }
	/**
     * 自定义访问控制，默认是所有访问都要经过认证。
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	List<String> permitAllEndpointList = Collections.singletonList("/**");
        http.cors()
	        .and()
	        // 关闭CSRF
	        .csrf().disable()
    		.authorizeRequests()
    		// 放行所有OPTIONS请求
            .antMatchers(HttpMethod.OPTIONS).permitAll()
    		.antMatchers(permitAllEndpointList.toArray(new String[0])).permitAll()
            .anyRequest().authenticated()
            .and()
            // 添加未登录与权限不足异常处理器
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler())
            .authenticationEntryPoint(authenticationEntryPoint())
            .and()
            .addFilterBefore(jwtAuthenticationFilter(), FilterSecurityInterceptor.class)
            .addFilterAfter(jwtAuthorizationFilter(), CommonJwtAuthenticationFilter.class)
            .addFilterAfter(tenantJwtAuthenticationFilter(), CommonJwtAuthorizationFilter.class)
            .addFilterAfter(tenantJwtAuthorizationFilter(), CommonTenantJwtAuthenticationFilter.class)
            .addFilterAfter(hmacAuthenticationFilter(), CommonTenantJwtAuthorizationFilter.class)
            .addFilterAfter(hmacAuthorizationFilter(), CommonHmacAuthenticationFilter.class)
            .addFilterAfter(thirdPartAuthenticationFilter(), CommonHmacAuthenticationFilter.class)
            .addFilterAfter(commonRateLimitFilter(), ThirdPartAuthenticationFilter.class)
            .addFilterBefore(commonBlackFilter(), CommonJwtAuthenticationFilter.class)
            .addFilterBefore(filterChainExceptionHandler(), LogoutFilter.class)
            // 关闭Session机制
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public CommonJwtAuthenticationFilter jwtAuthenticationFilter() {
    	return new CommonJwtAuthenticationFilter();
    }

    @Bean
    public CommonJwtAuthorizationFilter jwtAuthorizationFilter() {
        return new CommonJwtAuthorizationFilter();
    }

    @Bean
    public CommonTenantJwtAuthenticationFilter tenantJwtAuthenticationFilter() {
        return new CommonTenantJwtAuthenticationFilter();
    }

    @Bean
    public CommonTenantJwtAuthorizationFilter tenantJwtAuthorizationFilter() {
        return new CommonTenantJwtAuthorizationFilter();
    }

    @Bean
    public CommonHmacAuthenticationFilter hmacAuthenticationFilter() {
        return new CommonHmacAuthenticationFilter();
    }

    @Bean
    public CommonHmacAuthorizationFilter hmacAuthorizationFilter() {
        return new CommonHmacAuthorizationFilter();
    }

    @Bean
    public CommonBlackFilter commonBlackFilter() {
        return new CommonBlackFilter();
    }

    @Bean
    public RateLimitFilter commonRateLimitFilter() {
        return new RateLimitFilter();
    }

    @Bean
    public FilterChainExceptionHandler filterChainExceptionHandler() {
        return new FilterChainExceptionHandler();
    }
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    
    @Bean
    public UserDetailsService userDetailService() {
    	return new CommonUserDetailManager();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
    	return new CommonAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
    	return new CommonAuthencaitonEntryPointHandler();
    }

    @Bean
    public ThirdPartAuthenticationFilter thirdPartAuthenticationFilter() {
        return new ThirdPartAuthenticationFilter();
    }

    @Bean
    public CommonCrosFilter commonCrosFilter() {
        return new CommonCrosFilter();
    }

    @Bean
    public AuthorityWebExceptionHandler authorityWebExceptionHandler() {
        return new AuthorityWebExceptionHandler();
    }

    @Bean
    public AuthorizationCacheWarmUpRunner authorizationCacheWarmUpRunner() {
        return new AuthorizationCacheWarmUpRunner();
    }

    @Bean
    public SystemService systemService() {
        return new SystemServiceImpl();
    }

    @Bean
    public SystemTenantService systemTenantService() {
        return new SystemTenantServiceImpl();
    }
}

