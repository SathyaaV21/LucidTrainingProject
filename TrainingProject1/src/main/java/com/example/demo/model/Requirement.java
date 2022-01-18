/**
* 	@author Manju
*/
package com.example.demo.model;

import javax.validation.constraints.NotBlank;

public class Requirement {
	
	private String requirementId;
	
	@NotBlank(message = "Description is mandatory")
	private String requirementDescription;
	
	@NotBlank(message = "Status is mandatory")
	private String status;
	
	private int testCount;
	private int taskCount;
	

	public int getTestCount() {
		return testCount;
	}

	public void setTestCount(int testCount) {
		this.testCount = testCount;
	}

	public int getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}

	public String getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(String requirementId) {
		this.requirementId = requirementId;
	}

	public String getRequirementDescription() {
		return requirementDescription;
	}

	public void setRequirementDescription(String requirementDescription) {
		this.requirementDescription = requirementDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
