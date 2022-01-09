package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Project;
import com.example.demo.model.Testcase;
import com.example.demo.service.TestcaseService;

@RequestMapping("/api/v1")
@RestController
public class TestcaseController {
	
	private static final String String = null;
	
	@Autowired
	private TestcaseService testcaseservice;

	@PostMapping("/testcase")

//	public String CreateTestcase(@RequestBody Testcase testcase) {
//		testcaseservice.addTestcase(testcase);
//		return ("Status Code : " + HttpStatus.OK + '\n' + "Status Message : Success " + '\n'
//				+ "Description : Testcase created successfully ");
//	}

	@GetMapping("/testcase")
	public List<Testcase> getTestcases() {
		return testcaseservice.viewTestcases();
	}
	
	 @PutMapping("/testcase/{Id}")
	    public ResponseEntity<Testcase> updateTestcase(@PathVariable(value = "Id") String testCaseID,
	         @RequestBody Testcase testcase_){
			return testcaseservice.updateTestcases(testCaseID, testcase_);

	        
	    }


}
