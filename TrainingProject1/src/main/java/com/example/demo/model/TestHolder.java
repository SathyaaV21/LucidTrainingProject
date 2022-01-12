package com.example.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "testcases")

public class TestHolder {
	@Id
	private String projectId;
	private String requirementId;
	private List<Testcase> testList;
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
	public List<Testcase> getTestList() {
		return testList;
	}
	public void setTestList(List<Testcase> testList) {
		this.testList = testList;
	}

}

