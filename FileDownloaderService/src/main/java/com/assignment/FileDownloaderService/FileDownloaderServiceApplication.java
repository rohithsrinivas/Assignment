package com.assignment.FileDownloaderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.assignment.config.ApplicationConfig;

@SpringBootApplication
@ImportAutoConfiguration(classes = ApplicationConfig.class)
@EnableEurekaClient
public class FileDownloaderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileDownloaderServiceApplication.class, args);
	}
}
