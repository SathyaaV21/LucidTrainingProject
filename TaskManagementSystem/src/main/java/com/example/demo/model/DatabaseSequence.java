package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
public class DatabaseSequence {
	private static final String SEQUENCE_NAME = "user_sequence";
    
	@Id
    private String id;
    private int count;
    public DatabaseSequence() {}

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
}
