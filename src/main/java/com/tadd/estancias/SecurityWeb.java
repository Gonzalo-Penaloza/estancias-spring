package com.tadd.estancias;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityWeb {
	
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
        		.authorizeHttpRequests(authRequest -> 
        								authRequest
        								.requestMatchers("/index").permitAll()
        								.requestMatchers("/casa/**").authenticated()
        								.requestMatchers("/familia/**").authenticated()
        								.requestMatchers("/estancia/**").authenticated()
        								.requestMatchers("/css/*","/js/*","/img/*","/**").permitAll()   
        								.anyRequest().authenticated())
        		.formLogin(form -> 
        					form
        						.loginPage("/login")
        		                .loginProcessingUrl("/logincheck")
        		                .usernameParameter("email")
        		                .passwordParameter("password")
        		                .defaultSuccessUrl("/home")
        		                .permitAll())	
        		.logout(logout -> 
        				 logout
        				 		.logoutUrl("/logout") // URL para realizar el logout
        				 		.logoutSuccessUrl("/login?logout")) // URL de redirección después de hacer logout       				 	    				
        		.csrf(csrf -> 
        				csrf.disable())
        		.build();
    }

}
