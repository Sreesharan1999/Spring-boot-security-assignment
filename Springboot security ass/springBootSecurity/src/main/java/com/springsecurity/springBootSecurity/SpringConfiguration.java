package com.springsecurity.springBootSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.springsecurity.springBootSecurity.pojo.AppUserService;


@Configuration
@EnableWebMvc
public class SpringConfiguration {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.csrf().disable().authorizeRequests().requestMatchers("/**").authenticated().and().formLogin();
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new AppUserService();
	}
	
	//for db - We are going to encrypt using BCrypt method
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	//for db - defintion how the authertication must happen
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setPasswordEncoder(encoder());
		
		provider.setUserDetailsService(userDetailsService());
		
		return provider;
		
	}



}
