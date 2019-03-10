package com.bootcamp.bootcamp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated() // jesli nie ma czegos bardziej szczegolowego np antMatchers to wywoalo anyRequest
//                .and()
//                .logout().logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//                .and()
//                .formLogin();
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin","/admin/**")
                .authenticated()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .formLogin();
    }
}
