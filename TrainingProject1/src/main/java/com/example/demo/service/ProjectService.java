/**
* 	@author Manju
*/
package com.example.demo.service;
import java.util.List;
import java.util.Map;
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
	 * @throws ProjectNotFoundException.	
	 * 
	 */

	public Project getByProjectId(String Id) throws ProjectNotFoundException {
		Project project = mongotemplate.findById(Id, Project.class);

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


	public String updateproject(Map<String, String> project, String projectId){
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(projectId));
		Update update = new Update();
		for (Map.Entry test : project.entrySet()) {
			update.set((String) test.getKey(), test.getValue());
		}
		mongotemplate.findAndModify(query, update, Project.class);
		return "project Updated";

	}
}
















