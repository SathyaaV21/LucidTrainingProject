package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ProjectNotFoundException;
import com.example.model.ReqHolder;
import com.example.model.Requirement;
import com.example.service.RequirementService;

@RequestMapping("/api/v1")
@RestController
public class RequirementController {

	@Autowired
	private RequirementService reqservice;

	/**
	 * Method to add Requirement
	 *
	 * @param Project Id,Requirementmodel List
	 * @return Respective status of added Requirements.
	 */

	@PostMapping("requirement/{projectId}")
	public void createRequirement(@PathVariable String projectId, @RequestBody Requirement requirement) {
		reqservice.addRequirement(requirement, projectId);
	}

	/**
	 * Method to Get all Requirements
	 * 
	 * @return List of requirements from the database.
	 */

	@GetMapping("/requirement")
	public List<ReqHolder> getReq() {
		return reqservice.viewReq();
	}

	/**
	 * Method to Delete Requirement
	 *
	 * 
	 * @param Project Id,Requirement id
	 * @return Respective status of deleted requirement.
	 * @throws ProjectNotFoundException
	 */

	@DeleteMapping("testcase/{projectId}/{requirementId}")
	public void deleteTestcase(@PathVariable String projectId, @PathVariable String requirementId)
			throws ProjectNotFoundException {
		reqservice.deleteReq(requirementId, projectId);

	}

	/**
	 * Method to update Requirements
	 *
	 * 
	 * @param Requirement Id,Project Id and Requirement Model
	 * @return Respective status of Requirement updated.
	 */
	@PutMapping("requirement/{projectId}/{requirementId}")
	public void updateReq(@PathVariable String projectId, @PathVariable String requirementId,
			@RequestBody Requirement requirement) {
		reqservice.updateReq(requirement, requirementId, projectId);
	}

}
