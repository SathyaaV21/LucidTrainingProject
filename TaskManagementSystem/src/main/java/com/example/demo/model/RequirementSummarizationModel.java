package com.example.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="RequirementSummarization")
public class RequirementSummarizationModel {

	@Id
	private String req_Id;
	private int no_of_tasks;
	private int no_of_task_completed;
	private int no_of_task_notcompleted;
	private float completionPercentage;
	private List<TaskModel> reqTasks;
	
	
	
	public String getReq_Id() {
		return req_Id;
	}
	public void setReq_Id(String req_Id) {
		this.req_Id = req_Id;
	}
	public int getNo_of_tasks() {
		return no_of_tasks;
	}
	public void setNo_of_tasks(int no_of_tasks) {
		this.no_of_tasks = no_of_tasks;
	}
	public int getNo_of_task_completed() {
		return no_of_task_completed;
	}
	public void setNo_of_task_completed(int no_of_task_completed) {
		this.no_of_task_completed = no_of_task_completed;
	}
	public int getNo_of_task_notcompleted() {
		return no_of_task_notcompleted;
	}
	public void setNo_of_task_notcompleted(int no_of_task_notcompleted) {
		this.no_of_task_notcompleted = no_of_task_notcompleted;
	}
	public float getCompletionPercentage() {
		return completionPercentage;
	}
	public void setCompletionPercentage(float completionPercentage) {
		this.completionPercentage = completionPercentage;
	}
	public List<TaskModel> getReqTasks() {
		return reqTasks;
	}
	public void setReqTasks(List<TaskModel> reqTasks) {
		this.reqTasks = reqTasks;
	}
	
	
	
	
	
}
