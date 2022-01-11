/**
* 	@author SHAJIND C
*/

package com.example.demo.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "Comment_Collection")
public class Comments {
	@Id 
	private String assignedUser;
	private String comment;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comments(String assignedUser, String comment, LocalDateTime timestamp) {
		super(); 
		this.assignedUser = assignedUser;
		this.comment = comment;
		this.timestamp = timestamp;
	}
	

	public String getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(String assignedUser) {
		this.assignedUser = assignedUser;
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
