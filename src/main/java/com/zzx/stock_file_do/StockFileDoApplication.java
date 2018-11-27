package com.zzx.stock_file_do;

import com.zzx.stock_file_do.controller.StockController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration
@EnableScheduling
@MapperScan(basePackages = "com.zzx.stock_file_do.dao")
public class StockFileDoApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockFileDoApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
