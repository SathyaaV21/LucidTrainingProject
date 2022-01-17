/**
 * @author Ramapriya
 */

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
//Checking source tree
	@Autowired
	private MongoTemplate mongotemplate;
	@Autowired
	private TaskService taskservice;
	List<TaskModel> reqtaskCollection = new ArrayList<TaskModel>();
	
	public void createreqSum(RequirementSummarizationModel reqsummodel) {
		reqsummodel.setCompletionPercentage(0);
		reqsummodel.setNo_of_task_completed(0);
		reqsummodel.setNo_of_task_notcompleted(0);
		reqsummodel.setNo_of_tasks(0);
		mongotemplate.save(reqsummodel);
	}

	public void addTask(String reqId, TaskModel newtask) {
		taskservice.saveTask(newtask);
		RequirementSummarizationModel reqsummodel = mongotemplate.findById(reqId, RequirementSummarizationModel.class);
		if (reqsummodel.getReqTasks() != null) {
			reqtaskCollection = reqsummodel.getReqTasks();
		}
		reqtaskCollection.add(newtask);
		reqsummodel.setNo_of_tasks(reqsummodel.getNo_of_tasks()+1);
		reqsummodel.setNo_of_task_notcompleted(reqsummodel.getNo_of_task_notcompleted()+1);
		reqsummodel.setReqTasks(reqtaskCollection);
		mongotemplate.save(reqsummodel);
	}
	
	
	public void updateSum(String reqId, TaskModel taskmodel) {
		RequirementSummarizationModel reqsummodel = mongotemplate.findById(reqId, RequirementSummarizationModel.class);
		if (taskmodel.getTaskStatus()=="Completed") {
			reqsummodel.setNo_of_task_completed(reqsummodel.getNo_of_task_completed()+1);
			reqsummodel.setNo_of_task_notcompleted(reqsummodel.getNo_of_task_notcompleted()-1);
		}
		reqsummodel.setCompletionPercentage((reqsummodel.getNo_of_task_completed()/reqsummodel.getNo_of_tasks())*100);
	}
    public List<TaskModel> getallreqTasks(String reqId) {
    	RequirementSummarizationModel reqsummodel = mongotemplate.findById(reqId, RequirementSummarizationModel.class);
		reqtaskCollection=reqsummodel.getReqTasks();
    	return reqtaskCollection;
    }
    
    public List<RequirementSummarizationModel> getallreqSum(){
    	return mongotemplate.findAll(RequirementSummarizationModel.class);
    }

	public RequirementSummarizationModel getreqSum(String reqId) {
		// TODO Auto-generated method stub
		return mongotemplate.findById(reqId, RequirementSummarizationModel.class);
	}
}
