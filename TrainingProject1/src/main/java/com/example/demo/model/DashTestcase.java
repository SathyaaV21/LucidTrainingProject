/**
* 	@author RITIKA M
*/
package com.example.demo.model;

public class DashTestcase {
	private String Name;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	private String TestCaseID;
	private String Status;
	private String Description;
	public String getTestCaseID() {
		return TestCaseID;
	}
	public void setTestCaseID(String testCaseID) {
		TestCaseID = testCaseID;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public DashTestcase() {
		super();
	}
	public DashTestcase(String testCaseID, String status, String description) {
		super();
		TestCaseID = testCaseID;
		Status = status;
		Description = description;
	}
	
	
	

}
