package com.dzink.bas2101.Dzink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@SpringBootApplication
//public class DzinkApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(DzinkApplication.class, args);
//	}

//}


@SpringBootApplication
@RestController
public class DzinkApplication {
	public static void main(String[] args) {
		SpringApplication.run(DzinkApplication.class, args);
	}
	@GetMapping("/")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
}