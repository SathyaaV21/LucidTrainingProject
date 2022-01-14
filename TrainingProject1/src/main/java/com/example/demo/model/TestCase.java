/**
* 	@author Manju
*/
package com.example.demo.model;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "testcases")
public class Testcase {
	
	@Id
	private String testCaseId;
	private String requirementId;
	private String projectId;
	
	@NotBlank(message = "Testcase Name is mandatory")
	private String testCaseName;
	
	@NotBlank(message = "Description is mandatory")
	private String description;
	
	@NotBlank(message = "Expected Results is mandatory")
	private String expectedResults;
	
	@NotBlank(message = "Actual Results is mandatory")
	private String actualResults;
	
	@NotBlank(message = "Status is mandatory")
	private String status;


	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(String requirementId) {
		this.requirementId = requirementId;
	}

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExpectedResults() {
		return expectedResults;
	}

	public void setExpectedResults(String expectedResults) {
		this.expectedResults = expectedResults;
	}

	public String getActualResults() {
		return actualResults;
	}

	public void setActualResults(String actualResults) {
		this.actualResults = actualResults;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}


}
