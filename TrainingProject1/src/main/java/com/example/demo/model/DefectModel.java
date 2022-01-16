/**
* 	@author SHAJIND C
*/

package com.example.demo.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Defect_Collection")
public class DefectModel {
	@Id
	private String id;
	@NotNull(message = "Defect description is mandatory") 
	@Size(min=1,max=100,message="The descrption should not exceed 100 characters")
	private String description;
	private String projectID;
	private String assignedUser; 
	@NotNull(message="Please specify actual result")
	@Size(min=1,max=100,message="The message should not exceed 100 characters")
	private String actualResult;
	@NotNull(message="Please specify expected result")
	@Size(min=1,max=100,message="The message should not exceed 100 characters")
	private String expectedResult;
	private String presentStatus;
	private List<Status> defectHistory;
	private List<Comments> comments;
	private List<String> attachment; 

	private int severity;

	public DefectModel() {
		super();
	}

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

	public List<Status> getDefectHistory() {
		return defectHistory;
	}

	public void setDefectHistory(List<Status> defectHistory) {
		this.defectHistory = defectHistory;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public List<String> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<String> attachment) {
		this.attachment = attachment;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

}