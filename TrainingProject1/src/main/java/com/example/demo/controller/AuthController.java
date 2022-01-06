package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AuthenticationRequest;
import com.example.demo.model.AuthenticationResponse;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenUtil;

@RestController
public class AuthController {
	
	@Autowired
	private UserDetailsService myUserDetailsService;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/subs")
	private ResponseEntity<?> subscribeClient(@RequestBody AuthenticationRequest authenticationRequest){
		String username= authenticationRequest.getUsername();
		String password= authenticationRequest.getPassword();
		UserModel userModel=new UserModel();
		userModel.setUsername(username);
		userModel.setPassword(password);
		
		userRepository.save(userModel);
		//add try catch block here	
		return ResponseEntity.ok(new AuthenticationResponse("Succesful Subscription for client"+username));
	}
	
	
	@GetMapping("/welcome")
	public String hello() {
		return "Hello welcome";
	}
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = myUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
}
