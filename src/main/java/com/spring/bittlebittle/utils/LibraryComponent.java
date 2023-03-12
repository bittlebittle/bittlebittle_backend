package com.spring.bittlebittle.utils;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LibraryComponent {

    @Bean
    public Gson getGson(){
        return new Gson();
    }

   

}
