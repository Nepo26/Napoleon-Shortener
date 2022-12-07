package com.nepo.napoleon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Configuration
@AllArgsConstructor
public class FeignClientConfig {
    private final ObjectMapper mapper;

    @Bean
    ErrorDecoder errorDecoder() {
        return (s, response) -> new RuntimeException();
    }
}
