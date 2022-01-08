package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.RequirementSummarizationModel;
import com.example.demo.model.TaskHistory;
import com.example.demo.model.TaskModel;

@Service
public class ReqTaskService {

	@Autowired
	private MongoTemplate mongotemplate;
	@Autowired
	private TaskService taskservice;
	List<TaskModel> reqtaskCollection = new ArrayList<TaskModel>();
	public void createreqSum(RequirementSummarizationModel reqsummodel) {
		reqsummodel.setCompletionPercentage(0);
		mongotemplate.save(reqsummodel);
	}

	public void addTask(String reqId, TaskModel newtask) {

		taskservice.saveTask(newtask);
		RequirementSummarizationModel reqsummodel = mongotemplate.findById(reqId, RequirementSummarizationModel.class);
		if (reqsummodel.getReqTasks() != null) {
			reqtaskCollection = reqsummodel.getReqTasks();
		}
			
		reqtaskCollection.add(newtask);
		reqsummodel.setReqTasks(reqtaskCollection);
		mongotemplate.save(reqsummodel);
		//reqtaskCollection.clear();
	}

}
