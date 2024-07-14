package com.microservices.services.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.microservices.services.helper.PageDeserializer;
import com.microservices.services.helper.PageSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        // Cast the serializer to the appropriate type
        module.addSerializer((Class<Page<?>>) (Class<?>) Page.class, new PageSerializer());
        module.addDeserializer((Class<Page<?>>) (Class<?>) Page.class, new PageDeserializer());
        mapper.registerModule(module);
        return mapper;
    }
}

