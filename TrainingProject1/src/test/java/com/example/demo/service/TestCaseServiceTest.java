package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.TestCase;
@SpringBootTest
class TestCaseServiceTest {

	@Autowired
	private TestCaseService testservice;

//	@Test
//	void addTestcaseTest() {
//		TestCase test = new TestCase();
//		test.setTestCaseName("test 1");
//		test.setDescription("desc new");
//		test.setStatus("status new");
//		test.setExpectedResults("should pass");
//		test.setActualResults("passed");
//		test.getTestCaseName();
//		test.getDescription();
//		test.getStatus();
//		test.getExpectedResults();
//		test.getActualResults();
//		test.getProjectId();
//		test.getTestCaseId();
//		test.getRequirementId();
//		assertTrue(testservice.addTestcase(test, "Prj4", "Prj4Req2") instanceof String);
//	}

	@Test
	void getTestcasesTest() {
		assertTrue(testservice.viewTestcases().get(0) instanceof TestCase);
	}

	@Test
	void updateTestcaseTest() throws ProjectNotFoundException {
		Map<String, String> test = new HashMap<String, String>();
		test.put("testCaseName", "Dashboard services");
		test.put("expectedResults", "Ensure that the user is able to get the defects  with respective status.Ensure that a history table to be maitained . A scheduled script to be run at predefined set of time to populate the history table with count");
		test.put("description", "Ensure that the user is able to view the status of defects");
		test.put("actualResults", "Testing started");
		test.put("status", "Onhold");
		assertTrue(testservice.updateTestcase(test, "Prj4Req7Tc1") instanceof String);

	}
	

}



















