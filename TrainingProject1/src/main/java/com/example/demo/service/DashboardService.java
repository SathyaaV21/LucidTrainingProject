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
	
	public List<RTM> getRTM() throws ProjectNotFoundException{
		List<Project> projects=projectService.viewProjects();
		List<TestCase> testcases =testcaseService.viewTestcases();
		List<ReqHolder> requirements=requirementService.viewReq();
		List<RTM> response = new ArrayList<RTM>();
	for (int i = 0; i < 5; i++) {

		RTM rtm = new RTM();
		List<DashRequirements> tempRequirements = new ArrayList<DashRequirements>();

		for(ReqHolder requirement : requirements) {

			List<DashTestcase> tempTests = new ArrayList<DashTestcase>();
			List<Requirement> req_list=requirement.getRequirement();
			if (requirement.getProjectId() != null && requirement.getProjectId().equals(projects.get(i).getProjectId())) {
				DashRequirements reqModel = new DashRequirements();
				for (TestCase test : testcases) {
					if (test.getProjectId() != null)
						for(Requirement req : req_list)
							if(test.getRequirementId().equals(req.getRequirementId())
							&& test.getProjectId().equals(projects.get(i).getProjectId())) {
						DashTestcase testModel = new DashTestcase();
						testModel.setName(test.getTestCaseName());
						testModel.setStatus(test.getStatus());
						testModel.setTestCaseID(test.getTestCaseId());
						tempTests.add(testModel);
						reqModel.setRequirementID(req.getRequirementId());
						reqModel.setDescription(req.getRequirementDescription());

					}

				}
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
	
	public List<TestCaseCount> getTestCaseProgress(){
		return mongo.findAll(TestCaseCount.class);
		
	}
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
	
	public List<DefectCount> getDefectsProgress(){
		return mongo.findAll(DefectCount.class);
		
	}
	
	public int getOpenTestCaseCountbyRequirementId(String RequirementId) {
		return testcaseService.getRequirementTestcaseCount(RequirementId);
	}
		
		
		
	
		
		
		
		
		
		
		
		
		
		
		
	}
