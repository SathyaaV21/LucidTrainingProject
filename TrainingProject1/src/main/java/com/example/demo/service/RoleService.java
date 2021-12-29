package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.stereotype.Service;


import com.example.demo.exception.RoleNotFoundException;
import com.example.demo.model.RoleModel;
import com.example.demo.repository.RoleRepository;
@Service

public class RoleService {

MongoTemplate mongotemplate;
	
@Autowired
private RoleRepository roleRepo;
public String saveRole(RoleModel newrole) {
	roleRepo.save(newrole);
	return "In Service";
}
public List<RoleModel>viewRoles(){
	
	return roleRepo.findAll();
}

public void deleteRole(int Id) throws RoleNotFoundException{
	
	Optional<RoleModel> rolemodel= roleRepo.findById(Id);
	if (!rolemodel.isEmpty()) {
		roleRepo.deleteById(Id);
		}
	else {
	throw new RoleNotFoundException("ID is not present in Db "+Id);
}}
}
