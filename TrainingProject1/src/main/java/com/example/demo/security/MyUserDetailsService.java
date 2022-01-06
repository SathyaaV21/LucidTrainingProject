package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserModel;
//import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;



	@Service
	public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException  {
		 
		List<UserModel> userList=userService.findByUsername(username); 
		
		if(userList.size() !=1) {
			 return null; 
		}
		
		 UserModel user = userList.get(0);
		 
		 String name= user.getUsername(); 
		 
		 String pwd=user.getPassword();
		 
		 return new User(name,pwd,new ArrayList<>());
			  }
	}
