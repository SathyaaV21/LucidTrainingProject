package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.RoleNotFoundException;
import com.example.demo.model.RoleModel;

import com.example.demo.service.RoleService;

@RestController
@RequestMapping("/api/v1")
public class RoleController {

@Autowired
private RoleService roleservice;

@PostMapping("/role")

public String addRole(@RequestBody RoleModel newrole) {
	//Creating a new role
	roleservice.saveRole(newrole);
	
	return "Added Role details";
}
@GetMapping("/role")
public List<RoleModel> getRoles(){
	return roleservice.viewRoles();

}
@GetMapping("/test")
public String test() {
	return "It is Connecting";
}
@DeleteMapping("/role/{Id}")
public String deleteRole(@PathVariable int Id) throws RoleNotFoundException{
	
	roleservice.deleteRole(Id);
	return "role Deleted";
	}
	//return new ResponseEntity<Object>(Id, HttpStatus.OK);
@GetMapping("/role/{Id}")
public List<RoleModel> getRole(@PathVariable int Id){
	
	return roleservice.findRole(Id)	;

}
}
