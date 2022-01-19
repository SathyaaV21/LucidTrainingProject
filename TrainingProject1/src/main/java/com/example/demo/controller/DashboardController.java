/**
* 	@author RITIKA M
*/

package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);
	
	
	/**
	 * Method to get Requirements traceability matrix (RTM) of all the projects
	 * 
	 * @return List<RTM> with respective status and information.
	 * @throws ProjectNotFoundException handles Exception
	 */	
	@GetMapping("/rtm")
	public List<RTM> rtm() throws ProjectNotFoundException
	{
		LOGGER.info("Generating RTM");
		return dashboardService.getRTM();
	}
	
	
	/**
	 * Method to get Test case Trend (Start count and End count of open testcases)
	 * 
	 * @return List<TestCaseCount> with respective start count and end count.
	 * @throws ProjectNotFoundException handles Exception
	 */
	@GetMapping("/testcaseprogress")
	public List<TestCaseCount> testcaseProgress(){
		LOGGER.info("Generating TestCase Progress");
		return dashboardService.getTestCaseProgress();
		
	}
	
	
	/**
	 * Method to get Test case Trend (Start count and End count of open testcases)
	 * 
	 * @param Project Id
	 * @return List<TestCaseCount> with respective counts related to that project ID.
	 *@throws ProjectNotFoundException handles Exception
	 */
	@GetMapping("/testcaseprogressbyprojid/{projId}")
	public List<TestCaseCount> testcaseProgressbyProjId(@PathVariable String projId) throws ProjectNotFoundException{
			return dashboardService.getTestCaseProgressbyProjId(projId);
		
	}
	
	/**
	 * Method to get Defects Trend (Start count and End count of open Defects)
	 * 
	 * @return List<DefectCount> with respective start count and end count.
	 */
	@GetMapping("/defectprogress")
	public List<DefectCount> defectProgress(){
		LOGGER.info("Generating Defects Progress");
		return dashboardService.getDefectsProgress();
	}
	
	/**
	 * Method to get open Testcases count with Requirement ID
	 * 
	 * @param RequirementID
	 * @return int Count of open testcases
	 */
	@GetMapping("/opentestcase/{reqId}")
	public String openTestcasesCount(@PathVariable String reqId) {
		return "Open Testcases Count for  "+reqId +": " + dashboardService.getOpenTestCaseCountbyRequirementId(reqId);
	}
	
	

}
