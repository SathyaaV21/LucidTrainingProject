package com.example.demo.model;
import com.example.demo.model.TestCase;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "RTM")
public class RTM {
	@Id
	private String requirement_Id;
	private List<TestCase> testCase;
	
	

	public List<TestCase> getTestCase() {
		return testCase;
	}
	public void setTestCase(List<TestCase> testCase) {
		this.testCase = testCase;
	}
	public String getRequirement_Id() {
		return requirement_Id;
	}
	public void setRequirement_Id(String requirement_Id) {
		this.requirement_Id = requirement_Id;
	}
	
}
