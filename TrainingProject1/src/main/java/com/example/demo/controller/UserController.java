/**
 * @author Sathyaa
 *
 */
package com.example.demo.controller;

import java.util.HashMap;

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
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_MODERATOR')")
	@PostMapping("/adduser")
	public String addUser(@RequestBody User user) {
		//empservice.setId(service.getSequenceNumber("1"));
		
		userService.saveUser(user);
		
		return "Added user details";
	}
	  
	/**
	 * API To view all the users in the system. 
	 * @return ResponseEntity with information about all the users.
	 */
			  
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_MODERATOR')")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/alluser")
	public ResponseEntity<?> displayAllUserDetail() {
		System.out.println("displayAllUserDetail started");
		return ResponseEntity.ok(userService.displayAllUserDetail());
	}

	/**
	 * API specific to Administrator to delete a user from the database.
	 * @param user Id
	 * @return ResponseEntity stating that the particular user has been successfully
	 *         deleted.
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/user/{name}")
	public ResponseEntity<?> deleteUser(@PathVariable String name) {
		return ResponseEntity.ok(userService.deleteUser(name));
	}

	/**
	 * API specific for Administrator to grant requested roles to the appropriate
	 * user.
	 * @param UserName
	 * @return ResponseEntity contains the particular user detail appended with the
	 *         requested role.
	 */
	
	  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	  
	  @PostMapping("/addroletouser") public ResponseEntity<?>
	  addRoleToUser(@Valid @RequestBody HashMap<String, String> dataHashMap) {
	  return ResponseEntity.ok(userService.addRoleToUser(dataHashMap.get("id"),
	  dataHashMap.get("rolename"))); }
	 

	/**
	 * API specific for Administrator to remove a role from a particular user.
	 * @param User Id, Role Id
	 * @return ResponseEntity stating that the role is removed successfully from the particular user.
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/deleterolefromuser")
	public ResponseEntity<?> deleteRoleFromUser(@Valid @RequestBody HashMap<String, String> dataHashMap) {
		return ResponseEntity
				.ok(userService.deleteRoleFromUser(dataHashMap.get("userid"), dataHashMap.get("roleid")));
	}}