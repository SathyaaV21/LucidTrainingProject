package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserModel;


@Service
public class UserService {
	@Autowired
	MongoTemplate mongotemplate;
	public List<UserModel> findByUsername(String username) {
		Query query=Query.query(Criteria.where("username").is(username));
		
		return mongotemplate.find(query, UserModel.class);
	}
	public void saveUser(UserModel newuser) {
		
		mongotemplate.save(newuser);
		
	}
	public List<UserModel>viewUsers(){
		return mongotemplate.findAll(UserModel.class);
		
	}

	public void deleteUser(int Id) {
		
			Query query=Query.query(Criteria.where("Id").is(Id));
			mongotemplate.findAndRemove(query, UserModel.class);
			
	}
	public List<UserModel> findUser(int Id) {
		Query query=Query.query(Criteria.where("Id").is(Id));
		return mongotemplate.find(query, UserModel.class);
	}
	
}


