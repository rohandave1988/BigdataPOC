package com.hbase.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
(scanBasePackages={"com.hbase"})
public class HbaseSpringBootApp {

	public static void main(String[] args) {
		SpringApplication.run(HbaseSpringBootApp.class, args);
	}
}
