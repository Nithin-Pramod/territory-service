package com.territoryservice.territoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TerritoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TerritoryServiceApplication.class, args);
	}

}
