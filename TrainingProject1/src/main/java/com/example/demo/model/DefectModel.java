/**
* 	@author SHAJIND C
*/

package com.example.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Defect_Collection")
public class DefectModel {
	@Id
	private String id;
	private String description;
	private String projectID;
	private String assignedUser;
	private String actualResult;
	private String expectedResult; 
	private String presentStatus;
	private List<Status> status; 
	private List<Comments> comments;
	private int severity; 

	public DefectModel() {
		super();
	}

	/*public DefectModel(String id, String description, String projectID, String assignedUser, String actualResult,
			String expectedResult, int severity) {
		super();
		this.id = id;
		this.description = description;
		this.projectID = projectID;
		this.assignedUser = assignedUser;
		this.actualResult = actualResult;
		this.expectedResult = expectedResult;
		this.severity = severity;
	}

	public DefectModel(String id, String description, String projectID, String assignedUser, String actualResult,
			String expectedResult, String status, int severity) {
		super();
		this.id = id;
		this.description = description;
		this.projectID = projectID;
		this.assignedUser = assignedUser;
		this.actualResult = actualResult;
		this.expectedResult = expectedResult;
		this.status = status;
		this.severity = severity;
	}*/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(String assignedUser) {
		this.assignedUser = assignedUser;
	}

	public String getActualResult() {
		return actualResult;
	}

	public void setActualResult(String actualResult) {
		this.actualResult = actualResult;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}
	

	public String getPresentStatus() {
		return presentStatus;
	}

	public void setPresentStatus(String presentStatus) {
		this.presentStatus = presentStatus;
	}

	public List<Status> getStatus() {
		return status;
	}

	public void setStatus(List<Status> status) {
		this.status = status;
	}
	

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

}
