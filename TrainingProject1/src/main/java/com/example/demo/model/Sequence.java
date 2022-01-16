
package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Counter")
public class Sequence {
	private static final String SEQUENCE_NAME = "Counter_Sequence"; 
	private static final String SEQUENCE_NAME1=  "Defect_Sequence";
	private static final String SEQUENCE_NAME2=  "Project_Sequence";

	@Id
	private String id;
	private int count;

	public Sequence() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sequence(String id, int count) {
		super();
		this.id = id;
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}
	public static String getSequenceName1() {
		return SEQUENCE_NAME1;
	}
	public static String getSequenceName2() {
		return SEQUENCE_NAME2;
	}
}
