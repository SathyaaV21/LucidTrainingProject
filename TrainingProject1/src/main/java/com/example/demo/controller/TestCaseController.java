/**
* 	@author Manju
*/
package com.example.controller;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.exception.ProjectNotFoundException;
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
	public List<Testcase> getTestcases() {
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
			@Valid @RequestBody Testcase testcase) {
		testservice.addTestcase(testcase, projectId, requirementId);
	}
	

	/**
	 * Method to update testCase
	 *
	 * 
	 * @param Project id,requirement Id, TestCase Id and TestCaseModel
	 * @return 
	 * @return Respective status and information of TestCase Update.
	 */

	@PutMapping("testcase/{testcaseId}")
	public void updateTestcase(@RequestBody Map<String,String> testcase,@PathVariable String testcaseId)
			throws ProjectNotFoundException {
		testservice.updateTestcase( testcase, testcaseId);
	}
	
	

}







































