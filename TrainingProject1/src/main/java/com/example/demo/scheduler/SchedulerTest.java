/**
* 	@author RITIKA M
*/
package com.example.demo.scheduler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.controller.DashboardController;
import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.DefectCount;
import com.example.demo.model.Project;
import com.example.demo.model.TestCaseCount;
import com.example.demo.service.DefectService;
import com.example.demo.service.ProjectService;
import com.example.demo.service.TestCaseService;

@Component
public class SchedulerTest {
	
	@Autowired
	private TestCaseService testcaseService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired 
	private MongoTemplate mongo;
	
	@Autowired
	private DefectService defectService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerTest.class);
	
	private List<Project> projects;
	private List<TestCaseCount> testcaseCounts=new ArrayList<TestCaseCount>();
	private DefectCount defectCounts;
	
	@PostConstruct
	public void onstart() throws ProjectNotFoundException {
		projects=projectService.viewProjects();
		
	}

	@Scheduled(cron="0 15 21 * * *")
	public void setStartCount() throws ProjectNotFoundException {
		LOGGER.info("Scheduler started to set start count of day");
		defectCounts=new DefectCount();
		for(int i = 0; i < projects.size(); i++) {
			TestCaseCount testcaseCount=new TestCaseCount();
			String projId = projects.get(i).getProjectId();
			int startCount=testcaseService.getOpenTestcaseCount(projId);
			testcaseCount.setProject_id(projId);
			testcaseCount.setStartCount(startCount);
			testcaseCount.setEndCount(0);
			testcaseCounts.add(testcaseCount);
		}
		int defectStartCount=defectService.openDefectsCount();
		defectCounts.setStartCount(defectStartCount);
		
	}
	@Scheduled(cron="0 16 21 * * *")
	public void setEndCount() {
		LOGGER.info("Scheduler started to set end count of day");
		for(int i = 0; i < projects.size(); i++) {
			String projId = projects.get(i).getProjectId();
			int endCount=testcaseService.getOpenTestcaseCount(projId);
			testcaseCounts.get(i).setEndCount(endCount);
			testcaseCounts.get(i).setTimestamp(LocalDateTime.now());
		}
		for(TestCaseCount t: testcaseCounts) {
			mongo.save(t);
			
		}
		int defectEndCount=defectService.openDefectsCount();
		defectCounts.setEndCount(defectEndCount);
		defectCounts.setTimestamp(LocalDateTime.now());
		mongo.save(defectCounts);
		
}}
