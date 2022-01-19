package com.example.demo.model;
import java.util.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;


import com.fasterxml.jackson.annotation.JsonFormat;

public class TaskModel {

	@Id 
	private String taskId;
	
	private String taskName;
	@NotNull(message = "Task description is mandatory") 
	@Size(min=1,max=100,message="The descrption should not exceed 100 characters")
	private String taskDescription;
	@NotNull(message = "Task type is mandatory") 
	private String taskType;
	private String taskStatus;
	private String assignedTo;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date startDate;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date endDate;
	@NotNull(message = "Effort is mandatory")
	private int effort;
	private int todo;
	private String riskAnalysis;
	private List<TaskHistory> taskhistory;
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
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
	public int getEffort() {
		return effort;
	}
	public void setEffort(int effort) {
		this.effort = effort;
	}
	public int getTodo() {
		return todo;
	}
	public void setTodo(int todo) {
		this.todo = todo;
	}
	public String getRiskAnalysis() {
		return riskAnalysis;
	}
	public void setRiskAnalysis(String riskAnalysis) {
		this.riskAnalysis = riskAnalysis;
	}
	public List<TaskHistory> getTaskhistory() {
		return taskhistory;
	}
	public void setTaskhistory(List<TaskHistory> taskhistory) {
		this.taskhistory = taskhistory;
	}

}