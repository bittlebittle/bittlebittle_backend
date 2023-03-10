package com.spring.bittlebittle.utils1;

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
