package com.demo;

import org.springframework.boot.SpringApplication;
import reactor.blockhound.BlockHound;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

/*
 * if we're using a Spring Boot project and have a spring-data-* or spring-tx dependencies on the classpath, then 
 * transaction management will be enabled by default.
 * @EnableTransactionManagement
 */
@Slf4j
@SpringBootApplication
public class SpringWebclientCustomerDemoApplication {

	static {
        BlockHound.install();
    }
	public static void main(String[] args) {
		SpringApplication.run(SpringWebclientCustomerDemoApplication.class, args);
		log.info("Spring Web Client Customer Demo application started ......");
	}

}
