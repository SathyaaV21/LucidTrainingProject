package com.example.demo.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.DefectCount;
import com.example.demo.model.Project;
import com.example.demo.model.TestCaseCount;
import com.example.demo.service.DefectService;
import com.example.demo.service.ProjectService;
import com.example.demo.service.TestCaseService;

@EnableScheduling
public class scheduler {
	
	@Autowired
	private TestCaseService testcaseService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private TestCaseCount testcaseCount;
	
	@Autowired 
	private MongoTemplate mongo;
	
	@Autowired
	private DefectService defectService;
	
	private List<Project> projects;
	private TestCaseCount testcaseCounts;
	private DefectCount defectCounts;
	
	public void onstart() throws ProjectNotFoundException {
		projects=projectService.viewProjects();
		
	}

	@Scheduled(cron="0 6 * * *")
	public void setStartCount() throws ProjectNotFoundException {
		testcaseCounts=new TestCaseCount();
		defectCounts=new DefectCount();
		for(int i = 0; i < 5; i++) {
			String projId = projects.get(i).getProjectId();
			int startCount=testcaseService.getOpenTestcaseCount(projId);
			testcaseCounts.setProject_id(projId);
			testcaseCounts.setStartCount(startCount);
		}
		int defectStartCount=defectService.openDefectsCount();
		defectCounts.setStartCount(defectStartCount);
		
	}
	@Scheduled(cron="0 18 * * *")
	public void setEndCount() {
		for(int i = 0; i < 5; i++) {
			String projId = projects.get(i).getProjectId();
			int endCount=testcaseService.getOpenTestcaseCount(projId);
			testcaseCounts.setEndCount(endCount);
			mongo.save(testcaseCounts);
		}
		int defectEndCount=defectService.openDefectsCount();
		defectCounts.setEndCount(defectEndCount);
		mongo.save(defectCounts);
		
}

}
