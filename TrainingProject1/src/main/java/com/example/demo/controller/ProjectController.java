package com.example.demo.controller;

import java.util.List;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Project;

@RequestMapping("/api/v1")
@RestController
public class ProjectController {

	private static final String String = null;
	
	@Autowired
	private ProjectService projectservice;

//	Creates a new project.

	@PostMapping("/addproject")

	public String CreateProject(@RequestBody Project project) {
		projectservice.addProject(project);
		return ("Status Code : " + HttpStatus.OK + '\n' + "Status Message : Success " + '\n'
				+ "Description : Project created successfully ");
	}

//	Retrieves all projects.

	@GetMapping("/getproject")
	public List<Project> getProjects() {
		return projectservice.viewProjects();
	}

	@GetMapping("/getproject/{Id}")
	public Project getprojectByID(@PathVariable("Id") String id) {
		return projectservice.getByProjectId(id);

	}
	
	
	
	
	
	
	
	
	
//	Delete all projects.

	@DeleteMapping("/deleteprojects")
	public String deleteAllProjects() {
		projectservice.deleteAllProjects();
		return ("Status Code : " + HttpStatus.NO_CONTENT + '\n' + "Status Message : Success " + '\n'
				+ "Description : Project deleted successfully ");

	}
	
// Update projects
	
	 @PutMapping("/updateproject/{Id}")
	    public ResponseEntity < Project > updateProject(@PathVariable(value = "Id") String projectId,
	         @RequestBody Project project_){
			return projectservice.updateProjects(projectId, project_);

	        
	    }

}


















