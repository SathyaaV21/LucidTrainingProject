/**
 * @author Sathyaa
 *
 */
package com.example.demo.controller;

import java.util.HashMap;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;



import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.example.demo.service.RoleService;


/**
 * All the APIs are accessible only by ROLE_ADMIN
 *
 */


@RestController
@RequestMapping("/api/v1/")
public class RoleController {

@Autowired
private RoleService roleService;

/**
 * API To add new role to the application.
 * @param "rolename":"someRoleName" (HashMap)
 * @return ResponseEntity stating the the new role has been add to the Role collection.
 */
@PreAuthorize("hasAuthority('ROLE_ADMIN') ")
@PostMapping("/addnewrole")
public ResponseEntity<?> addNewRole(@Valid @RequestBody HashMap<String, String> dataHashMap) {
	return ResponseEntity.ok(roleService.addNewRole(dataHashMap.get("rolename")));
}

/**
 * API To view all the roles in the system.
 * @return ResponseEntity with information about all the roles.
 */
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@GetMapping("/allroles")
public ResponseEntity<?> displayAllRoleDetail() {
	return ResponseEntity.ok(roleService.displayAllRoleDetail());
}

/**
 * API To view all active roles in the system.
 * @return ResponseEntity with information about all the active roles.
 */
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@GetMapping("/allactiveroles")
public ResponseEntity<?> displayAllActiveRoleDetail() {
	return ResponseEntity.ok(roleService.displayAllActiveRoleDetail());
}

/**
 * API To delete a role to the application.
 * @return ResponseEntity stating that the role is successfully set inactive.
 */
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@PostMapping("/deleterole")
public ResponseEntity<?> deleteRole(@Valid @RequestBody HashMap<String, String> dataHashMap) {
	return ResponseEntity.ok(roleService.deleteRole(dataHashMap.get("roleid")));
}

/**
 * API To update a existing role in the application.
 * @param "roleId":"","roleName":"","roleStatus":""
 * @return ResponseEntity stating that the role has been updated successfully.
 */
@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MANAGER')")
@PostMapping("/updaterole")
public ResponseEntity<?> updateRole(@Valid @RequestBody HashMap<String, String> dataHashMap) {
	return ResponseEntity.ok(roleService.updateRole(dataHashMap.get("roleId"), dataHashMap.get("roleName"),
			Boolean.valueOf(dataHashMap.get("roleStatus"))));
}
}
