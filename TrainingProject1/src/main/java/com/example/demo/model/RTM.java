package com.example.demo.model;



import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "RTM")
public class RTM {
	@Id
	private String requirement_Id;
	private List<Testcase> testCase;
	
	

	public List<Testcase> getTestCase() {
		return testCase;
	}
	public void setTestCase(List<Testcase> testCase) {
		this.testCase = testCase;
	}
	public String getRequirement_Id() {
		return requirement_Id;
	}
	public void setRequirement_Id(String requirement_Id) {
		this.requirement_Id = requirement_Id;
	}
	
}
