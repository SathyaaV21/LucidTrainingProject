package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.RoleModel;
import com.example.demo.repository.RoleRepository;

public class RoleService {
	
@Autowired
private RoleRepository roleRepo;
public RoleModel saveRole(RoleModel newrole) {
	return roleRepo.save(newrole);
}
public List<RoleModel>viewRoles(){
	
	return roleRepo.findAll();
}
}
