package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.exception.ProjectNotFoundException;
import com.example.model.Project;
import com.example.model.ReqHolder;
import com.example.model.Requirement;
import com.example.model.Sequence;
import com.example.model.TestHolder;
import com.example.model.Testcase;

@Service

public class ProjectService {

	@Autowired
	private SequenceGenService service;

	@Autowired
	private MongoTemplate mongotemplate;

	public String addProject(Project project) {
		String j = "Pro" + service.getCount(Sequence.getSequenceName());
		project.setProjectId(j);
		mongotemplate.save(project);
		return "added";
	}

	public List<Project> viewProjects() {
		return mongotemplate.findAll(Project.class);
	}

	public Project getByProjectId(String id) throws ProjectNotFoundException {
		Project project = mongotemplate.findById(id, Project.class);

		if (project != null) {
			return project;
		} else {
			throw new ProjectNotFoundException("Project not found");
		}
	}

	public String updateproject(String Id, Project project) {

		Project project_ = mongotemplate.findById(Id, Project.class);

		project_.setTargetedReleaseDate(project.getTargetedReleaseDate());
		mongotemplate.save(project_);

		return "updated";

	}

	public void addRequirement(Requirement requirement, String projectId) {

		ReqHolder req = new ReqHolder();
		req.setProjectId(projectId);

		ReqHolder reqHolder = mongotemplate.findById(projectId, ReqHolder.class);
		if (reqHolder == null) {
			ReqHolder req_ = new ReqHolder();
			req_.setProjectId(projectId);
			requirement.setRequirementId(projectId + "req" + Integer.toString(1));
			List<Requirement> reqArr = new ArrayList<>();
			reqArr.add(requirement);
			req_.setRequirement(reqArr);

			mongotemplate.save(req_);

		} else {
			List<Requirement> r = reqHolder.getRequirement();
			requirement.setRequirementId(projectId + "req" + Integer.toString((r.size() + 1)));
			r.add(requirement);
			req.setRequirement(r);
			mongotemplate.save(req);

		}

	}
	
	
//	public void updateReq(Map<String,String> requirement,String requirementId,String projectId) {
//		Query query= new Query();
//		query.addCriteria(Criteria.where("requirementId").is(requirementId));
//		ReqHolder reqHolder = mongotemplate.findById(projectId, ReqHolder.class);
//		List<Requirement> req = reqHolder.getRequirement();
////		Requirement req_=new Requirement();
////		for (Requirement r:req) {
////			if (r.getRequirementId().equals(requirementId)) {
////				req_=r;
////				break;
////			}
////		}
//		Update update = new Update();
//		for (Map.Entry r1 : requirement.entrySet()) {
//			update.set((String) r1.getKey(), r1.getValue());
//		}
//
//	mongotemplate.findAndModify(query, update, ReqHolder.class);
//		
//		
		
		
//		ReqHolder reqHolder = mongotemplate.findById(projectId, ReqHolder.class);
//		List<Requirement> req = reqHolder.getRequirement();
//		
//		for (Requirement r:req) {
//			if (r.getRequirementId().equals(requirementId)) {
//				r.setRequirementDescription(requirement.getRequirementDescription());
//				r.setStatus(requirement.getStatus());
//			}
//		}
//		reqHolder.setRequirement(req);
//
//		mongotemplate.save(reqHolder);
//	}
	
	public void updateTestcase(Testcase testcase,String testcaseId, String projectId) {
		TestHolder testHolder = mongotemplate.findById(projectId, TestHolder.class);
		List<Testcase> test = testHolder.getTestList();
		for (Testcase t:test) {
			if (t.getTestCaseId().equals(testcaseId)) {
				t.setTestCaseName(testcase.getTestCaseName());
				t.setStatus(testcase.getStatus());		
			}
		}
		testHolder.setTestList(test);
		mongotemplate.save(testHolder);
	}
	

	public void deleteReq(String requirementId, String projectId) {
		ReqHolder reqHolder = mongotemplate.findById(projectId, ReqHolder.class);
		List<Requirement> req = reqHolder.getRequirement();
		for (Requirement r : req) {
			if (r.getRequirementId().equals(requirementId)) {
				req.remove(r);
				break;
			}
		}
		reqHolder.setRequirement(req);
		mongotemplate.save(reqHolder);

	}

	public void addTestcase(Testcase testcase, String projectId, String requirementId) {
		TestHolder test = new TestHolder();
		test.setProjectId(projectId);
		test.setRequirementId(requirementId);
		TestHolder testHolder = mongotemplate.findById(projectId, TestHolder.class);
		if (testHolder == null) {
			TestHolder test_ = new TestHolder();
			test_.setProjectId(projectId);
			testcase.setTestCaseId(projectId + requirementId + "tc" + Integer.toString(1));
			List<Testcase> testArr = new ArrayList<>();
			testArr.add(testcase);
			test_.setTestList(testArr);
			mongotemplate.save(test_);
		} else {
			List<Testcase> t = testHolder.getTestList();
			testcase.setTestCaseId(projectId + requirementId + "tc" + Integer.toString((t.size() + 1)));
			t.add(testcase);
			test.setTestList(t);
			mongotemplate.save(test);
		}

	}
	

}





























