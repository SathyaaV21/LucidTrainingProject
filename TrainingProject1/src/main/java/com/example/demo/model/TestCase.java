package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;

public class TestCase {

	private String testcase_Id;
	private String status;
	public String getTestcase_Id() {
		return testcase_Id;
	}
	public void setTestcase_Id(String testcase_Id) {
		this.testcase_Id = testcase_Id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

