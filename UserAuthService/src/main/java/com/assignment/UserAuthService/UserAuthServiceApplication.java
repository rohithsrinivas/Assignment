package com.assignment.UserAuthService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.assignment.config.UserConfig;

@SpringBootApplication
@ImportAutoConfiguration(classes = UserConfig.class)
@EnableEurekaClient
public class UserAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthServiceApplication.class, args);
	}
}
