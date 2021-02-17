package com.demo.aws.sqs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AwsSqsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsSqsDemoApplication.class, args);
		log.info("application started...");
	}

}
