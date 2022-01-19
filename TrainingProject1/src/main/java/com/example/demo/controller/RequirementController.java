/**
* 	@author Manju
*/
package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.ReqHolder;
import com.example.demo.model.Requirement;
import com.example.demo.service.RequirementService;

@RequestMapping("/api/v1")
@RestController
public class RequirementController {
	private static final Logger logger = LoggerFactory.getLogger(RequirementController.class);


	@Autowired
	private RequirementService reqservice;

	/**
	 * Method to add Requirement
	 *
	 * @param Project Id,Requirementmodel List
	 * @return Respective status of added Requirements.
	 */

	@PostMapping("requirement/{projectId}")
	public void createRequirement(@PathVariable String projectId,@Valid  @RequestBody Requirement requirement) {
		logger.info("In creating requirement");
		reqservice.addRequirement(requirement, projectId);
	}

	/**
	 * Method to Get all Requirements
	 * 
	 * @return List of requirements from the database.
	 */

	@GetMapping("/requirement")
	public List<ReqHolder> getReq() {
		logger.info("request to view all requirements");
		return reqservice.viewReq();
	}
	/**
	 * Method to update Requirements
	 *
	 * 
	 * @param Requirement Id,Project Id and Requirement Model
	 * @return Respective status of Requirement updated.
	 * @throws ProjectNotFoundException 
	 */
	@PutMapping("requirement/{projectId}/{requirementId}")
	public void updateReq(@PathVariable String projectId, @PathVariable String requirementId,
			@RequestBody Requirement requirement) throws ProjectNotFoundException {
		logger.info("request to update requirement");
		reqservice.updateReq(requirement, requirementId, projectId);
	}

	/**
	 * Method to Delete Requirement
	 *
	 * 
	 * @param Project Id,Requirement id
	 * @return Respective status of deleted requirement.
	 * @throws ProjectNotFoundException
	 */

	@DeleteMapping("requirement/{projectId}/{requirementId}")
	public void deleteRequirement(@PathVariable String projectId, @PathVariable String requirementId)
			throws ProjectNotFoundException {
		logger.info("In deleting requirement");
		reqservice.deleteReq(requirementId, projectId);

	}

	

}
