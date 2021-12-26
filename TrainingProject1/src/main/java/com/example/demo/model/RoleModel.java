package com.example.demo.model;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class RoleModel {

	
	@Document(collection="RoleDb")
	public class RoleDb {
		
		    
		@Id
		private int roleId;
	
		private String roleName;
		
		private List<String> rolePermission;
		
		private String roleDescription;
		
		private String roleStatus;

		public int getRoleId() {
			return roleId;
		}

		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}

		public String getRoleName() {
			return roleName;
		}

		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

		public List<String> getRolePermission() {
			return rolePermission;
		}

		public void setRolePermission(List<String> rolePermission) {
			this.rolePermission = rolePermission;
		}

		public String getRoleDescription() {
			return roleDescription;
		}

		public void setRoleDescription(String roleDescription) {
			this.roleDescription = roleDescription;
		}

		public String getRoleStatus() {
			return roleStatus;
		}

		public void setRoleStatus(String roleStatus) {
			this.roleStatus = roleStatus;
		}
		
		
}
}
