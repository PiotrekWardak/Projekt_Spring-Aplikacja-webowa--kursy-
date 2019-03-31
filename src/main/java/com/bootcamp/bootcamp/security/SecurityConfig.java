package com.bootcamp.bootcamp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        //adminow, oraz userow zapisujemy w jednej bazie, roznia sie jedynie polem ROLA. w zwiazku z tym gdy uzytkownik poda login i haslo to nastepuje porownanie wartosci wprowadzonych z tymi w abr
        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT email, password,1 FROM user WHERE email=?") //tam gdzie 1 to mozna sprawdzac kolumne active. 1 albo cos musi byc bo metoda wymaga 3 parametrow
                .authoritiesByUsernameQuery("Select u.email, r.role FROM user u inner join role r "+
                "on r.id=u.role_id WHERE u.email=?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);

    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/").permitAll();
////                .anyRequest().authenticated() // jesli nie ma czegos bardziej szczegolowego np antMatchers to wywoalo anyRequest
//                .and()
//                .logout().logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//                .and()
//                .formLogin();
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin","/admin/**").hasAuthority("admin")//.authenticated()
                .antMatchers("/user","/user/**").hasAuthority("user")//.authenticated()
                .antMatchers("/trainer","/trainer/**").hasAuthority("trainer")//.authenticated()
                .and()
                .logout()
                //    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
                .and()
                .formLogin()
                    .loginPage("/logowanie")
                    .defaultSuccessUrl("/");
////          .usernameParameter("username")
////                .passwordParameter("password")//jakbysmy dali w formularzu inne nazwy niz domyslne to tutaj wpisujemy te zmienione nazwy pol
    }
}


