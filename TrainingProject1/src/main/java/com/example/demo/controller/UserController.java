package com.example.demo.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * API to display the Logged in user's details.
	 * 
	 * @return ResponseEntity with the details of the currently logged in user
	 */
	/*
	 * @GetMapping("/userinfo") public ResponseEntity<?> displayUserDetail() {
	 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 * UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal(); return
	 * ResponseEntity.ok(userService.displayUserDetail(user.getId())); }
	 * 
	 *//**
		 * API to update the details of the currently logged in user.
		 * 
		 * @return ResponseEntity stating that the update operation is successful.
		 *//*
			 * @PutMapping("/updateuser") public ResponseEntity<?>
			 * updateEmployee(@RequestBody UserModel user) { Authentication auth =
			 * SecurityContextHolder.getContext().getAuthentication(); UserDetailsImpl userN
			 * = (UserDetailsImpl) auth.getPrincipal(); String username =
			 * userN.getUsername(); return
			 * ResponseEntity.ok(userService.updateUserDetails(username, user)); }
			 */
	/**
	 * API for registered users to request roles from the Administrator.
	 * 
	 * @return ResponseEntity stating that the request has been successfully
	 *         initiated.
	 */
	/*
	 * @PreAuthorize("#pendingRequest.getUserid() == authentication.principal.id")
	 * 
	 * @PostMapping("/requestrole") public ResponseEntity<?>
	 * requestRole(@Valid @RequestBody PendingRequest pendingRequest) { return
	 * ResponseEntity .ok(userService.addRoletoUser(pendingRequest.getUserid(),
	 * pendingRequest.getRequestedroleid())); }
	 */

	/**
	 * API specific for Administrator to view all the users in the system.
	 * 
	 * @return ResponseEntity with information about all the users.
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/alluserinfo")
	public ResponseEntity<?> displayAllUserDetail() {
		return ResponseEntity.ok(userService.displayAllUserDetail());
	}

	/**
	 * API specific for Administrator to delete an user from the system.
	 * 
	 * @return ResponseEntity stating that the particular user has been successfully
	 *         deleted.
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/deleteuser")
	public ResponseEntity<?> deleteUser(@Valid @RequestBody HashMap<String, String> dataHashMap) {
		return ResponseEntity.ok(userService.deleteUser(dataHashMap.get("userid")));
	}

	/**
	 * API specific for Administrator to grant requested roles to the appropriate
	 * user.
	 * 
	 * @return ResponseEntity contains the particular user detail appended with the
	 *         requested role.
	 */
	/*
	 * @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	 * 
	 * @PostMapping("/addroletouser") public ResponseEntity<?>
	 * addRoleToUser(@Valid @RequestBody HashMap<String, String> dataHashMap) {
	 * return ResponseEntity.ok(userService.addRoleToUser(dataHashMap.get("id"),
	 * dataHashMap.get("role"), dataHashMap.get("requestedroleid"))); }
	 */

	/**
	 * API specific for Administrator to remove a role from a particular user.
	 * @return ResponseEntity stating that the role is removed successfully from the particular user.
	 */
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/deleterolefromuser")
	public ResponseEntity<?> deleteRoleFromUser(@Valid @RequestBody HashMap<String, String> dataHashMap) {
		return ResponseEntity
				.ok(userService.deleteRoleFromUser(dataHashMap.get("userid"), dataHashMap.get("roleid")));
	}}