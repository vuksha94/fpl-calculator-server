package com.springboot.fplcalculatorserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.fplcalculatorserver.filters.JwtRequestFilter;


@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	private UserDetailsService myUserDetailsService;
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override protected void configure(HttpSecurity http) throws Exception {
		  
		http.csrf().disable()
		  	.authorizeRequests()
		  	 	.antMatchers("/admin").hasRole("ADMIN")
		  	 	.antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // because of cors options preflight request
		  	 	//.antMatchers("/user").hasRole("USER")
		  	 	//.antMatchers("/**").permitAll()
			  	.antMatchers("/login", "/register").permitAll()
			  	.anyRequest().authenticated()
			  	.and().sessionManagement()
			  	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
			  
			/*
			 * http.authorizeRequests() .antMatchers("/admin").hasRole("ADMIN")
			 * .antMatchers("/user").hasAnyRole("USER", "ADMIN")
			 * .antMatchers("/").permitAll().and().formLogin();
			 */
	  }
	 

	
}
