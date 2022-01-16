/**
* 	@author RITIKA M
*/

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.DefectCount;
import com.example.demo.model.RTM;
import com.example.demo.model.TestCaseCount;
import com.example.demo.service.DashboardService;

@RequestMapping("/api/v1/dashboard")
@RestController
public class DashboardController {
	
	@Autowired
	private DashboardService dashboardService;
	
	@GetMapping("/rtm")
	public List<RTM> rtm() throws ProjectNotFoundException
	{
		return dashboardService.getRTM();
	}
	
	@GetMapping("/testcaseprogress")
	public List<TestCaseCount> testcaseProgress(){
		return dashboardService.getTestCaseProgress();
		
	}
	
	@GetMapping("/testcaseprogressbyprojid/{projId}")
	public List<TestCaseCount> testcaseProgressbyProjId(@PathVariable String projId) throws ProjectNotFoundException{
			return dashboardService.getTestCaseProgressbyProjId(projId);
		
	}
	

	@GetMapping("/defectprogress")
	public List<DefectCount> defectProgress(){
		return dashboardService.getDefectsProgress();
	}
	
	@GetMapping("/opentestcase/{reqId}")
	public int openTestcasesCount(@PathVariable String reqId) {
		return dashboardService.getOpenTestCaseCountbyRequirementId(reqId);
	}
	
	

}
