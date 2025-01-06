package com.baomibing.snam.authority;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.core.env.Environment;
import org.springframework.jmx.support.RegistrationPolicy;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEncryptableProperties
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@MapperScan("com.baomibing.authority.mapper")
@ComponentScan({ "com.baomibing.authority.**","com.baomibing.snam.authority.**"})
@Slf4j
public class AuthorityServerBootstrap {

	public static void main(String[] args) throws UnknownHostException {
		long before = System.currentTimeMillis();
		ConfigurableApplicationContext context = new SpringApplicationBuilder(AuthorityServerBootstrap.class).run(args);
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
