package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.AuthController;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;


@SpringBootTest
public class UserManagerTest {

	

		@Autowired
		AuthController authController;
	
		@Autowired
		UserService userService;

		@MockBean
		private UserRepository userRepository;

		@Autowired
		RoleService roleServices;

		@SpyBean
		@Autowired
		MongoTemplate mongoTemplate;
		
		
		@Test
		public void signUpTest() throws IOException {
			SignupRequest user = new SignupRequest();
			user.setUsername("Sathyaa");
			user.setEmail("sathyaa@gmail.com");
			user.setPassword("pass");
			User usr = new User(user.getUsername(), 
					 user.getEmail(),
					user.getPassword());
			Mockito.when(userService.saveUser(usr)).thenReturn(null);
			assertTrue(authController.registerUser(user) instanceof ResponseEntity<?>);
		}
		
		/*
		 * @Test public void signInTest() throws IOException { LoginRequest user = new
		 * LoginRequest(); user.setUsername("Sathyaa");
		 * 
		 * user.setPassword("pass");
		 * 
		 * 
		 * assertTrue(authController.authenticateUser(user) instanceof ResponseEntity<?>
		 * ); }
		 */
		
		
		
		@Test
		public void saveUserTest() throws IOException {
			User user = new User("Sathyaa","qwerty","sathyaa@gmail.com");
			Mockito.when(mongoTemplate.save(user)).thenReturn(new User("Sathyaa","qwerty","sathyaa@gmail.com"));
			assertTrue(userService.saveUser(user) instanceof MessageResponse);
		}
		
		@Test
		public void registerUserTest() throws IOException {
			User user = new User("Sathyaa", "qwerty", "sathyaa@gmail.com");
			Mockito.when(mongoTemplate.save(user)).thenReturn(new User("Sathyaa","qwerty","sathyaa@gmail.com"));
			assertTrue(userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail()) instanceof MessageResponse);
		}
		
		
		@Test
		public void findUserByUserNameTest() throws IOException {
			User user = new User("Sathyaa", "qwerty", "sathyaa@gmail.com");
			Query query = Query.query(Criteria.where("username").is(user.getUsername()));
			query.fields().exclude("password");
			Mockito.when(mongoTemplate.find(query, User.class)).thenReturn(
					Stream.of(new User("Sathyaa", "qwerty", "sathyaa@gmail.com")).collect(Collectors.toList()));

			assertEquals(1, userService.findByUsername(user.getUsername()).size());
		}
		
		
		@Test
		public void findUserById() throws IOException {
			User user = new User("USR_1","Sathyaa", "qwerty", "sathyaa@gmail.com");
			Query query = new Query();
			query.addCriteria(Criteria.where("id").is("USR_1"));
			query.fields().exclude("password");
			Mockito.when( mongoTemplate.findOne(query, User.class)).thenReturn(
				user);

			assertEquals(user, userService.findUser("USR_1"));
		}
		
		@Test
		public void displayAllUserDetailTest() throws IOException {
			Query query = new Query();
			query.fields().exclude("password");
			Mockito.when(mongoTemplate.find(query, User.class))
					.thenReturn(Stream
							.of( new User("USR_1","Sathyaa", "qwerty", "sathyaa@gmail.com"),
									 new User("USR_2","Shajind", "qwerty", "shajind@gmail.com"),
									 new User("USR_3","Manju", "qwerty", "manju@gmail.com"))
							.collect(Collectors.toList()));
			List<User> allUsers = mongoTemplate.find(query, User.class);
			assertEquals(3,allUsers.size());
		}
	
		

		
		@Test
		public void addNewRoleTest() throws IOException {
			Role role = new Role("ROLE_1", "Tester", true);
			Mockito.when(mongoTemplate.save(role)).thenReturn(new Role("ROLE_1", "Tester", true));
			assertTrue(roleServices.addNewRole(role.getName()) instanceof MessageResponse);
		}
		
		@Test
		public void deleteRoleTest() throws IOException {
			Role role = new Role("ROLE_2","Tester", true);
			Mockito.when(mongoTemplate.save(role)).thenReturn(role);
			assertTrue(roleServices.deleteRole(role.getId()) instanceof MessageResponse);
		}
		
		@Test
		public void updateRoleTest() throws IOException {
			Role role = new Role("ROLE_1", "DEVELOPER", true);
			Mockito.when(mongoTemplate.save(role)).thenReturn(role);
			assertTrue(roleServices.updateRole(role.getId(),role.getName(), role.getIsRolestatusactive()) instanceof MessageResponse);
		}
		@Test
		public void displayAllActiveRoleDetailTest() throws IOException {
			Query query = new Query().addCriteria(Criteria.where("isrolestatusactive").is(true));
			Mockito.when(mongoTemplate.find(query, Role.class)).thenReturn(Stream.of(new Role("ROLE_1", "Admin", true),
					new Role("ROLE_2", "Manager", true),
					new Role("ROLE_3", "Tester", true)).collect(Collectors.toList()));
			assertEquals(3, roleServices.displayAllActiveRoleDetail().size());
		}
		
		
		@Test
		public void displayAllRoleDetailTest() throws IOException {
			Mockito.when(mongoTemplate.findAll(Role.class))
			.thenReturn(Stream.of(new Role("ROLE_1", "Admin", true),
					new Role("ROLE_2", "Manager", false),
					new Role("ROLE_3", "Tester", true)).collect(Collectors.toList()));
			assertEquals(3, roleServices.displayAllRoleDetail().size());
		}
		
		@Test
		public void addRoleToUserTest() throws IOException {
			User user = new User("USR_1","Sathyaa", "qwerty", "sathyaa@gmail.com");
			Role role= new Role("ROLE_1", "Admin", true);
			
			Query query = new Query();
			query.addCriteria(Criteria.where("id").is("USR_1"));
			
			Update update = new Update().addToSet("roles", role);
			
			Mockito.when(mongoTemplate.findAndModify(query, update, User.class)).thenReturn(user);
		
			assertEquals(user,userService.addRoleToUser("USR_1","Admin"));
		}
		
		/*
		 * @Test public void deleteRoleFromUserTest() throws IOException { User user =
		 * new User("USR_1","Sathyaa", "qwerty", "sathyaa@gmail.com"); Role role= new
		 * Role("ROLE_1", "Admin", true);
		 * 
		 * Query query = new Query();
		 * query.addCriteria(Criteria.where("id").is("USR_1")); Query query2 =
		 * Query.query(Criteria.where("$id").is("ROLE_1")); Update update = new
		 * Update().addToSet("roles", query2);
		 * 
		 * 
		 * 
		 * 
		 * mongoTemplate.updateMulti(query, update, User.class);
		 * 
		 * 
		 * 
		 * Mockito.when(mongoTemplate.updateMulti(query, update,
		 * User.class)).thenReturn(wasAcknowledged);
		 * 
		 * assertTrue(userService.deleteRoleFromUser("USR_1","ROLE_1") instanceof
		 * MessageResponse); }
		 */
		
}
