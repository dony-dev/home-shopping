package com.example.homeshopping;

import com.example.homeshopping.swagger.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class HomeShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeShoppingApplication.class, args);
	}

}
