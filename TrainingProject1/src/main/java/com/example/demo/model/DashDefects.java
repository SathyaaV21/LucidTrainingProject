package com.example.demo.model;

public class DashDefects {
	private String DefectID;
	private String UserId;
	private String description;
	private String status;
	public String getDefectID() {
		return DefectID;
	}
	public void setDefectID(String defectID) {
		DefectID = defectID;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public DashDefects() {
		super();
	}
	public DashDefects(String defectID, String userId, String description, String status) {
		super();
		DefectID = defectID;
		UserId = userId;
		this.description = description;
		this.status = status;
	}

}
