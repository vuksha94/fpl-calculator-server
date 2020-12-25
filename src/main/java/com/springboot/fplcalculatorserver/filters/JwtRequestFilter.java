package com.springboot.fplcalculatorserver.filters;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.fplcalculatorserver.entities.User;
import com.springboot.fplcalculatorserver.security.jwt.JwtUtil;
import com.springboot.fplcalculatorserver.services.UserService;

import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("invoking JwtRequestFilter...");
		final String authorizationHeader = request.getHeader("Authorization");

		System.out.println(authorizationHeader);
		try {
			if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				final String jwtToken = authorizationHeader.substring(7);
				final String username = jwtUtil.extractUsername(jwtToken);

				if(SecurityContextHolder.getContext().getAuthentication() == null) {
					System.out.println("getContext().getAuthentication() = null...");
					UserDetails userDetails = userService.findByEmail(username);
					Optional<User> user = userService.findUserByEmail(username);
					if (jwtUtil.validateToken(jwtToken, user.get())) {
		                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
		                        userDetails, null, userDetails.getAuthorities());
		                usernamePasswordAuthenticationToken
		                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		            }
				}
	        }
		} catch (MalformedJwtException malformedJwtException) {
			System.out.println(malformedJwtException.getMessage());
		}
		
		filterChain.doFilter(request, response);
		
		
		
	}

}
