package com.example.democonvojdbc;

import com.github.mhewedy.convo.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class Config {

    @Bean
    public IdGenerator idGenerator() {
        return () -> UUID.randomUUID().toString();   // could be the trace id or correlation id or request id, etc ...
    }
}
