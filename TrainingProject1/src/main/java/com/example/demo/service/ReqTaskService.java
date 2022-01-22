/**
 * @author Ramapriya
 */

package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.RequirementSummarizationModel;
import com.example.demo.model.TaskHistory;
import com.example.demo.model.TaskModel;

@Service
public class ReqTaskService {
	
	@Autowired
	private MongoTemplate mongotemplate;
	private static final Logger LOGGER = LoggerFactory.getLogger(ReqTaskService.class);
	List<TaskModel> reqtaskCollection = new ArrayList<TaskModel>();

	/**
	 * Service to create requirement summarization
	 * @param reqsummodel
	 * @return String
	 */
	public String createreqSum(RequirementSummarizationModel reqsummodel) {
		LOGGER.info("Summarization created");
		reqsummodel.setCompletionPercentage(0);
		reqsummodel.setNo_of_task_completed(0);
		reqsummodel.setNo_of_task_notcompleted(0);
		reqsummodel.setNo_of_tasks(0);
		mongotemplate.save(reqsummodel);
		return "Summarization created";
	}

	/**
	 * Service to update summarization
	 * @param reqId
	 * @param taskmodel
	 */
	public void updateSum(String reqId, TaskModel taskmodel) {
		RequirementSummarizationModel reqsummodel = this.getreqSum(reqId);
		List<TaskModel> tasks = this.getallreqTasks(reqId);
		List<TaskModel> empty = new ArrayList<TaskModel>();
		for (TaskModel i : tasks) {
			if (i.getTaskId().equals(taskmodel.getTaskId())) {
				empty.add(taskmodel);
			}
			else
				empty.add(i);
		}
		reqsummodel.setReqTasks(empty);
		if (taskmodel.getTaskStatus() == "Completed") {
			reqsummodel.setNo_of_task_completed(reqsummodel.getNo_of_task_completed() + 1);
			reqsummodel.setNo_of_task_notcompleted(reqsummodel.getNo_of_task_notcompleted() - 1);
		}
		reqsummodel.setCompletionPercentage((100*reqsummodel.getNo_of_task_completed() )/ reqsummodel.getNo_of_tasks());
		LOGGER.info("Task has been created");
		mongotemplate.save(reqsummodel);
	}

	/**
	 * Service to get all tasks of the specific requirement
	 * @param reqId
	 * @return All the tasks of that requirement
	 */
	public List<TaskModel> getallreqTasks(String reqId) {
		RequirementSummarizationModel reqsummodel = mongotemplate.findById(reqId, RequirementSummarizationModel.class);
		reqtaskCollection = reqsummodel.getReqTasks();
		LOGGER.info("Creating list of tasks for the requirement");
		return reqtaskCollection;
	}

	/**
	 * Service to get requirement summarization of all the available requirements 
	 * @return All the summarizations of the requirements
	 */
	
	public List<RequirementSummarizationModel> getallreqSum() {
		LOGGER.info("Getting all the requirement summarizations");
		return mongotemplate.findAll(RequirementSummarizationModel.class);
	}

	/**
	 * Service to view the specific requirement summarization
	 * @param reqId
	 * @return 
	 */
	public RequirementSummarizationModel getreqSum(String reqId) {
		LOGGER.info("Getting the summarization for the provided requirement");
		return mongotemplate.findById(reqId, RequirementSummarizationModel.class);
	}
}
