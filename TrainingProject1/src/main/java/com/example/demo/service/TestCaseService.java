/**
* 	@author Manju
*/
package com.example.demo.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.TestCase;
//import com.example.demo.model.Testcase;


@Service
public class TestcaseService {

	
	@Autowired
	private MongoTemplate mongotemplate;
	
	/**
	 * Method to get all Testcases for the particular requirement in the Database
	 * 
	 * @return all testcases .
	 */

	
	public List<TestCase> viewTestcases()  {
		
		return mongotemplate.findAll(TestCase.class);

}
	
	/**
	 * Method to add Testcase for the particular requirement in the Database
	 * 
	 * @param the Project id, requirement Id and TestcaseModel is passed.
	 * @return status of the Added testcase .
	 */

	public void addTestcase(TestCase testcase, String projectId, String requirementId) {

			testcase.setProjectId(projectId); 
			testcase.setRequirementId(requirementId);
			List<TestCase> test=mongotemplate.findAll(TestCase.class);
			int i=1; 
			for(TestCase t:test) {
				if(t.getRequirementId().equals(requirementId)){
					i++;
				}
			}
			testcase.setTestCaseId(requirementId+"Tc"+Integer.toString(i));
			mongotemplate.insert(testcase);
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
	public void updateTestcase(Map<String,String> testcase, String testcaseId) throws ProjectNotFoundException {

		Query query=new Query();
		query.addCriteria(Criteria.where("_id").is(testcaseId)); 
		Update update=new Update();
		for (Map.Entry test : testcase.entrySet()) {
			update.set((String) test.getKey(), test.getValue());
		} 
		mongotemplate.findAndModify(query, update,TestCase.class); 
		
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
		return count;
	}
}
		















































