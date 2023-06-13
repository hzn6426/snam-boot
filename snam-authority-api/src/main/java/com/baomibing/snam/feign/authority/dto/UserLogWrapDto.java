package com.baomibing.snam.feign.authority.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 用户日志封装
 *
 * @author zening
 * @date Apr 25, 2021 10:29:41 AM
 * @version 1.0.0
 */
@Data
@Accessors(chain = true)
public class UserLogWrapDto {

	 /**
     * ID
     */
    private String id;
    /**
     * 传输地址
     */
    private String exchangeUrl;
    /**
     * 传输方法
     */
    private String exchangeMethod;
    /**
     * 传输名称
     */
    private String exchangeName;
    /**
     * 传输参数
     */
    private String exchangeParam;
    /**
     * 传输时间
     */
    private Date exchangeTime;
    /**
     * 状态
     */
    private String state;
    /**
     * 异常消息
     */
    private String exceptionMsg;
    /**
     * IP地址
     */
    private String ipAddress;
    /**
     * 执行方法
     */
    private String executeMethod;
    /**
     * 执行时间
     */
    private Integer executeTimer;
    /**
     * 执行的微服务名称
     */
    private String executeModuleName;
    /**
     * 创建用户
     */
    private String createUser;
    /**
     * 更新用户
     */
    private String updateUser;
    /**
     * 创建用户姓名
     */
    private String createUserCnName;
    /**
     * 更新用户姓名
     */
    private String updateUserCnName;
    /**
     * 日志类型编码
     */
    private String logTypeCode;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * 系统信息
     */
    private String os;
}
