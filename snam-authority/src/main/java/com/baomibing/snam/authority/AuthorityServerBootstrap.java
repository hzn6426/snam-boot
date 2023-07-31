package com.baomibing.snam.authority;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.core.env.Environment;
import org.springframework.jmx.support.RegistrationPolicy;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.function.UnaryOperator;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEncryptableProperties
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@MapperScan("com.baomibing.authority.mapper")
@ComponentScan({ "com.baomibing.authority.**"})//"com.baomibing.web.**","com.baomibing.core.**","com.baomibing.orm.**"
@Slf4j
public class AuthorityServerBootstrap {

	public static void main(String[] args) throws UnknownHostException {
		long before = System.currentTimeMillis();
//		SpringApplication application = new SpringApplication(AuthorityServerBootstrap.class);
//		application.setBeanNameGenerator(new ProGuardBeanNameGenerator());
//		ConfigurableApplicationContext context =  application.run(args);
		ConfigurableApplicationContext context = new SpringApplicationBuilder(AuthorityServerBootstrap.class)
			.beanNameGenerator(new ProGuardBeanNameGenerator()).run(args);
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

	static class ProGuardBeanNameGenerator extends AnnotationBeanNameGenerator {
		@Override
		public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {

			UnaryOperator<String> fun= pkgName->{
				int lastIndex = pkgName.lastIndexOf ('.');
				if (lastIndex!=-1){
					pkgName=pkgName.substring (0, lastIndex);
				}
				return pkgName;
			};
			String className = super.generateBeanName(definition, registry);
			String packagename = definition.getBeanClassName();
			return (fun.apply(packagename) + "." + className);
		}
	}
}
