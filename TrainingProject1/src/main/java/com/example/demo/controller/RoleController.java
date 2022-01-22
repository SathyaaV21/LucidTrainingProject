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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;

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
@PostMapping("/role")
public ResponseEntity<?> addNewRole(@Valid @RequestBody HashMap<String, String> dataHashMap) {
	return ResponseEntity.ok(roleService.addNewRole(dataHashMap.get("rolename")));
}

/**
 * API To view all the roles in the system.
 * @return ResponseEntity with information about all the roles.
 */
@PreAuthorize("hasAuthority('ROLE_ADMIN')or hasAuthority('ROLE_MANAGER')")
@GetMapping("/roles")
public ResponseEntity<?> displayAllRoleDetail() {
	return ResponseEntity.ok(roleService.displayAllRoleDetail());
}

/**
 * API To view all active roles in the system.
 * @return ResponseEntity with information about all the active roles.
 */
@PreAuthorize("hasAuthority('ROLE_ADMIN')or hasAuthority('ROLE_MANAGER')")

@GetMapping("/activeroles")
public ResponseEntity<?> displayAllActiveRoleDetail() {
	return ResponseEntity.ok(roleService.displayAllActiveRoleDetail());
}

/**
 * API To delete a role to the application.
 * @return ResponseEntity stating that the role is successfully removed.
 */

@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@DeleteMapping("/role/{Id}")
public ResponseEntity<?> deleteRole(@Valid @PathVariable String Id) {
	return ResponseEntity.ok(roleService.deleteRole(Id));
}

/**
 * API To view a role using role id..
 * @return The role object with the specified Id is shown .
 */
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@GetMapping("/role/{Id}")
public Role getRole(@Valid @PathVariable String Id) {
	return roleService.findById(Id);
}

}
