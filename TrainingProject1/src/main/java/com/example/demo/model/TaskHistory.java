package com.example.demo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TaskHistory {
	private int todoBefore;
	private int todoNow;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date dateandTime;
	public int getTodoBefore() {
		return todoBefore;
	}
	public void setTodoBefore(int todoBefore) {
		this.todoBefore = todoBefore;
	}
	public int getTodoNow() {
		return todoNow;
	}
	public void setTodoNow(int todoNow) {
		this.todoNow = todoNow;
	}
	public Date getDateandTime() {
		return dateandTime;
	}
	public void setDateandTime(Date dateandTime) {
		this.dateandTime = dateandTime;
	}

	
}
