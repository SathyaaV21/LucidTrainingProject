/**
 * @author Manju
 * 
 */
package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.ReqHolder;
import com.example.demo.model.Requirement;

@SpringBootTest

class RequirementServiceTest {
	@Mock
	private MongoTemplate mongotemplate;

	@Spy

	@InjectMocks
	private RequirementService reqservice;

	@Test
	void addRequirement() throws ProjectNotFoundException {
		ReqHolder reqHolder = new ReqHolder();
		reqHolder.setProjectId("Prj24");

		Requirement req = new Requirement();
		req.setRequirementId("Prj7Req1");
		req.setRequirementDescription("Project description");
		req.setStatus("Completed");

		List<Requirement> req1 = new ArrayList<>();
		req1.add(req);

		reqHolder.setRequirement(req1);

		when(mongotemplate.save(reqHolder)).thenReturn(reqHolder);
		assertTrue(reqservice.addRequirement(req, "Prj24") instanceof String);

	}

	@Test
	void viewReq() {
		when(mongotemplate.findAll(ReqHolder.class))
				.thenReturn(Stream.of(new ReqHolder()).collect(Collectors.toList()));
		assertEquals(1, reqservice.viewReq().size());

	}

	@Test
	void updateReqTest() {

		ReqHolder reqHolder = new ReqHolder();
		reqHolder.setProjectId("Prj24");
		Requirement req = new Requirement();
		req.setRequirementId("Prj24Req1");
		req.setRequirementDescription("Project description");
		req.setStatus("Completed");
		List<Requirement> req1 = new ArrayList<>();
		req1.add(req);
		reqHolder.setRequirement(req1);

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is("Prj24"));

		ReqHolder reqHolder1 = new ReqHolder();
		Requirement req2 = new Requirement();
		req2.setRequirementDescription("Create service to Create a new project");
		req2.setStatus("Completed");
		List<Requirement> reqList = new ArrayList<>();
		reqList.add(req2);
		reqHolder1.setRequirement(reqList);

		when(mongotemplate.findById("Prj24", ReqHolder.class)).thenReturn(reqHolder);
		when(mongotemplate.save(reqHolder1)).thenReturn(reqHolder);

		assertTrue(reqservice.updateReq(req2, "Prj24Req1", "Prj24") instanceof String);

	}

	@Test
	void deleteReq() throws ProjectNotFoundException {
		ReqHolder reqHolder = new ReqHolder();
		reqHolder.setProjectId("Prj10");
		Requirement req = new Requirement();
		req.setRequirementId("Prj10Req1");
		req.setRequirementDescription("Project description");
		req.setStatus("Completed");
		List<Requirement> req1 = new ArrayList<>();
		req1.add(req);
		reqHolder.setRequirement(req1);

		Query query = new Query();
		query.addCriteria(Criteria.where("requirementId").is("Prj10Req1"));

		when(mongotemplate.findById("Prj10", ReqHolder.class)).thenReturn(reqHolder);
		when(mongotemplate.findAndRemove(query, Requirement.class)).thenReturn(req);
		when(mongotemplate.save(req)).thenReturn(req);
		assertTrue(reqservice.deleteReq("Prj10Req1", "Prj10") instanceof String);

	}

	@Test
	void deleteSingleReqTest() throws ProjectNotFoundException {

		RequirementService service = new RequirementService();
		ProjectNotFoundException exception = assertThrows(ProjectNotFoundException.class,
				() -> service.deleteReq("Prj10Req1", "Prj10"));
		assertEquals("Project Not Found", exception.getMessage());

	}

}
