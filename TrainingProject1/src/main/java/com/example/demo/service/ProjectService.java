/**
* 	@author Manju
*/
package com.example.demo.service;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.Project;

@Service

public class ProjectService {

	private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);


	@Autowired
	private MongoTemplate mongotemplate;

	/**
	 * Method to add a Project in the database.
	 *
	 * @param ProjectModel is passed.
	 * @return Status of the Project Creation.
	 * 
	 */

	public String addProject(Project pro) {
		

		List<Project> proList=mongotemplate.findAll(Project.class);
		int i=1; 
		for(Project p:proList) {
				i++;
			}
		pro.setProjectId("Prj"+Integer.toString(i));
		mongotemplate.insert(pro);
		logger.info("Project created");
		return "Project added";
		}

	/**
	 * Method to get every Projects from the database.
	 * 
	 * @return Fetch all Projects from the Mongo Database.
	 * @throws ProjectNotFoundException.	 
	 */

	public List<Project> viewProjects() throws ProjectNotFoundException {
		try {
			logger.info("Projects retreived");
			return mongotemplate.findAll(Project.class);
		} catch (Exception e) {
			logger.warn("Projects not retrieved");
			throw new ProjectNotFoundException("Projects Not Found");
		}
	}

	/**
	 * Method to get specific Project by project Id from the Database
	 * 
	 * @param the Project id is passed.
	 * @return Project Details of the project.
	 * @throws ProjectNotFoundException.	
	 * 
	 */

	public Project getByProjectId(String Id) throws ProjectNotFoundException {
		Project project = mongotemplate.findById(Id, Project.class);

		if (project != null) {
			logger.info("project retreived by id");
			return project;
		} else {
			logger.warn("Project id not found or incorrect");
			throw new ProjectNotFoundException("Project not found");
		}
	}

	/**
	 * Method to update specific Project by project Id from Database
	 * 
	 * @param the Project id and Projectmodel is passed.
	 * @return 
	 * @throws ProjectNotFoundException
	 * 
	 */


	public String updateproject(Map<String, String> project, String projectId){
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(projectId));
		Update update = new Update();
		for (Map.Entry test : project.entrySet()) {
			update.set((String) test.getKey(), test.getValue());
		}
		mongotemplate.findAndModify(query, update, Project.class);
		logger.info("Project updated for the specific id");
		return "Updated success";

	}
}
















