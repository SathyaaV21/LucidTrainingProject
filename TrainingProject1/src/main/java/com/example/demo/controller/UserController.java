/**
 * @author Sathyaa
 *
 */
package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

//import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.User;
//import com.example.demo.security.services.UserDetailsImpl;
//import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * API specific for ADMIN to add new user.
	 * @param User user.
	 * @return ResponseEntity stating that the request has been successfully
	 *         initiated.
	 */
	
	/*
	 * @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	 * 
	 * @PostMapping("/user") public String addUser(@RequestBody User user) {
	 * 
	 * 
	 * userService.saveUser(user);
	 * 
	 * return "Added user details"; }
	 */
	
	//add a proper registration service.
	
	
	  
	/**
	 * API To view all the users in the system. 
	 * @return ResponseEntity with information about all the users.
	 */
			  
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_MODERATOR')")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/users")
	public ResponseEntity<?> displayAllUserDetail() {
		System.out.println("displayAllUserDetail started");
		return ResponseEntity.ok(userService.displayAllUserDetail());
	}
	
	

	/**
	 * API specific to Administrator to find a user using ID from the database.
	 * @param user Id
	 * @return ResponseEntity stating that the particular user has been successfully
	 *         deleted.
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMIN')or hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_MODERATOR')")
	@GetMapping("/user/{Id}")
	public ResponseEntity<?> findUserById(@PathVariable String Id) {
		return ResponseEntity.ok(userService.findUser(Id));
	}
	
	
	/**
	 * API specific to Administrator to find a user using username from the database.
	 * @param user Id
	 * @return ResponseEntity stating that the particular user has been successfully
	 *         deleted.
	 */
	
	///set user status to false.and make changes in signin also.
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')or hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_MODERATOR')")
	@DeleteMapping("/user/{Id}")
	public ResponseEntity<?> deleteUser(@PathVariable String Id) {
		return ResponseEntity.ok(userService.deleteUser(Id));
	}
	

	/**
	 * API specific for Administrator to grant requested roles to the appropriate
	 * user.
	 * @param UserName
	 * @return ResponseEntity contains the particular user detail appended with the
	 *         requested role.
	 */
	
	

	
	
	  //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	  
	  @PostMapping("/addroletouser/{userId}/{roleId}") 
	  public ResponseEntity<?>addRoleToUser(@Valid @PathVariable String userId,@PathVariable String roleId) {
		  
		  return ResponseEntity.ok(userService.addRoleToUser(userId,roleId)); }
	 

	/**
	 * API specific for Administrator to remove a role from a particular user.
	 * @param User Id, Role Id
	 * @return ResponseEntity stating that the role is removed successfully from the particular user.
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/deleterolefromuser/{userId}/{roleId}")
	public ResponseEntity<?> deleteRoleFromUser(@Valid @PathVariable String userId,@PathVariable String roleId) {
		return ResponseEntity
				.ok(userService.deleteRoleFromUser(userId,roleId));
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')or hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_MODERATOR')")
	@PostMapping("/updateuser/{Id}")
	public ResponseEntity<?> updateUser(@PathVariable String Id,@RequestBody Map<String,String> update) {
		return userService.updateUser(Id,update);
		}
	}