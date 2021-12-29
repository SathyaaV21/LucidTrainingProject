package com.example.demo.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;


@RestController
//@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserRepository userrepo;
	
	
	@GetMapping("/getstring")
	public String helloWorld() {
		System.out.println("Hello!!!! ");
		return"Hey bud!";
	}
	
	@PostMapping("/user")
	public String addUser(@RequestBody UserModel user) {
		//empservice.setId(service.getSequenceNumber("1"));
		
		userrepo.save(user);
		
		return "Added user details";
	}
	
	
	@GetMapping("/user")
	public List<UserModel> getUsers(){
		return userrepo.findAll();
	}
	@GetMapping("/user/{Id}")
	public Optional<UserModel> getUser(@PathVariable int Id){
		
		return userrepo.findById(Id);
	}
	@DeleteMapping("/user/{Id}")
	public String deleteUser(@PathVariable int Id){
		userrepo.deleteById(Id);
		return "User Deleted";
	}
}