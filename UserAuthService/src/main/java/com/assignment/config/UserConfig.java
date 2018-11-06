package com.assignment.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.assignment.repo")
@ComponentScan(basePackages = "com.assignment.*")
@EntityScan(basePackages = "com.assignment.model")
@EnableTransactionManagement
public class UserConfig {

}
