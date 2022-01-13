package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ProjectNotFoundException;
import com.example.model.Project;
import com.example.service.ProjectService;

@RequestMapping("/api/v1")
@RestController
public class ProjectController {

	@Autowired
	private ProjectService projectservice;

	/**
	 * Method to create project
	 *
	 * 
	 * @param ProjectModel
	 * @return Respective status of ProjectModel.
	 */

	@PostMapping("/addproject")

	public String CreateProject(@RequestBody Project project) {
		projectservice.addProject(project);
		return ("Status Code : " + HttpStatus.OK + '\n' + "Status Message : Success " + '\n'
				+ "Description : Project created successfully ");
	}

	/**
	 * Method to Get all projects
	 * 
	 * @return List of Projects from the database.
	 */

	@GetMapping("/getproject")
	public List<Project> getProjects() throws ProjectNotFoundException {
		return projectservice.viewProjects();
	}

	/**
	 * Method to get specific project
	 * 
	 * @param Project Id
	 * @return Project model of the respective project Id is passed.
	 */
	@GetMapping("/getproject/{Id}")
	public Project getprojectByID(@PathVariable("Id") String id) throws ProjectNotFoundException {
		return projectservice.getByProjectId(id);

	}

	/**
	 * Method to Update project
	 *
	 * 
	 * @param ProjectModel and project Id is passed
	 * @return Respective status of ProjectModel Update.
	 */
	@PutMapping("updateproject/{Id}")
	public String updateproject(@PathVariable String Id, @RequestBody Project project) throws ProjectNotFoundException {
		return projectservice.updateproject(Id, project);
	}

}
