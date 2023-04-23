package com.watchworthy.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableFeignClients
@EntityScan(basePackages = {"com.watchworthy.api.entity"})
public class WatchWorthyApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchWorthyApplication.class, args);
	}

}
