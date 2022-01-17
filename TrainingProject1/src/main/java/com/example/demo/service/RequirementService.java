/**
* 	@author Manju
*/
package com.example.demo.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.ReqHolder;
import com.example.demo.model.Requirement;


import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.ReqHolder;
import com.example.demo.model.Requirement;
import com.example.demo.model.RequirementSummarizationModel;


@Service

public class RequirementService {

	@Autowired
	private MongoTemplate mongotemplate;
	
	@Autowired
	private ReqTaskService reqtaskservice;
	
	/**
	 * Method to add Requirements for the Project in the Database
	 * 
	 * @param the Project id and RequirementModel is passed.
	 * @return status of the Added Requirement .
	 */

	public String addRequirement(Requirement requirement, String projectId) {

		ReqHolder req = new ReqHolder();
		req.setProjectId(projectId);

		ReqHolder reqHolder = mongotemplate.findById(projectId, ReqHolder.class);
		if (reqHolder == null) {
			ReqHolder req_ = new ReqHolder();
			req_.setProjectId(projectId);
			requirement.setRequirementId(projectId + "Req" + Integer.toString(1));
			List<Requirement> reqArr = new ArrayList<>();
			reqArr.add(requirement);
			req_.setRequirement(reqArr);
			mongotemplate.save(req_);

		} else {
			List<Requirement> r = reqHolder.getRequirement();
			requirement.setRequirementId(projectId + "Req" + Integer.toString((r.size() + 1)));
			r.add(requirement);
			req.setRequirement(r);
			mongotemplate.save(req);
		}
		RequirementSummarizationModel reqsummodel=new RequirementSummarizationModel();
		reqsummodel.setReq_Id(requirement.getRequirementId());
		reqtaskservice.createreqSum(reqsummodel);
		return "Requirement added successfully";
	}
	
	/**
	 * Method to get all Requirements for the Project in the Database
	 * 
	 * @return all requirements .
	 * @throws ProjectNotFoundException
	 */

	public List<ReqHolder> viewReq() {

		return mongotemplate.findAll(ReqHolder.class);

	}
	
	/**
	 * Method to Update Requirements for the Project in the Database
	 * 
	 * @param the Requirement id and project id is passed.
	 * @return status of the updated Requirement .
	 */

	public String updateReq(Requirement requirement, String requirementId, String projectId) {

		ReqHolder reqHolder = mongotemplate.findById(projectId, ReqHolder.class);
		List<Requirement> req = reqHolder.getRequirement();

		for (Requirement r : req) {
			if (r.getRequirementId().equals(requirementId)) {
				if(requirement.getRequirementDescription()!=null)
				r.setRequirementDescription(requirement.getRequirementDescription());
				if(requirement.getStatus()!=null)
				r.setStatus(requirement.getStatus());
			}
		}
		reqHolder.setRequirement(req);

		mongotemplate.save(reqHolder);
		return "Requirement updated";
	}

	/**
	 * Method to delete Requirements for the Project in the Database
	 * 
	 * @param the Requirement id and project id is passed.
	 * @return status of the Deleted Requirement .
	 * @throws ProjectNotFoundException
	 */

	public String deleteReq(String requirementId, String projectId) throws ProjectNotFoundException {
		try {
		ReqHolder reqHolder = mongotemplate.findById(projectId, ReqHolder.class);
		if (reqHolder == null) {
			throw new ProjectNotFoundException("req not found");
		}
		List<Requirement> req = reqHolder.getRequirement();

		for (Requirement r : req) {
			if (r.getRequirementId().equals(requirementId)) {
				req.remove(r);
				break;
			}
		}
		reqHolder.setRequirement(req);
		mongotemplate.save(reqHolder);

	}catch(Exception e) {
		throw new ProjectNotFoundException("Project Not Found");

	}
		return "Requirement deleted";

}
}