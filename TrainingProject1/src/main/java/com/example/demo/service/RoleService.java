package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.RoleModel;
import com.example.demo.repository.RoleRepository;
@Service
public class RoleService {
	
@Autowired
private RoleRepository roleRepo;
public String saveRole(RoleModel newrole) {
	roleRepo.save(newrole);
	return "In Service";
}
public List<RoleModel>viewRoles(){
	
	return roleRepo.findAll();
}
}
