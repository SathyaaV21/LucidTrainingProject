
/**
 * @author Sathyaa
 *
 */
package com.example.demo.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.example.demo.model.Role;
import com.example.demo.model.Sequence;

import com.example.demo.model.User;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.RoleRepository;
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
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	RoleService roleService;
	@Autowired
	private SequenceGenService service;
	
	
	
	
	/**
	 * Service to add information about the user
	 * 
	 * @param User newUser.
	 * @return MessageResponse with the details of the user.
	 */
	
	public ResponseEntity<?> saveUser(User user) {
		
		if (userRepository.existsByUsername(user.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
	

		if (userRepository.existsByEmail(user.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		user.setId("USR" + service.getCount(Sequence.getSequenceName5()));
		user.setPassword(encoder.encode(user.getPassword()));
		
		
		user.setIsuserStatusActive(true);
		mongoTemplate.save(user);
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	/**
	 * Service that displays all the users in the application.
	 * @return MessageResponse with information about all the users.
	 */
	public Object displayAllUserDetail(){
		Query query = new Query();
		query.fields().exclude("password");
		List<User> allUsers = mongoTemplate.find(query, User.class);
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
			
			  if(!userRepository.existsById(Id)) return new MessageResponse(Id +
			  "does not exist!");
			
			  Query query = new Query();
				query.addCriteria(Criteria.where("id").is(Id));
				
				User user= mongoTemplate.findOne(query, User.class); 
				user.setIsuserStatusActive(false);
				mongoTemplate.save(user);
			
			return new MessageResponse(Id+ " has been successfully set to inactive status.");
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
	public User findUser(String Id) {
		
		if(!userRepository.existsById(Id)) {
			throw new BadRequestException(Id+ " is not found in the database.");
		}
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(Id));
		query.fields().exclude("password");
		return mongoTemplate.findOne(query, User.class);
	}
	
	/**
	 * Service to add requested roles to a specific user.
	 * 
	 * @param  user id and roleName.
	 * @return UserModel object.
	 */
	
	public User addRoleToUser(String userId, String roleId) {
		
		if(!userRepository.existsById(userId)) {
			throw new BadRequestException(userId+ " is not found in the database.");}
		if(!roleRepository.existsById(roleId)) {
			throw new BadRequestException(roleId+ " is not found in the database.");}
		
		
		
			
			Query query = new Query();
			query.addCriteria(Criteria.where("id").is(userId));
			
			
			Role role = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("id").is(roleId)),
					Role.class);
			
			
			
			role.setIsRolestatusactive(true);
			mongoTemplate.save(role);
			
			
			Update update = new Update().addToSet("roles", role);
			
			return mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(false), User.class);
			
	}
	/**
	 * Service to remove a role from a specific user.
	 * 
	 * @param user id and role id.
	 * @return MessageResponse stating that the role is removed successfully from the particular user.
	 */
	public MessageResponse deleteRoleFromUser(String userId, String roleId) {
		
		if(!userRepository.existsById(userId)) {
			throw new BadRequestException(userId+ " is not found in the database.");}
		if(!roleRepository.existsById(roleId)) {
			throw new BadRequestException(roleId+ " is not found in the database.");}
		
			
			
			Role role = mongoTemplate.findOne(
					new Query().addCriteria(Criteria.where("id").is(roleId)),
					Role.class);
			Query query = Query.query(Criteria.where("id").is(userId));
			Query query2 = Query.query(Criteria.where("$id").is( roleId));
			Update update = new Update().pull("roles", query2);
			mongoTemplate.updateMulti(query, update, User.class);
			
			
			
			
			return new MessageResponse(role.getName() + " Role has been successful removed from the user " + userId);
		
	}

	


	
		
		
		
		
		
	
	
	
	
	}



