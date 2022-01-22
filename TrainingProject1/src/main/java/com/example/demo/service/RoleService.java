/**
 * @author SATHYAA
 *
 */
package com.example.demo.service;


import java.util.List;
//import java.util.Optional;
//import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;

import com.example.demo.model.Role;
import com.example.demo.model.Sequence;
import com.example.demo.model.User;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;



@Service

public class RoleService {
@Autowired
MongoTemplate mongoTemplate;

@Autowired
RoleRepository roleRepository;
@Autowired
UserRepository userRepository;

@Autowired
private SequenceGenService service;
	

private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);



/**
 * Service that allows administrator to add new role to the application,
 * 
 * @param role name.
 * @return MessageResponse stating the the new role has been add to the Role collection.
 */
public MessageResponse addNewRole(String rolename) {
	try {
		if (roleRepository.existsByName("ROLE_" + rolename.toUpperCase())) {
			return new MessageResponse("Error: Role is already in use!");
		}
		Role role = new Role("ROLE_" + rolename.toUpperCase());
		//setRoleID
		
		//role.setIsRolestatusactive(true);
		role.setId("ROLE" + service.getCount(Sequence.getSequenceName4()));
		mongoTemplate.save(role);
		return new MessageResponse("Role added successfully");
	} catch (Exception e) {
		LOGGER.warn(e.getMessage());
		throw new BadRequestException("Request format is wrong!");
	}
}

/**
 * Service that allows administrator to delete role from the application.
 * This Deletes the role from the user also.
 * @param role id.
 * @return MessageResponse stating that the role is successfully set inactive.
 */
public MessageResponse deleteRole(String roleid) {
	if (!roleRepository.existsById(roleid)) {
		return new MessageResponse("Error: Role is not available");
	}
	Query query = Query.query(Criteria.where("_id").is(roleid));
	Role role = mongoTemplate.findOne(query, Role.class);
	System.out.println(role);
	
	Query query3 = Query.query(Criteria.where("name").is(role.getName()));
	
	Update update = new Update().pull("roles", query3);
	mongoTemplate.updateMulti( new Query(), update, User.class );
	
	
	  
	  mongoTemplate.findAndRemove(query, Role.class);
	  
	  
	 	
	
	
	return new MessageResponse("Role is removed");
}



/**
 * Service that allows administrator to delete role from the application.
 * @return  List<Role>.
 */
public List<Role> displayAllRoleDetail() {
	try {
		return mongoTemplate.findAll(Role.class);
	} catch (Exception e) {
		LOGGER.warn(e.getMessage());
		throw new BadRequestException("Request format is wrong!");
	}
}

/**
 * Service that allows administrator to delete role from the application.
 * @return List<Role>.
 */
public List<Role> displayAllActiveRoleDetail() {
	try {
		return mongoTemplate.find(new Query().addCriteria(Criteria.where("isrolestatusactive").is(true)),
				Role.class);
	} catch (Exception e) {
		LOGGER.warn(e.getMessage());
		throw new BadRequestException("Request format is wrong!");
	}
}

public Role findById(String Id) {
	if (!roleRepository.existsById(Id)) {
		throw new BadRequestException(Id+ " is not found in the database.");
	}
	
	Query query = Query.query(Criteria.where("_id").is(Id));
	
	Role role = mongoTemplate.findOne(query, Role.class);
	
	return role;
	}
}

