
package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.controller.AuthController;
import com.example.demo.model.Role;
import com.example.demo.model.Sequence;
import com.example.demo.model.User;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;

@SpringBootTest
public class UserManagerTest {

	@Autowired
	AuthController authController;

//	@Autowired
	@InjectMocks
	UserService userService;
	
	@Mock
	UserService userServe;
	@Mock
	private UserRepository userRepository;

//	@Autowired
	@InjectMocks
	RoleService roleServices;
	@Mock
	private SequenceGenService service;
	
	//@SpyBean

	//@Autowired
	@Mock
	RoleRepository roleRepository;
	
	@Mock
	MongoTemplate mongoTemplate;
	
	@Mock
	PasswordEncoder encoder;
	
	@Mock
	MongoOperations mongoOperations;

	
	@Test
	public void signUpTest() throws IOException {
		SignupRequest user = new SignupRequest();
		user.setUsername("Sooryaa");
		user.setEmail("sooryaa@gmail.com");
		user.setPassword("pass");
		User usr = new User(user.getUsername(), user.getEmail(), user.getPassword());
		Mockito.when(userServe.saveUser(usr)).thenReturn(null);
		assertTrue(authController.registerUser(user) instanceof ResponseEntity<?>);
	}

	@Test
	public void signInTest() throws IOException {
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("Sooryaa");

		loginRequest.setPassword("pass");
		User user = new User("Sooryaa", "qwerty", "sathyaa@gmail.com");
		Mockito.when(userRepository.existsByUsername(loginRequest.getUsername())).thenReturn(true);
		Query query = Query.query(Criteria.where("username").is(loginRequest.getUsername()));
		Mockito.when(mongoTemplate.findOne(query, User.class)).thenReturn(user);
		assertTrue(authController.authenticateUser(loginRequest) instanceof ResponseEntity<?>);
	}

	@Test
	public void saveUserTest() throws IOException {
		User user = new User("Sooryaa", "qwerty", "sathyaa@gmail.com");
		
		Mockito.when(service.getCount(Sequence.getSequenceName5())).thenReturn(1);
		Mockito.when(encoder.encode("qwerty")).thenReturn("asfafdfdf");
		Mockito.when(mongoTemplate.save(user)).thenReturn(new User("Sathyaa", "qwerty", "sathyaa@gmail.com")); 
		assertTrue(userService.saveUser(user) instanceof ResponseEntity<?>);
	}
	@Test
	public void findUserById() throws IOException {
		User user = new User("USR_1", "Sathyaa", "qwerty", "sathyaa@gmail.com");
		Query query = new Query();
		Mockito.when(userRepository.existsById("USR_1")).thenReturn(true);
		query.addCriteria(Criteria.where("id").is("USR_1"));
		query.fields().exclude("password");
		Mockito.when(mongoTemplate.findOne(query, User.class)).thenReturn(user);

		assertEquals(user, userService.findUser("USR_1"));
	}
	
	

	@Test
	public void displayAllUserDetailTest() throws IOException {
		Query query = new Query();
		query.fields().exclude("password");
		Mockito.when(mongoTemplate.find(query, User.class))
				.thenReturn(Stream.of(new User("USR_1", "Sathyaa", "qwerty", "sathyaa@gmail.com"),
						new User("USR_2", "Shajind", "qwerty", "shajind@gmail.com"),
						new User("USR_3", "Manju", "qwerty", "manju@gmail.com")).collect(Collectors.toList()));
		List<User> allUsers = mongoTemplate.find(query, User.class);
		assertEquals(3, allUsers.size());
	}
	
	@Test
	public void addRoleToUserTest() throws IOException {
		
		String userId="USR_1";
		String roleId="ROLE_1";
		User user = new User(userId, "Sathyaa", "qwerty", "sathyaa@gmail.com");
		Role role = new Role(roleId, "Admin", true);
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(userId));

