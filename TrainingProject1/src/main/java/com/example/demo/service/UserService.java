package com.example.demo.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;

import com.example.demo.model.Role;

import com.example.demo.model.UserModel;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.UserRepository;


@Service
public class UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MongoOperations mongoOperations;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	/**
	 * Service to display the information of the user
	 * 
	 * @param username.
	 * @return MessageResponse with the details of the user.
	 */
	
	public List<UserModel> findByUsername(String username) {
		Query query=Query.query(Criteria.where("username").is(username));
		query.fields().exclude("password");
		return mongoTemplate.find(query, UserModel.class);
	}
	
	/**
	 * Service to add new user to the application.
	 * 
	 * @param user name, password and email.
	 * @return 
	 * @return MessageResponse stating that the user has been successfully registered.
	 */
	
	public MessageResponse saveUser(String username, String password, String email) {
		
		if (userRepository.existsByUsername(username)) {
			return new MessageResponse("Error: Username is already in use!");
		}

		if (userRepository.existsByEmail(email)) {
			return new MessageResponse("Error: Email is already in use!");
		}

		// Create new user's account
		UserModel user = new UserModel(username, encoder.encode(email), password);
		
		user.setIsuserStatusActive(true);
		userRepository.save(user);
		return new MessageResponse("User registered successfully!");
	}
		
	/**
	 * Service that displays all the users in the application.
	 * @return MessageResponse with information about all the users.
	 */
	public Object displayAllUserDetail(){
		Query query = new Query();
		query.fields().exclude("password");
		List<UserModel> allUsers = mongoTemplate.find(query, UserModel.class);
		if (allUsers == null)
			return new MessageResponse("No user is available in the system");
		return allUsers;
	}
		
	/**
	 * Service that deletes an user from the user collection.
	 * 
	 * @param user id
	 * @return MessageResponse stating that the particular user has been successfully deleted.
	 */
	public MessageResponse deleteUser(String Id) {
		try {
			/*
			 * if(!userRepository.existsById(Id)) return new MessageResponse(Id +
			 * "does not exist!");
			 */ 
			mongoTemplate.findAndRemove(new Query().addCriteria(Criteria.where("id").is(Id)), UserModel.class);
			return new MessageResponse(Id+ " has been successfully removed from the system.");
		} catch(Exception e) {
			LOGGER.warn(e.getMessage());
			throw new BadRequestException("Request format is wrong!");
		}
	}
	
	/**
	 * Service to display the information of the current user
	 * 
	 * @param user id.
	 * @return MessageResponse with the details of the currently logged in user.
	 */
	public UserModel findUser(String userid) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(userid));
		query.fields().exclude("password");
		return mongoTemplate.findOne(query, UserModel.class);
	}
	
	/**
	 * Service that allows administrator to add requested roles to a specific user.
	 * 
	 * @param  user id and roleName.
	 * @return UserModel object.
	 */
	
	public UserModel addRoleToUser(String userid, String requestedrole) {
		try {
			
			Query query = new Query();
			query.addCriteria(Criteria.where("id").is(userid));
			Role role = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("name").is(requestedrole)),
					Role.class);
			Update update = new Update().addToSet("roles", role);
			
			return mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(false), UserModel.class);
		} catch (Exception e) {
			LOGGER.warn(e.getMessage());
			throw new BadRequestException("Request format is wrong!");
		}
	}
	/**
	 * Service that allows administrator to remove a role from a specific user.
	 * 
	 * @param user id and role id.
	 * @return MessageResponse stating that the role is removed successfully from the particular user.
	 */
	public MessageResponse deleteRoleFromUser(String userid, String roleid) {
		try {
			Role role = mongoTemplate.findOne(
					new Query().addCriteria(Criteria.where("id").is(roleid)),
					Role.class);
			Query query = Query.query(Criteria.where("id").is(userid));
			Query query2 = Query.query(Criteria.where("$id").is(new ObjectId(roleid)));
			Update update = new Update().pull("roles", query2);
			mongoTemplate.updateMulti(query, update, UserModel.class);
			return new MessageResponse(role.getName() + " Role has been successful removed from the user " + userid);
		} catch (Exception e) {
			LOGGER.warn(e.getMessage());
			throw new BadRequestException("Request format is wrong!");
		}
	}
	}



