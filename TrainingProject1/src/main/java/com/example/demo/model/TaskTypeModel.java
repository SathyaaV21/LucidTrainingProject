package com.example.demo.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TaskTypeDetails")

public class TaskTypeModel {

@Id
private int tasktypeId;
@NotNull(message = "Task type name is mandatory")
private String tasktypeName;
private String tasktypeDescription;

public int getTasktypeId() {
	return tasktypeId;
}
public void setTasktypeId(int tasktypeId) {
	this.tasktypeId = tasktypeId;
}
public String getTasktypeName() {
	return tasktypeName;
}
public void setTasktypeName(String tasktypeName) {
	this.tasktypeName = tasktypeName;
}
public String getTasktypeDescription() {
	return tasktypeDescription;
}
public void setTasktypeDescription(String tasktypeDescription) {
	this.tasktypeDescription = tasktypeDescription;
}



}
