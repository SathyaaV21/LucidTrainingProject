/**
* 	@author Manju
*/
package com.example.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "Requirements")

public class ReqHolder {
@Id
private String projectId;
private List<Requirement> requirement;
public String getProjectId() {
	return projectId;
}
public void setProjectId(String projectId) {
	this.projectId = projectId;
}
public List<Requirement> getRequirement() {
	return requirement;
}
public void setRequirement(List<Requirement> requirement) {
	this.requirement = requirement;
}
}
