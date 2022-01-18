/**
 * @author SATHYAA
 *
 */
package com.example.demo.service;

import java.util.List;
//import java.util.Optional;

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


//import com.example.demo.repository.RoleRepository;
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
		role.setIsRolestatusactive(true);
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
 * 
 * @param role id.
 * @return MessageResponse stating that the role is successfully set inactive.
 */
public MessageResponse deleteRole(String roleid) {
	if (!roleRepository.existsById(roleid)) {
		return new MessageResponse("Error: Role is not available");
	}
	Query query = Query.query(Criteria.where("_id").is(roleid));
	Role role = mongoTemplate.findOne(query, Role.class);
	role.setIsRolestatusactive(false);
	mongoTemplate.save(role);

	Query query2 = Query.query(Criteria.where("id").exists(true));
	Query query3 = Query.query(Criteria.where("$id").is(role.getId()));
	Update update = new Update().pull("roles", query3);
	mongoTemplate.updateMulti(query2, update, User.class);
	return new MessageResponse("Role is set inactive");
}

/**
 * Service that allows administrator to update a role in the application.
 * 
 * @param role id, role name and role status.
 * @return MessageResponse stating that the role has been updated successfully.
 */
public MessageResponse updateRole(String roleid, String rolename, Boolean rolestatus) {
	if (!roleRepository.existsById(roleid)) {
		return new MessageResponse("Error: Role is not available");
	}
	rolename = "ROLE_" + rolename.toUpperCase();
	Query query = new Query().addCriteria(Criteria.where("_id").is(roleid));
	Update update = new Update().set("name", rolename).set("isrolestatusactive", rolestatus);
	mongoTemplate.findAndModify(query, update, Role.class);
	return new MessageResponse("Role has been updated");
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
}

