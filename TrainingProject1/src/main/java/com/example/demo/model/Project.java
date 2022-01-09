package com.example.demo.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "Projects")
public class Project {

	@Id
	private String projectId;

	private String projectName;
	private String projectDescription;
	private List<Requirement> rqmt;
	private List<Testcase> test;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String targetedReleaseDate;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTargetedReleaseDate() {
		return targetedReleaseDate;
	}

	public void setTargetedReleaseDate(String targetedReleaseDate) {
		this.targetedReleaseDate = targetedReleaseDate;
	}

	public List<Requirement> getRqmt() {
		return rqmt;
	}

	public void setRqmt(List<Requirement> rqmt) {
		this.rqmt = rqmt;
	}

	public List<Testcase> getTest() {
		return test;
	}

	public void setTest(List<Testcase> test) {
		this.test = test;
	}

}
