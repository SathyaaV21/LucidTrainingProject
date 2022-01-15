package com.example.demo.model;

public class GroupedCount {
	int openCount;
	int closedCount;
	int failedCount;
	int fixedCount;
	public GroupedCount(int openCount, int closedCount, int failedCount, int fixedCount) {
		super();
		this.openCount = openCount;
		this.closedCount = closedCount;
		this.failedCount = failedCount;
		this.fixedCount = fixedCount;
	}
	public GroupedCount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getOpenCount() {
		return openCount;
	}
	public void setOpenCount(int openCount) {
		this.openCount = openCount;
	}
	public int getClosedCount() {
		return closedCount;
	}
	public void setClosedCount(int closedCount) {
		this.closedCount = closedCount;
	}
	public int getFailedCount() {
		return failedCount;
	}
	public void setFailedCount(int failedCount) {
		this.failedCount = failedCount;
	}
	public int getFixedCount() {
		return fixedCount;
	}
	public void setFixedCount(int fixedCount) {
		this.fixedCount = fixedCount;
	}
	
}
