package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Requirement;

@Service
public class RqmtService {

	@Autowired
	private MongoTemplate mongotemplate;

	// getMapping
	public List<Requirement> viewRequirements() {
		return mongotemplate.findAll(Requirement.class);
	}

	// deleteMapping
	public void deleteAllRequirements() {
		mongotemplate.dropCollection(Requirement.class);
	}

	// postMapping
	public String addRequirement(Requirement requirement) {
		mongotemplate.save(requirement);
		return "Requirement added";
	}

}