		Update update = new Update().addToSet("roles", role);
		Mockito.when(userRepository.existsById(userId)).thenReturn(true);
		Mockito.when(roleRepository.existsById(roleId)).thenReturn(true);
		Mockito.when(mongoTemplate.findOne(new Query().addCriteria(Criteria.where("id").is(roleId)), Role.class)).thenReturn(role);
		Mockito.when(mongoTemplate.save(role)).thenReturn(role);
	

		
		Mockito.when(mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(false), User.class)).thenReturn(user);
		assertNull(userService.addRoleToUser(userId, roleId));
	}
	
	@Test
	public void deleteUserTest() throws IOException{
		Mockito.when(userRepository.existsById("USR_1")).thenReturn(true);
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is("USR_1"));
		User user = new User("USR_1", "Sathyaa", "qwerty", "sathyaa@gmail.com");
		Mockito.when(mongoTemplate.findOne(query, User.class)).thenReturn(user);
		Mockito.when(mongoTemplate.save(user)).thenReturn(user);
		assertTrue(userService.deleteUser("USR_1") instanceof MessageResponse);
	}
	

	
	@Test
	public void deleteRoleFromUserTest() throws IOException {
		User user = new User("USR_1", "Sathyaa", "qwerty", "sathyaa@gmail.com");
		Role role = new Role("ROLE_1", "Admin", true);

		
		Mockito.when(userRepository.existsById("USR_1")).thenReturn(true);
		Mockito.when(roleRepository.existsById("ROLE_1")).thenReturn(true);
		
		Mockito.when(mongoTemplate.findOne(new Query().addCriteria(Criteria.where("id").is("ROLE_1")), Role.class)).thenReturn(role);
		
		Query query = Query.query(Criteria.where("id").is("USR_1"));
		Query query2 = Query.query(Criteria.where("name").is(role.getName()));
		Update update = new Update().pull("roles", query2);
		Mockito.when(mongoTemplate.updateFirst(query, update, User.class)).thenReturn(null);
		
		Query query3 = new Query();
		query3.addCriteria(Criteria.where("roles.name").is(role.getName()));
		List<User> activeroles= new ArrayList<User>();
		Mockito.when(mongoTemplate.find(query3, User.class)).thenReturn(activeroles);
		
		//Mockito.when(activeroles.isEmpty()).thenReturn(true);
		Mockito.when(mongoTemplate.save(role)).thenReturn(role);

		

		assertTrue(userService.deleteRoleFromUser("USR_1", "ROLE_1") instanceof MessageResponse);
	}
	 

	@Test
	public void displayAllActiveRoleDetailTest() throws IOException {
		Query query = new Query().addCriteria(Criteria.where("isrolestatusactive").is(true));
		Mockito.when(mongoTemplate.find(query, Role.class)).thenReturn(Stream.of(new Role("ROLE_1", "Admin", true),
				new Role("ROLE_2", "Manager", true), new Role("ROLE_3", "Tester", true)).collect(Collectors.toList()));
		assertEquals(3, roleServices.displayAllActiveRoleDetail().size());
	}
    
	@Test
	public void displayAllRoleDetailTest() throws IOException {
		Mockito.when(mongoTemplate.findAll(Role.class)).thenReturn(Stream.of(new Role("ROLE_1", "Admin", true),
				new Role("ROLE_2", "Manager", false), new Role("ROLE_3", "Tester", true)).collect(Collectors.toList()));
		assertEquals(3, roleServices.displayAllRoleDetail().size());
	}
	
	@Test
	public void displayRoleByIdTest() throws IOException {
		Role role=new Role("ROLE_1", "Admin", true);
		Query query = Query.query(Criteria.where("_id").is("ROLE_1"));
		Mockito.when(roleRepository.existsById("ROLE_1")).thenReturn(true);
		Mockito.when(mongoTemplate.findOne(query,Role.class)).thenReturn(role);
		assertEquals(role ,roleServices.findById("ROLE_1"));
	}
	
	@Test
	public void addNewRoleTest() throws IOError {
		Role role = new Role("ROLE_1", "Tester", true);
		Mockito.when(service.getCount(Sequence.getSequenceName4())).thenReturn(1);
		Mockito.when(mongoTemplate.save(role)).thenReturn(new Role("ROLE_1", "Tester", true));
		assertTrue(roleServices.addNewRole(role.getName()) instanceof MessageResponse);
	}

	@Test
	public void deleteRoleTest() throws IOException {
		Role role = new Role("ROLE_2", "Tester", true);
		Mockito.when(roleRepository.existsById("ROLE_2")).thenReturn(true);
		Query query = Query.query(Criteria.where("_id").is("ROLE_2"));
		Mockito.when(mongoTemplate.findOne(query, Role.class)).thenReturn(role);
		
		Query query3 = Query.query(Criteria.where("name").is("Tester"));
		
		Update update = new Update().pull("roles", query3);
		Mockito.when(mongoTemplate.updateMulti( new Query(), update, User.class )).thenReturn(null);
		Mockito.when(mongoTemplate.findAndRemove(query, Role.class)).thenReturn(role);
		assertTrue(roleServices.deleteRole("ROLE_2") instanceof MessageResponse);
	}

	@Test
	public void updateRoleTest() throws IOException {
		Map<String,String> hashMap=new HashMap<>();
		hashMap.put("password","qwerty");
		hashMap.put("phonenumber","1234567890");
		Query query = Query.query(Criteria.where("id").is("USR_1"));
		User user = new User("USR_1", "Sathyaa", "qwerty", "sathyaa@gmail.com");

		Update update1 = new Update();
		update1.set("password", encoder.encode("qwerty"));
		update1.set("phonenumber", "0123456789");
		Mockito.when(mongoTemplate.findAndModify(query, update1, User.class)).thenReturn(user);
		
		assertTrue(userService.updateUser("USR_1", hashMap) instanceof ResponseEntity<?>);
		
	}
}
