package com.stackroute.moneynmonetary.otpservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OtpserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtpserviceApplication.class, args);
	}

}
