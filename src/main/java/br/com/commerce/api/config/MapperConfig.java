package br.com.commerce.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;


@Component
public class MapperConfig {
    
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
