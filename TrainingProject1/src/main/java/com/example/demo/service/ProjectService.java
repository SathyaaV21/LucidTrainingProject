package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.model.Project;
import com.example.demo.model.Requirement;
import com.example.demo.model.Sequence;

@Service

public class ProjectService {

	@Autowired
	private SequenceGenService service;

	@Autowired
	private MongoTemplate mongotemplate;

	public List<Project> viewProjects() {
		return mongotemplate.findAll(Project.class);
	}
	
	
	public void deleteAllProjects() {
		mongotemplate.dropCollection(Project.class);
	}

	public String addProject(Project project) {
		int i = 1;
		String j = "Pro" + service.getCount(Sequence.getSequenceName());
		project.setProjectId(j);
		List<Requirement> req = project.getRqmt();
		for (Requirement r : req) {
			r.setRequirementID(j + "req" + Integer.toString(i));
			i++;
		}
		mongotemplate.save(project);
		return "added";
	}

	public ResponseEntity<Project> updateProjects(String projectId, Project project_) {
		Project project = mongotemplate.findById(projectId, Project.class);
		project.setProjectName(project_.getProjectName());
		project.setProjectDescription(project_.getProjectDescription());
		project.setRqmt(project_.getRqmt());
		final Project updatedProject = mongotemplate.save(project);
		return ResponseEntity.ok(updatedProject);

	}


	public Project getByProjectId(String id) {
		return mongotemplate.findById(id, Project.class);
	}


	

}











