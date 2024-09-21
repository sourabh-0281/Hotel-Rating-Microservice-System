package com.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class MicroservicesApplication {

	@Bean
	@LoadBalanced  //This configuration allows the RestTemplate to make calls to a service using its logical name
	public RestTemplate rt() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MicroservicesApplication.class, args);
	}

}
