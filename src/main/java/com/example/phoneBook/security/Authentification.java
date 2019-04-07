package com.example.phoneBook.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;

@Configuration
class Authentification extends GlobalAuthenticationConfigurerAdapter {
    public void init(AuthenticationManagerBuilder builder){
        try {
            builder.inMemoryAuthentication()
                    .withUser("user")
                    .password("pass")
                    .roles("USER");
        }
        catch(java.lang.Exception ex){
            System.out.println(ex.toString());
        }
    }
}

