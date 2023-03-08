package com.bittlebittle.utils;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LibraryComponent {

    @Bean
    public Gson getGson(){
        return new Gson();
    }

    // 수동으로 Bean 을 Application context에 등록 하려면,
    // Bean 으로 등록하려는 메소드에 @Bean 어노테이션을 붙여주고,
    // @Bean 어노테이션이 존재하는 클래스에 @Configuration 어노테이션을 붙인다.
    // getBean 의라는 bean 메소드의 id 는 메소드 이름이다.


}
