
package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Counter")
public class Sequence {
	private static final String SEQUENCE_NAME = "Counter_Sequence"; 
	private static final String SEQUENCE_NAME1=  "Defect_Sequence";
	private static final String SEQUENCE_NAME2=  "Project_Sequence";
	private static final String SEQUENCE_NAME3 = "user_sequence";
	private static final String SEQUENCE_NAME4 = "Role_sequence";
	private static final String SEQUENCE_NAME5 = "Usr_sequence";

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
	public static String getSequenceName3() {
		return SEQUENCE_NAME3;
	}
	public static String getSequenceName4() {
		return SEQUENCE_NAME4;
	}
	public static String getSequenceName5() {
		return SEQUENCE_NAME5;
	}
	
}
