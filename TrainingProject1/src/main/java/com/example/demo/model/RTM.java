package com.example.demo.model;
<<<<<<< HEAD
=======


>>>>>>> e7de00154097d4c75f1599047c38bb8f0d112c58

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "RTM")
public class RTM {
	@Id
	private String ID;
	private String name;
	private List<DashRequirements> requirements;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public List<DashRequirements> getRequirements() {
		return requirements;
	}
	public void setRequirements(List<DashRequirements> requirements) {
		this.requirements = requirements;
	}
	public RTM(String name, List<DashRequirements> requirements) {
		super();
		this.name = name;
		this.requirements = requirements;
	}
	public RTM() {
		super();	
		}
	
	
}
