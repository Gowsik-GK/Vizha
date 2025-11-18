package com.vizha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				// Disable CSRF for APIs (useful when using Postman, Angular, etc.)
				.csrf(csrf -> csrf.disable())

				// Authorization rules
				.authorizeHttpRequests(auth -> auth
						// Public APIs — no authentication required
						.requestMatchers("/user/**", "/actuator/health", "/swagger-ui/**").permitAll()

						// All other APIs require authentication
						.anyRequest().authenticated())

				// Optional: enable basic auth for testing (can be removed later)
				.httpBasic(httpBasic -> httpBasic.disable())

				// Disable default Spring login form
				.formLogin(form -> form.disable());

		return http.build();
	}
}
