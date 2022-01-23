/**
	* 	@author Manju
	*/
package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import static org.mockito.Mockito.when;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.TestCase;

@SpringBootTest
class TestCaseServiceTest {

	@Mock
	private MongoTemplate mongotemplate;

	@Spy

	@InjectMocks
	private TestCaseService testservice;

	@Autowired
	private TestCaseService service;

	@Test
	void addTestcaseTest() throws ProjectNotFoundException {
		TestCase test = new TestCase();
		test.setTestCaseName("Testing purpose");
		test.setDescription("Testing description");
		test.setStatus("Test Status");
		test.setExpectedResults("Test should pass");
		test.setActualResults("passed");
		test.getTestCaseName();
		test.getDescription();
		test.getStatus();
		test.getExpectedResults();
		test.getActualResults();
		test.getProjectId();
		test.getTestCaseId();
		test.getRequirementId();
		assertTrue(service.addTestcase(test, "Prj1", "Prj1Req1") instanceof String);
	}

	@Test
	public void getallTestcasesTest() throws ProjectNotFoundException {
		when(mongotemplate.findAll(TestCase.class)).thenReturn(Stream.of(new TestCase()).collect(Collectors.toList()));
		assertEquals(1, testservice.viewTestcases().size());

	}

	@Test
	void updateTestcaseTest() throws ProjectNotFoundException {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("testCaseName", "Dashboard services");
		parameters.put("expectedResults", "Mockito update");
		parameters.put("description", "testing the update service");
		parameters.put("actualResults", "Testing started");
		parameters.put("status", "Passed");

		TestCase test = new TestCase();
		test.setTestCaseId("Prj1Req1tc1");
		test.setProjectId("Prj1");
		test.setRequirementId("Prj1Req1");
		test.setTestCaseName("new test");
		test.setExpectedResults("New");
		test.setDescription("new desc");
		test.setActualResults("new result");
		test.setStatus("Onhold");

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is("Prj1Req1tc1"));

		Update update = new Update();
		update.set("testCaseName", "Dashboard services");
		update.set("expectedResults", "Mockito update");
		update.set("description", "testing the update service");
		update.set("projectDescription", "Status updated");
		update.set("actualResults", "Testing started");
		update.set("status", "Passed");

		when(mongotemplate.findById("Prj1Req1tc1", TestCase.class)).thenReturn(test);
		when(mongotemplate.findAndModify(query, update, TestCase.class)).thenReturn(test);
		assertTrue(testservice.updateTestcase(parameters, "Prj1Req1tc1") instanceof String);

	}

}
