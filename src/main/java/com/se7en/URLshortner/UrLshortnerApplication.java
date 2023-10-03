package com.se7en.URLshortner;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UrLshortnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrLshortnerApplication.class, args);
	}

	// created a config for this
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
