package com.example.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="previous_day_data")
public class PreviousDayCount {
	@Id
	private String Type;
	private long prevCount;
	private List<testCaseID> typeLists;
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public long getPrevCount() {
		return prevCount;
	}
	public void setPrevCount(long prevCount) {
		this.prevCount = prevCount;
	}
	public List<testCaseID> getTypeLists() {
		return typeLists;
	}
	public void setTypeLists(List<testCaseID> typeLists) {
		this.typeLists = typeLists;
	}
	public PreviousDayCount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PreviousDayCount(String type, long prevCount, List<testCaseID> typeLists) {
		super();
		Type = type;
		this.prevCount = prevCount;
		this.typeLists = typeLists;
	}
}
