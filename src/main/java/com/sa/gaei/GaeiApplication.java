package com.sa.gaei;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@PropertySource("file:${localPropertiesPath}")
@SpringBootApplication
public class GaeiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaeiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper;
	}

}
