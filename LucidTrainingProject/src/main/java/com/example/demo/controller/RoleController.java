package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.RoleModel;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;

@RestController
@RequestMapping("/api/v1")
public class RoleController {
@Autowired
private RoleRepository roleRepo;
@Autowired
private RoleService roleservice;

@PostMapping("/role")

public String addEmp(@RequestBody RoleModel newrole) {
	//Creating a new role
	roleservice.saveRole(newrole);
	
	return "Added Role details";
}
@GetMapping("/role")
public List<RoleModel> getRoles(){
	return roleRepo.findAll();

}
@GetMapping("/test")
public String test() {
	return "It is Connecting";
}
}
