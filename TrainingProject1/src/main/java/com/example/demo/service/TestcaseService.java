package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.file.model.Testcase;

@Service
public class TestcaseService {
	@Autowired
	private MongoTemplate mongotemplate;


	public List<Testcase> viewTestcases() {
		return mongotemplate.findAll(Testcase.class);
	}


	

	public ResponseEntity<Testcase> updateTestcases(String testCaseId, Testcase testcase_) {
		Testcase testcase = mongotemplate.findById(testCaseId, Testcase.class);
		testcase.setTestCaseName(testcase_.getTestCaseName());
		testcase.setDescription(testcase_.getDescription());
		testcase.setExpectedResults(testcase_.getExpectedResults());
		final Testcase updatedTestcase = mongotemplate.save(testcase);
		return ResponseEntity.ok(updatedTestcase);

	}
}












