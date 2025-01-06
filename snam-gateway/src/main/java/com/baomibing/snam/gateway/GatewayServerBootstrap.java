package com.baomibing.snam.gateway;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEncryptableProperties
@EnableFeignClients
@Slf4j
@ComponentScan({ "com.baomibing.snam.gateway.**"})
public class GatewayServerBootstrap {

	public static void main(String[] args) throws UnknownHostException {
		long before = System.currentTimeMillis();
		SpringApplication application = new SpringApplication(GatewayServerBootstrap.class);
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
