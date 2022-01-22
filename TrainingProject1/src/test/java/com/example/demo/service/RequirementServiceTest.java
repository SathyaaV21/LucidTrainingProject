/**
	* 	@author Manju
	*/
package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.ReqHolder;
import com.example.demo.model.Requirement;

@SpringBootTest

class RequirementServiceTest {

	@Autowired
	private RequirementService reqservice;

	@InjectMocks
	private RequirementService service;

	@Mock
	Requirement requirement;

	@Mock
	MongoTemplate mongoTemplate;

	@Test
	void addRequirement() throws ProjectNotFoundException {
		Requirement req = new Requirement();
		req.setRequirementId("Prj7Req1");
		req.setRequirementDescription("Project description");
		req.setStatus("Completed");

		ReqHolder reqHolder = new ReqHolder();
		reqHolder.setProjectId("Prj7");

		List<Requirement> reqList = new ArrayList<Requirement>();
		reqList.add(req);
		reqHolder.setRequirement(reqList);

		Mockito.when(mongoTemplate.findById("Prj7Req1", Requirement.class)).thenReturn(req);
		Mockito.when(mongoTemplate.save(req)).thenReturn(null);
		Mockito.when(mongoTemplate.findById("Prj7", ReqHolder.class)).thenReturn(reqHolder);
		Mockito.when(mongoTemplate.save(reqHolder)).thenReturn(reqHolder);

		assertTrue(service.deleteReq("Prj7Req1", "Prj7") instanceof String);

	}

	@Test
	void getallRequirementsTest() {
		assertTrue(reqservice.viewReq().get(0) instanceof ReqHolder);
	}

	@Test
	void updatereqbyIdTest() throws ProjectNotFoundException {
		Requirement req = new Requirement();
		req.setRequirementDescription("Create service to Create a new project");
		assertTrue(reqservice.updateReq(req, "Prj2Req1", "Prj2") instanceof String);

	}

	@Test
	void deleteRequirement() throws ProjectNotFoundException {
		Requirement req = new Requirement();
		req.setRequirementId("Prj7Req1");
		req.setRequirementDescription("Project description");
		req.setStatus("Completed");

		ReqHolder reqHolder = new ReqHolder();
		reqHolder.setProjectId("Prj7");

		List<Requirement> reqList = new ArrayList<Requirement>();
		reqList.add(req);
		reqHolder.setRequirement(reqList);

		Mockito.when(mongoTemplate.findById("Prj7Req1", Requirement.class)).thenReturn(req);
		Mockito.when(mongoTemplate.remove(req)).thenReturn(null);
		Mockito.when(mongoTemplate.findById("Prj7", ReqHolder.class)).thenReturn(reqHolder);
		Mockito.when(mongoTemplate.save(reqHolder)).thenReturn(reqHolder);
		assertTrue(service.deleteReq("Prj7Req1", "Prj7") instanceof String);

	}

}
