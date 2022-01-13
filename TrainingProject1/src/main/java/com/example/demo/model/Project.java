/**
* 	@author Manju
*/

package com.example.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "Projects")
public class Project {

	@Id
	private String projectId;

	@NotBlank(message = "Project Name is mandatory")
	private String projectName;

	@NotBlank(message = "Project Description is mandatory")
	private String projectDescription;

	@NotNull(message = "Start date is mandatory")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startDate;

	@NotNull(message = "End date is mandatory")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endDate;

	@NotNull(message = "Targeted release is mandatory")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date targetedReleaseDate;

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

	public Date getTargetedReleaseDate() {
		return targetedReleaseDate;
	}

	public void setTargetedReleaseDate(Date targetedReleaseDate) {
		this.targetedReleaseDate = targetedReleaseDate;
	}

}
