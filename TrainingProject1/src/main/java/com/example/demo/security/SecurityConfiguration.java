package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.filter.JwtRequestFilter;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.security.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true)

//@PreAuthorize("hasRole('ADMIN')")---Add similar function to your controllers to give authorization to certain apis!


public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth.userDetailsService(myUserDetailsService);
	}

	
	  @Bean	  
	  @Override public AuthenticationManager authenticationManagerBean() throws
	  Exception { return super.authenticationManagerBean(); }
	 

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable().
				authorizeRequests().antMatchers("/authenticate","/welcome","/subs","/api/v1/**").permitAll().
						anyRequest().authenticated().and().
						exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}
	
	/*
	 * @EnableWebSecurity public class SecurityConfiguration extends
	 * WebSecurityConfigurerAdapter {
	 * 
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { // TODO Auto-generated method stub
	 * 
	 * //Will do this Dynamically using UserDb auth.inMemoryAuthentication()
	 * .withUser("Admin").password("pass").roles("ADMIN").and()
	 * .withUser("Developer").password("password").roles("DEVELOPER").and()
	 * .withUser("User").password("user").roles("USER"); }
	 * 
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.authorizeRequests()
	 * .antMatchers("/api/v1/role","/api/v1/role/{Id}").hasRole("ADMIN")
	 * .antMatchers("/api/v1/user","/api/v1/user/{Id}").hasAnyRole("ADMIN",
	 * "DEVELOPER") .antMatchers("/getstring").hasAnyRole("ADMIN",
	 * "DEVELOPER","USER") .and().formLogin(); } //add an authenticate api instead
	 * of using the form and return a jwt instead of giving the access so that
	 * //session handler is enabled
	 * 
	 * @Bean public PasswordEncoder getPasswordEncoder() { return
	 * NoOpPasswordEncoder.getInstance(); } }
	 */

}