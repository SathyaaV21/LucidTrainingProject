/**
* 	@author Manju
*/
package com.example.controller;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
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
	 * Method to create project.
	 *
	 * 
	 * @param ProjectModel.
	 * @return status of project.
	 */

	@PostMapping("/project")

	public String CreateProject(@Valid @RequestBody Project project) {
		return ("Status Code : " + HttpStatus.OK + '\n' + "Status Message : Success " + '\n'
				+ "Description : Project created successfully "+ '\n' +projectservice.addProject(project));
	}

	/**
	 * Method to Fetch all projects.
	 * 
	 * @return List of all the Projects from the database.
	 */

	@GetMapping("/project")
	public List<Project> getProjects() throws ProjectNotFoundException {
		return projectservice.viewProjects();
	}

	/**
	 * Method to get a specific project
	 * 
	 * @param Project Id
	 * @return Project model of the respective project Id is passed.
	 */
	@GetMapping("/project/{Id}")
	public Project getprojectByID(@PathVariable String Id) throws ProjectNotFoundException {
		return projectservice.getByProjectId(Id);

	}

	/**
	 * Method to Update project
	 *
	 * 
	 * @param ProjectModel and project Id is passed
	 * @return Respective status of ProjectModel Update.
	 */
	@PutMapping("project/{projectId}")
	public void updateproject(@RequestBody Map<String,String> project,@PathVariable String projectId){
		projectservice.updateproject(project,projectId);
	}

}

















