package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ProjectNotFoundException;
import com.example.model.Project;
import com.example.model.Requirement;
import com.example.model.Testcase;
import com.example.service.ProjectService;

@RequestMapping("/api/v1")
@RestController
public class ProjectController {

	@Autowired
	private ProjectService projectservice;

	@PostMapping("/addproject")

	public String CreateProject(@RequestBody Project project) {
		projectservice.addProject(project);
		return ("Status Code : " + HttpStatus.OK + '\n' + "Status Message : Success " + '\n'
				+ "Description : Project created successfully ");
	}

	@GetMapping("/getproject")
	public List<Project> getProjects() {
		return projectservice.viewProjects();
	}

	@GetMapping("/getproject/{Id}")
	public Project getprojectByID(@PathVariable("Id") String id) throws ProjectNotFoundException {
		return projectservice.getByProjectId(id);

	}

	@PutMapping("updateproject/{Id}")
	public String updateproject(@PathVariable String Id , @RequestBody Project project) {
		return projectservice.updateproject(Id,project);
	}

	@PostMapping("requirement/{projectId}")
	public void createRequirement(@PathVariable String projectId,
			@RequestBody Requirement requirement) {
		 projectservice.addRequirement(requirement, projectId);
	}

	@PostMapping("testcase/{projectId}/{requirementId}")
	public void createTestcase(@PathVariable String projectId,@PathVariable String requirementId,
			@RequestBody Testcase testcase)  {
		 projectservice.addTestcase(testcase, projectId , requirementId);
	}

	@DeleteMapping("testcase/{projectId}/{requirementId}")
	public void deleteTestcase(@PathVariable String projectId,@PathVariable String requirementId) {
		projectservice.deleteReq( requirementId, projectId);
		
	}
	
//	@PutMapping("requirement/{projectId}/{requirementId}")
//	public void updateReq(@PathVariable String projectId,@PathVariable String requirementId,
//			@RequestBody Requirement requirement)
//	{
//		projectservice.updateReq(requirement, requirementId, projectId);
//	}
	
	@PutMapping("testcase/{projectId}/{testcaseId}")
	public void updateTestcase(@PathVariable String projectId,@PathVariable String testcaseId,
			@RequestBody Testcase testcase)
	{
		projectservice.updateTestcase(testcase, testcaseId, projectId);
	}
	
}































