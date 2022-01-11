/**
* 	@author SHAJIND C
*/

package com.example.demo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "Status_Collection")
public class Status {
	@Id
	private String assignedUser;
	private String statusBefore;
	private String currentStatus;
	private String comment;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Status(String assignedUser, String statusBefore, String currentStatus, String comment,
			LocalDateTime timestamp) {
		super();
		this.assignedUser = assignedUser;
		this.statusBefore = statusBefore;
		this.currentStatus = currentStatus;
		this.comment = comment;
		this.timestamp = timestamp;
	}

	public String getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(String assignedUser) {
		this.assignedUser = assignedUser;
	}

	public String getStatusBefore() {
		return statusBefore;
	}

	public void setStatusBefore(String statusBefore) {
		this.statusBefore = statusBefore;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
