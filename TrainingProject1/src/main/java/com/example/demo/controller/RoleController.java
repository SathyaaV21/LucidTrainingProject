package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.example.demo.service.RoleService;

@RestController
@RequestMapping("/api/v1")
public class RoleController {

@Autowired
private RoleService roleService;

/**
 * API specific for Administrator to add new role to the application.
 * @return ResponseEntity stating the the new role has been add to the Role collection.
 */
@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MANAGER')")
@PostMapping("/addnewrole")
public ResponseEntity<?> addNewRole(@Valid @RequestBody HashMap<String, String> dataHashMap) {
	return ResponseEntity.ok(roleService.addNewRole(dataHashMap.get("rolename")));
}

/**
 * API specific for Administrator to view all the roles in the system.
 * @return ResponseEntity with information about all the roles.
 */
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@GetMapping("/allroleinfo")
public ResponseEntity<?> displayAllRoleDetail() {
	return ResponseEntity.ok(roleService.displayAllRoleDetail());
}

/**
 * API specific for Administrator to view all active roles in the system.
 * @return ResponseEntity with information about all the active roles.
 */
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@GetMapping("/allactiveroleinfo")
public ResponseEntity<?> displayAllActiveRoleDetail() {
	return ResponseEntity.ok(roleService.displayAllActiveRoleDetail());
}

/**
 * API specific for Administrator to delete a role to the application.
 * @return ResponseEntity stating that the role is successfully set inactive.
 */
@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MANAGER')")
@PostMapping("/deleterole")
public ResponseEntity<?> deleteRole(@Valid @RequestBody HashMap<String, String> dataHashMap) {
	return ResponseEntity.ok(roleService.deleteRole(dataHashMap.get("roleid")));
}

/**
 * API specific for Administrator to update a existing role in the application.
 * @return ResponseEntity stating that the role has been updated successfully.
 */
@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MANAGER')")
@PostMapping("/updaterole")
public ResponseEntity<?> updateRole(@Valid @RequestBody HashMap<String, String> dataHashMap) {
	return ResponseEntity.ok(roleService.updateRole(dataHashMap.get("roleid"), dataHashMap.get("rolename"),
			Boolean.valueOf(dataHashMap.get("rolestatus"))));
}
}
