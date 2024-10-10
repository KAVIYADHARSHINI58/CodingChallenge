package com.springboot.book_app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.book_app.service.MyUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
    private JwtRequestFilter jwtRequestFilter;
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(auth -> auth
                       .requestMatchers("/auth/token").permitAll()
                       .requestMatchers("/auth/signup").permitAll()
                       .requestMatchers("/admin/hello").hasRole("ADMIN")
                       .requestMatchers("/user/hello").hasAnyRole("ADMIN")
                       
                       .requestMatchers("/book/add").permitAll()
                       .requestMatchers("/book/find/{id}").permitAll()
                       .requestMatchers("/book/find/all").permitAll()
                       .requestMatchers("/book/delete/{id}").permitAll()
                       .requestMatchers("/book/edit/{id}").permitAll()
                       .requestMatchers("/book/categories").permitAll() 
                       .requestMatchers("/book/description/{id}").permitAll() 
                      
                       
                       .anyRequest().authenticated()
               )
               .sessionManagement(session -> session
                       .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               );

       http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

       return http.build();
   }
	
	@Bean
    DaoAuthenticationProvider authenticationProvider(MyUserDetailsService myUserDetailsService, PasswordEncoder passwordEncoder) {
       DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       authProvider.setUserDetailsService(myUserDetailsService);
       authProvider.setPasswordEncoder(getEncoder());
       return authProvider;
   }
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
   }
	
	@Bean
	PasswordEncoder getEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
}

/*
 *    PasswordEncoder
 *          |
 * BcryptPasswordEncoder
 */
