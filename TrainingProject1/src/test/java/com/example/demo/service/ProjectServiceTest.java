/**
	* 	@author Manju
	*/

package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;

import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.Project;
import com.example.demo.model.Requirement;
import com.example.demo.model.User;

@SpringBootTest

class ProjectServiceTest {

	@Autowired
	private ProjectService service;

	@InjectMocks
	private ProjectService projectservice;

	@Mock
	Project project;

	@Mock
	MongoTemplate mongoTemplate;

	@Test
	public void GetProjectByIdTest() throws ProjectNotFoundException {
		Project pro = new Project();
		pro.setProjectId("Prj78");
		pro.setProjectDescription("For unit Testing purpose");
		pro.setProjectName("Junit mockito");

		when(mongoTemplate.save(pro)).thenReturn(pro);
		assertEquals("Project added", projectservice.addProject(pro));

	}

	@Test
	void getallprojectsTest() throws ProjectNotFoundException {
		assertTrue(service.viewProjects().get(0) instanceof Project);
	}

	@Test
	void updateprojectTest() {
		Map<String, String> pro = new HashMap<String, String>();
		pro.put("projectName", "Requirement management");
		pro.put("projectDescription", "Adding Project,Requirement,Testcase services");
		Query query = Query.query(Criteria.where("_id").is("Prj2"));

		Update update = new Update();
		update.set("projectName", "Updated success");
		update.set("projectDescription", "Updated using Mockito");
		
		Mockito.when(mongoTemplate.findAndModify(query, update, Project.class));

		assertTrue(service.updateproject(pro, "Prj2") instanceof String);
	}

}











