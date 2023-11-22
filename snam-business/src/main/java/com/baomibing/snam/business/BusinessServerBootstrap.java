package com.baomibing.snam.business;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.core.env.Environment;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * BusinessServerBootstrap
 *
 * @author frog 2023/6/5 16:47
 * @version 1.0.0
 **/

@SpringBootApplication
@EnableDiscoveryClient
@EnableEncryptableProperties
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@EnableFeignClients(basePackages = {"com.baomibing.snam.feign.authority"})
@EnableScheduling
@EnableAsync
@MapperScan("com.baomibing.snam.business.mapper")
@ComponentScan({ "com.baomibing.snam.business.**", "com.baomibing.snam.feign.authority.**" })
@Slf4j
public class BusinessServerBootstrap {

    public static void main(String[] args) throws UnknownHostException {
        long before = System.currentTimeMillis();
        SpringApplication application = new SpringApplication(BusinessServerBootstrap.class);
        ConfigurableApplicationContext context =  application.run(args);
        Environment env = context.getEnvironment();
        long after = System.currentTimeMillis();
        long use = (after - before) / 1000;
        log.warn("\n----------------------------------------------------------\n\t" +
                        "应用 '{}' 运行成功! 启动时间 {} 秒\n\t 访问连接:" + "http://{}:{}{}{}\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                use,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getProperty("server.servlet.context-path", ""),
                env.getProperty("spring.mvc.servlet.path", "")
        );
    }
}
