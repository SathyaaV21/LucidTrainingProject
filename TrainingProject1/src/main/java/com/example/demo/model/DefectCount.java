/**
* 	@author RITIKA M
*/

package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="DefectCount")
public class DefectCount {
	private int startCount;
	private int endCount;

	public int getStartCount() {
		return startCount;
	}
	public void setStartCount(int startCount) {
		this.startCount = startCount;
	}
	public int getEndCount() {
		return endCount;
	}
	public void setEndCount(int endCount) {
		this.endCount = endCount;
	}
	
}
