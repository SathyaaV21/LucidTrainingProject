package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


import com.example.demo.model.UserModel;


@Service
public class UserService {
	@Autowired
	MongoTemplate mongotemplate;
	
	/**
	 * Service to display the information of the user
	 * 
	 * @param username.
	 * @return MessageResponse with the details of the user.
	 */
	
	public List<UserModel> findByUsername(String username) {
		Query query=Query.query(Criteria.where("username").is(username));
		query.fields().exclude("password");
		return mongotemplate.find(query, UserModel.class);
	}
	
	/**
	 * Service to add new user to the application.
	 * 
	 * @param usermodel newuser(json object).
	 * @return MessageResponse stating that the user has been successfully registered.
	 */
	
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
	
	public UserModel updateUserDetails(String username, UserModel user) {
		Query query = Query.query(Criteria.where("username").is(username));
		Update update = new Update();
		update.set("Roles", user.getRoles());
		//can add multiple update statements in future
		mongotemplate.updateMulti(query, update, UserModel.class);
		return user;
	}
}


