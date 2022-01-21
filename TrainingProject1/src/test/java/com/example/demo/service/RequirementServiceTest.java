package com.example.demo.service;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.ReqHolder;
import com.example.demo.model.Requirement;

@SpringBootTest

class RequirementServiceTest {

	@Autowired
	private RequirementService reqservice;

	@Test
	void addRequirementTest() {
		Requirement req = new Requirement();
		req.setRequirementDescription("requirement changed");
		req.setStatus("status - onhold");
		assertTrue(reqservice.addRequirement(req, "Prj10") instanceof String);
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

//	@Test
//	void deleteReqTest() throws ProjectNotFoundException {
//		assertTrue(reqservice.deleteReq("Prj2Req3", "Prj2") instanceof String);
//	}

}
