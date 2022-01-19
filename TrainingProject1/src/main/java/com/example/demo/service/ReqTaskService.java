/**
 * @author Ramapriya
 */

package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.RequirementSummarizationModel;
import com.example.demo.model.TaskHistory;
import com.example.demo.model.TaskModel;

@Service
public class ReqTaskService {
	
	@Autowired
	private MongoTemplate mongotemplate;

	List<TaskModel> reqtaskCollection = new ArrayList<TaskModel>();

	public String createreqSum(RequirementSummarizationModel reqsummodel) {
		reqsummodel.setCompletionPercentage(0);
		reqsummodel.setNo_of_task_completed(0);
		reqsummodel.setNo_of_task_notcompleted(0);
		reqsummodel.setNo_of_tasks(0);
		mongotemplate.save(reqsummodel);
		return "Summarization created";
	}

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
		mongotemplate.save(reqsummodel);
	}

	public List<TaskModel> getallreqTasks(String reqId) {
		RequirementSummarizationModel reqsummodel = mongotemplate.findById(reqId, RequirementSummarizationModel.class);
		reqtaskCollection = reqsummodel.getReqTasks();
		return reqtaskCollection;
	}

	public List<RequirementSummarizationModel> getallreqSum() {
		return mongotemplate.findAll(RequirementSummarizationModel.class);
	}

	public RequirementSummarizationModel getreqSum(String reqId) {
		return mongotemplate.findById(reqId, RequirementSummarizationModel.class);
	}
}
