/**
 * @author Sathyaa
 *
 */
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.filter.AuthEntryPointJwt;
import com.example.demo.security.filter.AuthTokenFilter;
import com.example.demo.security.services.UserDetailsServiceImpl;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/api/v1/auth/**").permitAll()
			.anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
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

	
	
	//"/authenticate","/welcome","/subs","/api/v1/**"
