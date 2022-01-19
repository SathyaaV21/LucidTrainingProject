/**
* 	@author Manju
*/
package com.example.demo.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.controller.RequirementController;
import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.ReqHolder;
import com.example.demo.model.Requirement;
import com.example.demo.model.TestCase;
//import com.example.demo.model.Testcase;
@Service
public class TestCaseService {
	private static final Logger logger = LoggerFactory.getLogger(TestCaseService.class);

	
	@Autowired
	private MongoTemplate mongotemplate;
	
	/**
	 * Method to get all Testcases for the particular requirement in the Database
	 * 
	 * @return all testcases .
	 */

	
	public List<TestCase> viewTestcases()  {
		logger.info("Testcases fetched and returned");
		return mongotemplate.findAll(TestCase.class);

}
	
	/**
	 * Method to add Testcase for the particular requirement in the Database
	 * 
	 * @param the Project id, requirement Id and TestcaseModel is passed.
	 * @return status of the Added testcase .
	 */

	public String addTestcase(TestCase testcase, String projectId, String requirementId) {
			testcase.setProjectId(projectId); 
			testcase.setRequirementId(requirementId);
			
			ReqHolder req=mongotemplate.findById(projectId,ReqHolder.class);
			List<Requirement> reqList=req.getRequirement();
			List<Requirement> req_=new ArrayList<Requirement>();
			for(Requirement i:reqList) {
				if(i.getRequirementId().equals(requirementId)) {
					testcase.setTestCaseId(requirementId+"tc"+Integer.toString(i.getTestCount()));
					i.setTestCount(i.getTestCount()+1);
					req_.add(i);
				}
				else
					req_.add(i);
			}
			req.setRequirement(req_);
			mongotemplate.save(req);
			
//			List<TestCase> test=mongotemplate.findAll(TestCase.class);
//			int i=1; 
//			for(TestCase t:test) {
//				if(t.getRequirementId().equals(requirementId)){
//					i++;
//				}
//			}
//			testcase.setTestCaseId(requirementId+"Tc"+Integer.toString(i));
			mongotemplate.insert(testcase);
			logger.info("testcase added successfully");
			return "Testcase added";
	}

	/**
	 * Method to update Testcases for the Project to the Database
	 * 
	 * @param the Project id , testcase id, and testcase model is passed.
	 * @return status of updated testcase
	 * @throws ProjectNotFoundException
	 * @throws Handles Exception.
	 * 
	 */
	public String updateTestcase(Map<String,String> testcase, String testcaseId) throws ProjectNotFoundException {

		Query query=new Query();
		query.addCriteria(Criteria.where("_id").is(testcaseId)); 
		Update update=new Update();
		for (Map.Entry test : testcase.entrySet()) {
			update.set((String) test.getKey(), test.getValue());
		} 
		mongotemplate.findAndModify(query, update,TestCase.class);
		logger.info("testcase updated successfully");
		return "Testcase updated"; 
		
	}
	
	/**
	 * Method to get Testcase count for particular Project from the Database
	 * 
	 * @param the Project id
	 * @return status count of testcases of particular project.
	 */
	
	public int getOpenTestcaseCount(String projectId) {
		List<TestCase> testcase=mongotemplate.findAll(TestCase.class);
		int count=0;
		for(TestCase test:testcase) {
			if (test.getProjectId().equals(projectId))
			{
				if(!(test.getStatus().equals("Failed"))) {
					count++;
				}
			}
		}
		logger.info("returned count of open testcases with respect to project Id");
		return count;
	}
	
	/**
	 * Method to get Testcase count for particular Requirement from the Database
	 * 
	 * @param the requirement id
	 * @return status count of testcases of particular requirement.
	 */
	public int getRequirementTestcaseCount(String requirementId) {
		List<TestCase> testcase=mongotemplate.findAll(TestCase.class);
		int count=0;
		for(TestCase test:testcase) {
			if (test.getRequirementId().equals(requirementId))
			{
				if(!(test.getStatus().equals("Failed"))) {
					count++;
				}
			}
		}
		logger.info("returned count of open testcases with respect to requirement Id");
		return count;
	}
}
		















































