/**
* 	@author SHAJIND C
*/

package com.example.demo.model;

public class DefectModelHolder {
	private String id;
	private String description;
	private String projectID;
	private String assignedUser;
	private String actualResult;
	private String expectedResult;
	private String status;
	private int severity;
	private String comment;

	public DefectModelHolder() {
		super();

	}

	public DefectModelHolder(String id, String description, String projectID, String assignedUser, String actualResult,
			String expectedResult, String status, int severity, String comment) {
		super();
		this.id = id;
		this.description = description;
		this.projectID = projectID;
		this.assignedUser = assignedUser;
		this.actualResult = actualResult;
		this.expectedResult = expectedResult;
		this.status = status;
		this.severity = severity;
		this.comment = comment;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}