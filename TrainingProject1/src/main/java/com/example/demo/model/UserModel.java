package com.example.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class UserModel {

	@Document(collection="UserDb")
	public class UserDb {
		
		    
		@Id
		private int userId;
	
		private String firstName;
		
		private String lastName;
		
		private List<String> userRoles;
		
		private String userEmail;
		
		private String userPassword;
		
		private String userStatus;

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public List<String> getUserRoles() {
			return userRoles;
		}

		public void setUserRoles(List<String> userRoles) {
			this.userRoles = userRoles;
		}

		public String getUserEmail() {
			return userEmail;
		}

		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}

		public String getUserPassword() {
			return userPassword;
		}

		public void setUserPassword(String userPassword) {
			this.userPassword = userPassword;
		}

		public String getUserStatus() {
			return userStatus;
		}

		public void setUserStatus(String userStatus) {
			this.userStatus = userStatus;
		}
}
}
