/**
 * Copyright (c) 2018-2023, zening (316279828@qq.com).
 * <p>
 * Any unauthorised copying, selling, transferring, distributing, transmitting, renting,
 * or modifying of the Software is considered an infringement.
 */
package com.baomibing.snam.business.config;

import com.baomibing.web.common.ReturnHandlerAdvice;
import com.baomibing.web.convert.*;
import com.baomibing.web.exception.GlobalExceptionHandler;
import com.baomibing.web.interceptor.ContextHandlerInterceptor;
import com.baomibing.web.undertow.UndertowServerFactoryCustomizer;
import com.google.common.collect.Lists;
import io.undertow.Undertow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.List;
import java.util.Locale;

/**
 * WebMvc 通用配置类
 * 
 * @author zening
 * @since 1.0.0
 */
@ConditionalOnProperty(prefix = "spring.mvc", name = "enabled", havingValue = "true", matchIfMissing = true)
@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

	@Autowired
	private MappingJackson2HttpMessageConverter jacksonMessageConverter;
	@Autowired
	private StringHttpMessageConverter stringMessageConvert;

	@Bean
	public ContextHandlerInterceptor contextInterceptor() {
		return new ContextHandlerInterceptor();
	}

//	@Bean
//	public TenantHandlerInterceptor tenantInterceptor() {
//		return new TenantHandlerInterceptor();
//	}

	@Bean
	public ReturnHandlerAdvice returnHandlerAdvice() {
		return  new ReturnHandlerAdvice();
	}

	@Bean
	public GlobalExceptionHandler globalExceptionHandler() {
		return new GlobalExceptionHandler();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(contextInterceptor()).excludePathPatterns(Lists.newArrayList("/activiti/**"))
				.addPathPatterns("/**").order(-1);

//		registry.addInterceptor(tenantInterceptor()).excludePathPatterns(Lists.newArrayList("/activiti/**"))
//				.addPathPatterns("/**").order(1);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new Date2StringConverter());
		registry.addConverter(new LocalDate2StringConverter());
		registry.addConverter(new LocalDateTime2StringConverter());
		registry.addConverter(new LocalTime2StringConverter());
		registry.addConverter(new String2DateConverter());
		registry.addConverter(new String2LocalDateConverter());
		registry.addConverter(new String2LocalDateTimeConverter());
		registry.addConverter(new String2LocalTimeConverter());
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(jacksonMessageConverter);
		converters.add(stringMessageConvert);
	}

	@Bean
	public LocalValidatorFactoryBean validatorFactoryBean() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.getValidationPropertyMap().put("hibernate.validator.fail_fast", "true");
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:Validation");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(false);
		messageSource.setCacheSeconds(60);
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		// 指定默认语言为中文
		localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
		return localeResolver;
	}

	@Bean
	@ConditionalOnClass(Undertow.class)
	public UndertowServerFactoryCustomizer getUndertowServerFactoryCustomizer() {
		return new UndertowServerFactoryCustomizer();
	}

}
