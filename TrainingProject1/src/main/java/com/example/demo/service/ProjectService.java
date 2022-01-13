package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import com.example.exception.ProjectNotFoundException;
import com.example.model.Project;
import com.example.model.Sequence;

@Service

public class ProjectService {

	@Autowired
	private SequenceGenService service;

	@Autowired
	private MongoTemplate mongotemplate;

	/**
	 * Method to add Projects in the database
	 *
	 * @param ProjectModel which contains Project details
	 * @return Status of the Project Creation.
	 * 
	 */

	public String addProject(Project project) {
		String j = "Pro" + service.getCount(Sequence.getSequenceName());
		project.setProjectId(j);
		mongotemplate.save(project);
		return "added";
	}

	/**
	 * Method to get every Projects from the database
	 * 
	 * @return view all Projects from the Mongo Database.
	 * @throws ProjectNotFoundException
	 * @throws Handles                  Exception from Database .
	 * 
	 */

	public List<Project> viewProjects() throws ProjectNotFoundException {
		try {
			return mongotemplate.findAll(Project.class);
		} catch (Exception e) {
			throw new ProjectNotFoundException("Projects Not Found");
		}
	}

	/**
	 * Method to get specific Project by project Id from the Database
	 * 
	 * @param the Project id is passed.
	 * @return Project Details of the project.
	 * @throws Handles Exception from Database.
	 * 
	 */

	public Project getByProjectId(String id) throws ProjectNotFoundException {
		Project project = mongotemplate.findById(id, Project.class);

		if (project != null) {
			return project;
		} else {
			throw new ProjectNotFoundException("Project not found");
		}
	}

	/**
	 * Method to update specific Project by project Id from Database
	 * 
	 * @param the Project id and Projectmodel is passed.
	 * @return Status of the Project Update.
	 * @throws ProjectNotFoundException
	 * 
	 */

	public String updateproject(String Id, Project project) throws ProjectNotFoundException {
		try {

			Project project_ = mongotemplate.findById(Id, Project.class);
			if (project_ == null) {
				throw new ProjectNotFoundException("id not found");
			}

			project_.setTargetedReleaseDate(project.getTargetedReleaseDate());
			mongotemplate.save(project_);

			return "updated";

		} catch (Exception e) {
			throw new ProjectNotFoundException("Project Not Found");
		}
	}

}
