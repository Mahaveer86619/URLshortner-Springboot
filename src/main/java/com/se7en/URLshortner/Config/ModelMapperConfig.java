package com.se7en.URLshortner.Config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configure ModelMapper matching strategy
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Register a custom converter for LocalDateTime
        Converter<String, LocalDateTime> stringToLocalDateTime = new AbstractConverter<String, LocalDateTime>() {
            @Override
            protected LocalDateTime convert(String source) {
                return LocalDateTime.parse(source); // You may need to adjust the date-time format
            }
        };

        modelMapper.addConverter(stringToLocalDateTime);

        return modelMapper;
    }
}
