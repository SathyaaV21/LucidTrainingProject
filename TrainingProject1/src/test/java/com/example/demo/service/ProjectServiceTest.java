/**
	* 	@author Manju
	* 
	* 
	*/

package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.Project;

@SpringBootTest

class ProjectServiceTest {

	@Mock
	private MongoTemplate mongotemplate;

	@Spy

	@InjectMocks
	private ProjectService projectservice;

	@Test
	public void addProjectTest() throws ProjectNotFoundException {
		Project pro = new Project();
		pro.setProjectId("Prj78");
		pro.setProjectDescription("For unit Testing purpose");
		pro.setProjectName("Junit mockito");

		when(mongotemplate.save(pro)).thenReturn(pro);
		assertTrue(projectservice.addProject(pro) instanceof String);

	}

	@Test
	public void updateProjectTest() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("projectName", "Retest");
		parameters.put("projectDescription", "Status updated");

		Project pro = new Project();
		pro.setProjectId("Prj88");
		pro.setProjectDescription("For unit Testing purpose");
		pro.setProjectName("Junit mockito");

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is("Prj88"));

		Update update = new Update();
		update.set("projectName", "Retest");
		update.set("projectDescription", "Status updated");
		when(mongotemplate.findById("Prj88", Project.class)).thenReturn(pro);
		when(mongotemplate.findAndModify(query, update, Project.class)).thenReturn(pro);
		assertTrue(projectservice.updateproject(parameters, "Prj88") instanceof String);

	}

	@Test
	public void getallProjectTest() throws ProjectNotFoundException {
		when(mongotemplate.findAll(Project.class)).thenReturn(Stream.of(new Project()).collect(Collectors.toList()));
		assertEquals(1, projectservice.viewProjects().size());

	}

	@Test

	public void testGetProjectById() throws ProjectNotFoundException {
		Project pro = new Project();
		pro.setProjectId("Prj34");
		pro.setProjectDescription("description new changed");
		pro.setProjectName("mockito project");

		when(mongotemplate.findById("Prj34", Project.class)).thenReturn(pro);
		assertEquals(pro, projectservice.getByProjectId("Prj34"));

	}

	@Test
	public void getsingleprojectTest() {
		Project pro = new Project();
		pro.setProjectId("Prj34");
		pro.setProjectDescription("description new changed");
		pro.setProjectName("mockito project");

		when(mongotemplate.findById("Prj34", Project.class)).thenReturn(null);
		Assertions.assertThrows(ProjectNotFoundException.class, () -> projectservice.getByProjectId(null));

	}

	@Test
	public void getallProjectsTest() throws ProjectNotFoundException {

		ProjectService service = new ProjectService();
		ProjectNotFoundException exception = assertThrows(ProjectNotFoundException.class, () -> service.viewProjects());
		assertEquals("Projects Not Found", exception.getMessage());

	}
}
