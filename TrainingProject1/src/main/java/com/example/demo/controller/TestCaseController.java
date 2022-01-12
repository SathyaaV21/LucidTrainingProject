package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ProjectNotFoundException;
import com.example.model.TestHolder;
import com.example.model.Testcase;
import com.example.service.TestCaseService;

@RequestMapping("/api/v1")
@RestController
public class TestCaseController {
	

	@Autowired
	private TestCaseService testservice;

	/**
	 * Method to Get all Testcases
	 * 
	 * @return List of testcases from the database.
	 */

	@GetMapping("/testcase")
	public List<TestHolder> getTestcases() {
		return testservice.viewTestcases();
	}
	
	
	/**
	 * Method to add TestCases
	 *
	 * 
	 * @param Project Id,Requirement id,testcase model
	 * @return Respective status of Added testCases.
	 */

	@PostMapping("testcase/{projectId}/{requirementId}")
	public void createTestcase(@PathVariable String projectId, @PathVariable String requirementId,
			@RequestBody Testcase testcase) {
		testservice.addTestcase(testcase, projectId, requirementId);
	}
	

	/**
	 * Method to update testCase
	 *
	 * 
	 * @param Project id,requirement Id, TestCase Id and TestCaseModel
	 * @return Respective status and information of TestCase Update.
	 */

	@PutMapping("testcase/{projectId}/{testcaseId}")
	public void updateTestcase(@PathVariable String projectId, @PathVariable String testcaseId,
			@RequestBody Testcase testcase) throws ProjectNotFoundException {
		testservice.updateTestcase(testcase, testcaseId, projectId);
	}
}
