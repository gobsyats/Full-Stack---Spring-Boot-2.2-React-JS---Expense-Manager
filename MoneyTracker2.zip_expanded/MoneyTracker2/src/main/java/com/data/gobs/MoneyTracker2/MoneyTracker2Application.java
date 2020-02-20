package com.data.gobs.MoneyTracker2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.data.gobs.MoneyTracker2"})
public class MoneyTracker2Application {

	public static void main(String[] args) {
		SpringApplication.run(MoneyTracker2Application.class, args);
	}

}
