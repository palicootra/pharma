package com.example.ProjetFInal.configuration;


import com.example.ProjetFInal.filter.JwtFilter;
import com.example.ProjetFInal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurati  extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().disable();

        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/authenticate","/all","/addLot","/lot/addLot","/lot/allLot",
                        "/allLot","/addSortie","/Sortie/addSortie","/Sortie/getByPharmacie",
                        "/Sortie/findAllSortie",
                        "/getstats",
                        "/pharmacie/findAll","/findAll","/addPharma","/pharmacie/addPharma",
                        "/findMedoc","/medicament/findMedoc","/findAllMedoc","/medicament/findAllMedoc",
                        "/createUser","/medicament/addMedoc","/addMedoc","/register",
                        "/medicament/delete",
                        "/pharmacie/delete",
                        "/lot/delete",
                        "/Sortie/delete",
                        "/delete",
                        "/swagger-ui.html","/login",
                        "/pp/oauth/token"
                        )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


    }
}
