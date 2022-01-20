package com.example.demo.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.Project;
@SpringBootTest

class ProjectServiceTest {
	@Autowired
	private ProjectService projectservice;
	
//	@Test
//	void addProjectTest() throws ProjectNotFoundException {
//		Project pro = new Project();
//		pro.setProjectName("project1");
//		pro.setProjectDescription("desc");
//		pro.getProjectName();
//		pro.getProjectDescription();
//		assertTrue(projectservice.addProject(pro) instanceof String);
//
//	}

	@Test
	void getallprojectsTest() throws ProjectNotFoundException {
		assertTrue(projectservice.viewProjects().get(0) instanceof Project);
	}

	@Test
	void updateprojectTest() {
		Map<String, String> pro = new HashMap<String, String>();
		pro.put("projectName", "name");
		pro.put("projectDescription", "changed");
		assertTrue(projectservice.updateproject(pro, "Prj2") instanceof String);
	}

	@Test
	void getprojectbyidTest() throws ProjectNotFoundException {
		assertTrue(projectservice.getByProjectId("Prj3") instanceof Project);
	}
	
}
