package com.unir.ms_operator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsOperatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsOperatorApplication.class, args);
	}

}
