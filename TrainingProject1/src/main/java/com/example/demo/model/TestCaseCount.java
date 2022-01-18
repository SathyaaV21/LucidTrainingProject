/**
* 	@author RITIKA M
*/

package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="TestCaseCount")
public class TestCaseCount {
	private int startCount;
	private int endCount;
	private String project_id;
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public int getStartCount() {
		return startCount;
	}
	public void setStartCount(int startCount) {
		this.startCount = startCount;
	}
	public int getEndCount() {
		return endCount;
	}
	public void setEndCount(int endCount) {
		this.endCount = endCount;
	}
	

}
