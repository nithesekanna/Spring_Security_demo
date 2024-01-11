package com.example.Security_only.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.Security_only.service.UserInfoUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    UserDetailsService UserDetailsService() {
//		UserDetails admin = User.withUsername("Nithese").password(encoder.encode("Nithese")).roles("ADMIN").build();
//		UserDetails user = User.withUsername("Raj").password(encoder.encode("Raj")).roles("USER").build();
//		return new InMemoryUserDetailsManager(admin,user);
    	return new UserInfoUserDetailsService();
		}

    @Bean
    PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((auth) -> auth.requestMatchers("/control/get","/control/newUser").permitAll().requestMatchers("/control/**").authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(formLogin -> formLogin.loginProcessingUrl("/login"));
			return http.build();	
	}
    
    @Bean
    AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
    	dao.setUserDetailsService(UserDetailsService());
    	dao.setPasswordEncoder(PasswordEncoder());
    	return dao;
    }
	

}
