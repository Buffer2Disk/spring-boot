package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

//springBootApplication 等价于 默认使用@configuration @commpomentScan @EnableAutoConfiguration
@SpringBootApplication
@MapperScan("com.example.mapper")
@EnableScheduling
public class DemoApplication extends SpringBootServletInitializer{

	//右键debug，或者run用，项目里面不要出现main函数，否则会被scan到，报错
	public static void main(String[] args) {
		System.out.println("<---------main------------------>" );
		SpringApplication.run(DemoApplication.class, args);
	}

	/*发布到tomcat里面用这个*/
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		System.out.println("<-------------SpringApplicationBuilder-------------->" );
		return builder.sources(this.getClass());
	}
}
