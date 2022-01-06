package com.example.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="UserDb")
public class UserModel {

	
	
		
		    
		@Id
		private int userId;
		private String username;
		/*
		 * private String firstName;
		 * 
		 * private String lastName;
		 */
		
		/*
		 * private List<String> userRoles;
		 */
		/* private String userEmail; */
		
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		private String password;
		
		/* private String userStatus; */

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		/*
		 * public String getFirstName() { return firstName; }
		 * 
		 * public void setFirstName(String firstName) { this.firstName = firstName; }
		 * 
		 * public String getLastName() { return lastName; }
		 * 
		 * public void setLastName(String lastName) { this.lastName = lastName; }
		 * 
		 * public List<String> getUserRoles() { return userRoles; }
		 * 
		 * public void setUserRoles(List<String> userRoles) { this.userRoles =
		 * userRoles; }
		 * 
		 * public String getUserEmail() { return userEmail; }
		 * 
		 * public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
		 */

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		/*
		 * public String getUserStatus() { return userStatus; }
		 * 
		 * public void setUserStatus(String userStatus) { this.userStatus = userStatus;
		 * }
		 */
}

