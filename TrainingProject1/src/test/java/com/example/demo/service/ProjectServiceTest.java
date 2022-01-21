package com.example.demo.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.Project;

@SpringBootTest

class ProjectServiceTest {

	@Autowired
	private ProjectService service;
	@Mock
	private MongoTemplate mongotemplate;
	@Spy
	@InjectMocks
	private ProjectService projectservice;
	
	@Test
	public void GetProjectByIdTest() throws ProjectNotFoundException {
		Project pro = new Project();
		pro.setProjectId("Prj34");
		pro.setProjectDescription("description new changed");
		pro.setProjectName("mockito project");
		when(mongotemplate.findById("Prj34", Project.class)).thenReturn(pro);
		assertEquals(pro, projectservice.getByProjectId("Prj34"));

	}

	@Test
	void addProjectTest() throws ProjectNotFoundException {
		Project pro = new Project();
		pro.setProjectName("project1");
		pro.setProjectDescription("desc");
		pro.getProjectName();
		pro.getProjectDescription();
		assertTrue(service.addProject(pro) instanceof String);

	}

	@Test
	void getallprojectsTest() throws ProjectNotFoundException {
		assertTrue(service.viewProjects().get(0) instanceof Project);
	}

	@Test
	void updateprojectTest() {
		Map<String, String> pro = new HashMap<String, String>();
		pro.put("projectName", "name");
		pro.put("projectDescription", "changed");
		assertTrue(service.updateproject(pro, "Prj2") instanceof String);
	}

	

}
