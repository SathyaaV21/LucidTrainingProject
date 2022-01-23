/**
* 	@author Manju
*/
package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.TestCase;
import com.example.demo.service.TestCaseService;

@RequestMapping("/api/v1")
@RestController
public class TestCaseController {
	private static final Logger logger = LoggerFactory.getLogger(TestCaseController.class);

	@Autowired
	private TestCaseService testservice;

	/**
	 * Method to Get all Testcases
	 * 
	 * @return List of testcases from the database.
	 */

	@GetMapping("/testcase")
	public List<TestCase> getTestcases() {
		logger.info("request sent to fetch testcases");
		return testservice.viewTestcases();
	}

	/**
	 * Method to add TestCases
	 *
	 * 
	 * @param Project Id,Requirement id,testcase model
	 * @return
	 * @return Respective status of Added testCases.
	 * @throws ProjectNotFoundException
	 */

	@PostMapping("testcase/{projectId}/{requirementId}")
	public String createTestcase(@PathVariable String projectId, @PathVariable String requirementId,
			@Valid @RequestBody TestCase testcase) throws ProjectNotFoundException {
		logger.info("In creating testcase");
		testservice.addTestcase(testcase, projectId, requirementId);
		return ("Status Code : " + HttpStatus.OK + '\n' + "Status Message : Success " + '\n'
				+ "Description : Testcase added successfully ");
	}

	/**
	 * Method to update testCase
	 *
	 * 
	 * @param Project id,requirement Id, TestCase Id and TestCaseModel
	 * @return
	 * @return
	 * @return Respective status and information of TestCase Update.
	 */

	@PutMapping("testcase/{testcaseId}")
	public String updateTestcase(@RequestBody Map<String, String> testcase, @PathVariable String testcaseId) {
		logger.info("In updating testcase");
		testservice.updateTestcase(testcase, testcaseId);
		return "Testcase updated successfully";
	}

}
