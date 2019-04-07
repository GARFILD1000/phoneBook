package com.example.phoneBook.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;


@Configuration
@EnableWebMvcSecurity
class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    public void configure(HttpSecurity http){
        try {
            http.authorizeRequests()
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()) //for static resources
                    .permitAll()
                    .antMatchers("/","/legal")
                    .permitAll()
                    .anyRequest()
                    .authenticated();

            http.formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll();
        }
        catch(java.lang.Exception ex){
            System.out.println(ex.toString());
        }
    }

}

/*
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/legal").permitAll();
    }
}
*/
