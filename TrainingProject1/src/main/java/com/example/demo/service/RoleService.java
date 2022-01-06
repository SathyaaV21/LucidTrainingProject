package com.example.demo.service;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import com.example.demo.exception.RoleNotFoundException;
import com.example.demo.model.RoleModel;


//import com.example.demo.repository.RoleRepository;
@Service

public class RoleService {
@Autowired
MongoTemplate mongotemplate;
	
/*
 * @Autowired private RoleRepository roleRepo;
 */

public String saveRole(RoleModel newrole) {
	//roleRepo.save(newrole);
	mongotemplate.save(newrole);
	return "In Service";
}
public List<RoleModel>viewRoles(){
	return mongotemplate.findAll(RoleModel.class);
	//return roleRepo.findAll();
}

public void deleteRole(int Id) throws RoleNotFoundException{
	//RoleModel rolemodel=mongotemplate.findById(Id, RoleModel.class);
	//Optional<RoleModel> rolemodel= roleRepo.findById(Id);
	//if (!rolemodel.isEmpty()) {
		Query query=Query.query(Criteria.where("Id").is(Id));
		mongotemplate.findAndRemove(query, RoleModel.class);
		//roleRepo.deleteById(Id);
		//}
	//else {
	//throw new RoleNotFoundException("ID is not present in Db "+Id);
	}
public List<RoleModel> findRole(int Id) {
	Query query=Query.query(Criteria.where("Id").is(Id));
	return mongotemplate.find(query, RoleModel.class);
}
}

