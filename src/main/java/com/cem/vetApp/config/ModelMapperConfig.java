package com.cem.vetApp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    // ModelMapper sınıfı için bir bean oluşturuldu.
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
// ModelMapperConfig.java dosyası oluşturuldu ve ModelMapper sınıfı için bir bean oluşturuldu. Bu bean, ModelMapper sınıfının bir örneğini döndürür.