package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.example.demo.model.RTM;
import com.example.demo.model.TaskModel;
import com.example.demo.model.TestCaseCount;
import com.example.demo.model.DefectCount;
import com.example.demo.model.File;
import com.example.demo.service.DashboardService;

import com.example.demo.exception.ProjectNotFoundException;

@SpringBootTest
public class DashboardTest {
	
	@SpyBean
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private DashboardService dashboardService;
	
	
	@Test
	public void getRTMTest() throws ProjectNotFoundException {
		assertTrue(dashboardService.getRTM().get(0) instanceof RTM);
		
	}
	
	@Test
	public void getTestCaseProgressTest() {
	  assertTrue(dashboardService.getTestCaseProgress().get(0) instanceof
	  TestCaseCount);
	  
	  }
	 
	@Test
	public void getDefectsProgressTest() {
	  assertTrue(dashboardService.getDefectsProgress().get(0) instanceof DefectCount);
	  
	  }
	@Test
	public void getTestCaseProgressbyProjIdTest() throws ProjectNotFoundException {
	  assertTrue(dashboardService.getTestCaseProgressbyProjId("Prj2").get(0) instanceof TestCaseCount);
	  
	  }
	
		/*
		 * @Test public void getOpenTestCaseCountbyRequirementIdTest() {
		 * assertTrue(dashboardService.getOpenTestCaseCountbyRequirementId("Prj4Req1")
		 * instanceof int);
		 * 
		 * }
		 */
	
	
}
