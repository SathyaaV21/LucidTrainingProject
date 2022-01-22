/**
* 	@author RITIKA M
*/

package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.example.demo.model.RTM;
import com.example.demo.model.ReqHolder;
import com.example.demo.model.Requirement;
import com.example.demo.model.TestCase;
import com.example.demo.model.TestCaseCount;
import com.example.demo.model.Project;
import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.DashRequirements;
import com.example.demo.model.DashTestcase;
import com.example.demo.model.DefectCount;

@Service
public class DashboardService {
	
	@Autowired
	private MongoTemplate mongo;
	
	@Autowired
	private TestCaseService testcaseService;
	
	@Autowired
	private DefectService defectService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private RequirementService requirementService;
	
	
	/**
	 * Method to get Requirements traceability matrix (RTM) of all the projects
	 * 
	 * @return List<RTM> with respective status and information.
	 * @throws ProjectNotFoundException handles Exception
	 */

	public List<RTM> getRTM() throws ProjectNotFoundException{
		List<Project> projects=projectService.viewProjects();
		List<TestCase> testcases =testcaseService.viewTestcases();
		List<ReqHolder> requirements=requirementService.viewReq();
		List<RTM> response = new ArrayList<RTM>();
		List<Requirement> requirements_list=new ArrayList<Requirement>();
		List<String> ProjID=new ArrayList<String>();
		for(ReqHolder requirement : requirements) {
			List<Requirement> req=requirement.getRequirement();
			String ID=requirement.getProjectId();
			for(Requirement requirement1:req) {
				requirements_list.add(requirement1);
				ProjID.add(ID);
				
			}
			
		}
		
		
	for (int i = 0; i < projects.size(); i++) {

		RTM rtm = new RTM();
		List<DashRequirements> tempRequirements = new ArrayList<DashRequirements>();

		for(Requirement individual_requirement : requirements_list) {
			int index=requirements_list.indexOf(individual_requirement);
			List<DashTestcase> tempTests = new ArrayList<DashTestcase>();
			if (ProjID.get(index)!= null && ProjID.get(index).equals(projects.get(i).getProjectId())) {
				DashRequirements reqModel = new DashRequirements();
				for (TestCase test : testcases){
					if (test.getProjectId() != null
							&& test.getRequirementId().equals(individual_requirement.getRequirementId())
							&& test.getProjectId().equals(projects.get(i).getProjectId()))
					{
						DashTestcase testModel = new DashTestcase();
						testModel.setName(test.getTestCaseName());
						testModel.setStatus(test.getStatus());
						testModel.setTestCaseID(test.getTestCaseId());
						testModel.setDescription(test.getDescription());
						tempTests.add(testModel);

					}

				}
				reqModel.setRequirementID(individual_requirement.getRequirementId());
				reqModel.setDescription(individual_requirement.getRequirementDescription());
				reqModel.setTestcases(tempTests);
				tempRequirements.add(reqModel);
			}

		}
		rtm.setID(projects.get(i).getProjectId());
		rtm.setName(projects.get(i).getProjectName());
		rtm.setRequirements(tempRequirements);
		response.add(rtm);

	}

	return response;
	}
	
	
	/**
	 * Method to get Test case Trend (Start count and End count of open testcases)
	 * 
	 * @return List<TestCaseCount> with respective start count and end count.
	 * @throws ProjectNotFoundException handles Exception
	 */
	public List<TestCaseCount> getTestCaseProgress(){
		return mongo.findAll(TestCaseCount.class);
		
	}
	
	
	/**
	 * Method to get Test case Trend (Start count and End count of open testcases)
	 * 
	 * @param Project Id
	 * @return List<TestCaseCount> with respective counts related to that project ID.
	 *@throws ProjectNotFoundException handles Exception
	 */
	public List<TestCaseCount> getTestCaseProgressbyProjId(String ProjectId) throws ProjectNotFoundException{
		Query q = new Query();
		q.addCriteria(Criteria.where("project_id").is(ProjectId));

		List<TestCaseCount> reqEntity = mongo.find(q, TestCaseCount.class);
		if (reqEntity != null) {
			return reqEntity;
		} else {
			throw new ProjectNotFoundException("Invalid Project ID");
		}
	}
	
	/**
	 * Method to get Defects Trend (Start count and End count of open Defects)
	 * 
	 * @return List<DefectCount> with respective start count and end count.
	 */
	public List<DefectCount> getDefectsProgress(){
		return mongo.findAll(DefectCount.class);
		
	}
	
	/**
	 * Method to get open Testcases count with Requirement ID
	 * 
	 * @param RequirementID
	 * @return int Count of open testcases
	 */
	public int getOpenTestCaseCountbyRequirementId(String RequirementId) {
		return testcaseService.getRequirementTestcaseCount(RequirementId);
	}
		
		
		
	
		
		
		
		
		
		
		
		
		
		
		
	}
