package com.assignment.FileUploaderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.assignment.config.ApplicationConfig;

@SpringBootApplication
@ImportAutoConfiguration(classes = ApplicationConfig.class)
@EnableEurekaClient
public class FileUploaderService {

	public static void main(String[] args) {
		SpringApplication.run(FileUploaderService.class, args);
	}
}
