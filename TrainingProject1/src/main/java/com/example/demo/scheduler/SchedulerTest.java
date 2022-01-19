/**
* 	@author RITIKA M
*/
package com.example.demo.scheduler;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
	
	private List<Project> projects;
	private List<TestCaseCount> testcaseCounts=new ArrayList<TestCaseCount>();
	private DefectCount defectCounts;
	
	@PostConstruct
	public void onstart() throws ProjectNotFoundException {
		projects=projectService.viewProjects();
		
	}

	@Scheduled(cron="0 0 14 * * *")
	public void setStartCount() throws ProjectNotFoundException {
		defectCounts=new DefectCount();
		for(int i = 0; i < 5; i++) {
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
	@Scheduled(cron="0 05 14 * * *")
	public void setEndCount() {
		for(int i = 0; i < 5; i++) {
			String projId = projects.get(i).getProjectId();
			int endCount=testcaseService.getOpenTestcaseCount(projId);
			testcaseCounts.get(i).setEndCount(endCount);
		}
		for(TestCaseCount t: testcaseCounts) {
			mongo.save(t);
			
		}
		int defectEndCount=defectService.openDefectsCount();
		defectCounts.setEndCount(defectEndCount);
		mongo.save(defectCounts);
		
}}
