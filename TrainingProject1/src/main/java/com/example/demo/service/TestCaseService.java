package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.exception.ProjectNotFoundException;
import com.example.model.TestHolder;
import com.example.model.Testcase;

@Service
public class TestCaseService {
	
	@Autowired
	private SequenceGenService service;

	@Autowired
	private MongoTemplate mongotemplate;
	
	/**
	 * Method to get all Testcases for the particular requirement in the Database
	 * 
	 * @return all testcases .
	 */

	
	public List<TestHolder> viewTestcases()  {
		
		return mongotemplate.findAll(TestHolder.class);

}
	
	/**
	 * Method to add Testcase for the particular requirement in the Database
	 * 
	 * @param the Project id, requirement Id and TestcaseModel is passed.
	 * @return status of the Added testcase .
	 */

	public void addTestcase(Testcase testcase, String projectId, String requirementId) {
		TestHolder test = new TestHolder();
		test.setProjectId(projectId);
		test.setRequirementId(requirementId);
		TestHolder testHolder = mongotemplate.findById(projectId, TestHolder.class);
		if (testHolder == null) {
			TestHolder test_ = new TestHolder();
			test_.setProjectId(projectId);
			testcase.setTestCaseId(requirementId + "tc" + Integer.toString(1));
			List<Testcase> testArr = new ArrayList<>();
			testArr.add(testcase);
			test_.setTestList(testArr);
			mongotemplate.save(test_);
		} else {
			List<Testcase> t = testHolder.getTestList();
			testcase.setTestCaseId(requirementId + "tc" + Integer.toString((t.size() + 1)));
			t.add(testcase);
			test.setTestList(t);
			mongotemplate.save(test);
		}

	}

	/**
	 * Method to update Requirements for the Project to the Database
	 * 
	 * @param the Project id , testcase id, and testcase model is passed.
	 * @return status of updated testcase
	 * @throws ProjectNotFoundException
	 * @throws Handles                  Exception.
	 * 
	 */
	public void updateTestcase(Testcase testcase, String testcaseId, String projectId) throws ProjectNotFoundException {
		try {
			TestHolder testHolder = mongotemplate.findById(projectId, TestHolder.class);
			List<Testcase> test = testHolder.getTestList();
			for (Testcase t : test) {
				if (t.getTestCaseId().equals(testcaseId)) {
					t.setTestCaseName(testcase.getTestCaseName());
					t.setStatus(testcase.getStatus());
				}
			}
			testHolder.setTestList(test);
			mongotemplate.save(testHolder);
		} catch (Exception e) {
			throw new ProjectNotFoundException("Testcase Not Found");
		}
	}
	
}
