package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.provider.CustomAuthProvider;

import lombok.RequiredArgsConstructor;

//old,deprecated method
// public class SecurityConfig extends WebSecurityConfigureAdaptor{

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomAuthProvider customAuthProvider;

    @Bean
    public SecurityFilterChain configureFilterChain(HttpSecurity http) throws Exception{
        http
            .csrf().disable()//for example only, do not use on production
			.authorizeHttpRequests((authorize) -> authorize
				.anyRequest().authenticated()
			)
			.httpBasic(Customizer.withDefaults())
			.formLogin(Customizer.withDefaults())
            .authenticationProvider(customAuthProvider);

		return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        //NOT RECOMENDED, FOR EXAMPLE ONLY
        return NoOpPasswordEncoder.getInstance();
    }
    
}