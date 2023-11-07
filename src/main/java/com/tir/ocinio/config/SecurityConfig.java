package com.tir.ocinio.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.tir.ocinio.enumeration.Anzianita;
import com.tir.ocinio.filters.JwtAuthenticationFilter;
import com.tir.ocinio.service.DipendenteService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//non estende ne implementa alcuna classe o interfaccia
	
	//necessaria dependy injection del filtro per autenticare richiesta
	@Autowired
	private JwtAuthenticationFilter auth; 
	
	@Autowired
	private DipendenteService dipService;
	
	//
	@Bean
	SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
		//Cross Site Request Forgery -- attacco hacker che manda una richiesta con tutti 
		//i dati, che però sta accedendo per conto di un altro pc e quindi disabilitandola evitiamo questo
		
		http.cors(cors-> {
			try {
				http.csrf(AbstractHttpConfigurer::disable)
						.authorizeHttpRequests( request -> 
						request				
							.requestMatchers("api/auth/**").permitAll() //permettiamo tutte le richieste con tale path url
							.requestMatchers("api/dipendente/confirm/**").permitAll()
							.requestMatchers("/api/task/**").hasAnyAuthority(Anzianita.SENIOR.nome)
							.requestMatchers("/api/consuntivo/**").permitAll()
							.requestMatchers("/api/**").hasAnyAuthority(Anzianita.HR.nome)
							.anyRequest().authenticated());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		})
		.sessionManagement(manager -> 
			manager
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authProvider())
			.addFilterBefore(auth, UsernamePasswordAuthenticationFilter.class);
		return http.build();		
	}
	
	@Bean
	AuthenticationProvider authProvider () {
		var authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(dipService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
		
	}
	
	@Bean
	AuthenticationManager authManager (AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("*"));
	    configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE"));
	    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
	    configuration.setExposedHeaders(Arrays.asList("Authorization"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}


}
