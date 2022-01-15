package com.example.demo.model;

import java.util.List;

public class DashRequirements {
	private String RequirementID;
	private String Description;
	private List<DashTestcase> Testcases;
	public DashRequirements(String requirementID, String description, List<DashTestcase> testcases) {
		super();
		RequirementID = requirementID;
		Description = description;
		Testcases = testcases;
	}
	public DashRequirements() {
		super();
	}
	public String getRequirementID() {
		return RequirementID;
	}
	public void setRequirementID(String requirementID) {
		RequirementID = requirementID;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public List<DashTestcase> getTestcases() {
		return Testcases;
	}
	public void setTestcases(List<DashTestcase> testcases) {
		Testcases = testcases;
	}
	
	
	

}
