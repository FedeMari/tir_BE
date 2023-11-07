package com.tir.ocinio.filters;

import java.io.IOException;
import java.util.Collections;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tir.ocinio.service.DipendenteService;
import com.tir.ocinio.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private DipendenteService dipService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("Headers ricevuti: " + Collections.list(request.getHeaderNames()));

		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String email;
		
		//controlliamo che l'header Authorization non sia vuoto e che contenga la stringa Bearer, se la condizione si verifica la richiesta viene filtrata
		if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		//in caso in cui la stringa non sia vuota e inizi con Bearer: bisogna splittarlo al settimo carattere per prendere solo la JWT
		jwt = authHeader.substring(7);
		email = jwtService.extractUsername(jwt);
		if(StringUtils.isNotEmpty(email) && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails user = dipService.loadUserByUsername(email);
			
			if(jwtService.isTokenValid(jwt, user)) {
				//stiamo creando un context vuoto per ospitare l'utente singolo e lo stiamo facendo in modo esplicito in questa istruzione per evitare
				//la concorrenza di richieste se lo facessimo in modo automatico
				SecurityContext secCon = SecurityContextHolder.createEmptyContext();
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				secCon.setAuthentication(token);
				System.out.println(secCon);
				SecurityContextHolder.setContext(secCon);
			}
		}
		filterChain.doFilter(request, response);
	}
	
	

}
