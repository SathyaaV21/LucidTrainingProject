package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.RequirementSummarizationModel;
import com.example.demo.model.TaskModel;

@SpringBootTest
class ReqTaskServiceTest {
	
	@Autowired
	private ReqTaskService reqservice; 
	
	@Test
	void createreqSumTest() {
		RequirementSummarizationModel reqsummodel=new RequirementSummarizationModel();
		reqsummodel.setReq_Id("Prj5Req13");
		assertTrue(reqservice.createreqSum(reqsummodel) instanceof String);
	}

	@Test
	void getallreqTasks() {
		assertTrue(reqservice.getallreqTasks("Prj5Req13").get(0) instanceof TaskModel);
	}
	
	@Test
	void getallreqSumTest() {
		assertTrue(reqservice.getallreqSum().get(0) instanceof RequirementSummarizationModel);
	}
	
	@Test
	void getreqSumTest() {
		assertTrue(reqservice.getreqSum("Prj5Req13") instanceof RequirementSummarizationModel);
	}
}
