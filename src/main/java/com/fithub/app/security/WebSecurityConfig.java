package com.fithub.app.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fithub.app.security.jwt.AuthEntryPointJwt;
import com.fithub.app.security.jwt.AuthTokenFilter;
import com.fithub.app.services.UsuarioService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final AuthTokenFilter authTokenFilter;
	private final AuthenticationProvider authenticationProvider;


	/**
	 * Contiene la cadena de filtros que se trabajaran
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf-> csrf.disable())
				.authorizeHttpRequests(authRequest ->
						authRequest
								.requestMatchers("/api/auth/**").permitAll()
								.requestMatchers("api/v1/auth/**").permitAll()
								.anyRequest().authenticated()
				)
				.sessionManagement(sessionManager->
						sessionManager
								.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(authTokenFilter,UsernamePasswordAuthenticationFilter.class)
				.build();
	}

}
