 /**
 * @author Ramapriya
 */

package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.ReqHolder;
import com.example.demo.model.Requirement;
import com.example.demo.model.RequirementSummarizationModel;
import com.example.demo.model.TaskHistory;
import com.example.demo.model.TaskModel;
import com.example.demo.model.TaskTypeModel;

@Service
public class TaskService {
	
	@Autowired
	private ReqTaskService reqtaskservice;
	
	@Autowired
	private MongoTemplate mongotemplate;
	
	/**
 	* Service to create to new task
 	* @param newtask
 	*/
	public String saveTask(TaskModel newtask, String reqId, String prjId) {
		List<TaskModel> reqtaskCollection = new ArrayList<TaskModel>();
		ReqHolder req=mongotemplate.findById(prjId,ReqHolder.class);
		List<Requirement> requir=req.getRequirement();
		List<Requirement> empty=new ArrayList<Requirement>();
		for(Requirement i:requir) {
			if(i.getRequirementId().equals(reqId)) {
				newtask.setTaskId(reqId+"tsk"+Integer.toString(i.getTaskCount()));
				i.setTaskCount(i.getTaskCount()+1);
				empty.add(i);
			}
			else
				empty.add(i);
		}
		req.setRequirement(empty);
		mongotemplate.save(req);
		newtask.setTaskStatus("New");
		newtask.setRiskAnalysis("No risk analysed");
		newtask.setTodo(newtask.getEffort()); 
		List<TaskTypeModel> typeList=mongotemplate.findAll(TaskTypeModel.class); 
		List<String> a=new ArrayList<>();
		for(TaskTypeModel task:typeList)
			a.add(task.getTasktypeName());
		if (!(a.contains(newtask.getTaskType()))) {
			throw new BadRequestException("Task type is invalid");
		} 
		mongotemplate.save(newtask);
		RequirementSummarizationModel reqsummodel = mongotemplate.findById(reqId, RequirementSummarizationModel.class);
		if (reqsummodel.getReqTasks() != null) {
			reqtaskCollection = reqsummodel.getReqTasks();
		}
		reqtaskCollection.add(newtask);
		reqsummodel.setNo_of_tasks(reqsummodel.getNo_of_tasks()+1);
		reqsummodel.setNo_of_task_notcompleted(reqsummodel.getNo_of_task_notcompleted()+1);
		reqsummodel.setReqTasks(reqtaskCollection);
		mongotemplate.save(reqsummodel);
		return "Task added";
	}

	/**
	 * Service to update todo in task 
	 * @param taskid
	 * @throws TaskNotFoundException
	 */
	public String updateTodo(String reqId,String taskid, TaskModel taskmodel) {
		TaskModel task = mongotemplate.findById(taskid,TaskModel.class);
		if (task != null && !(task.getTaskStatus().equals("Completed"))) {
			List<TaskHistory> taskhistoryCollection=new ArrayList<TaskHistory>();
			TaskHistory taskhistory=new TaskHistory();
			taskhistory.setTodoBefore(task.getTodo());
			task.setTodo(task.getTodo()-taskmodel.getTodo());
			mongotemplate.save(task);
			TaskModel newtask = mongotemplate.findById(taskid,TaskModel.class);
			int curtodo=newtask.getTodo();
			taskhistory.setTodoNow(task.getTodo());
			Date curDate=new Date();
			taskhistory.setDateandTime(curDate);
			newtask.setTaskStatus("In Progress");
			if (curtodo<=0) {
				newtask.setTaskStatus("Completed");
			}
			if(task.getTaskhistory()!=null) {
				taskhistoryCollection=task.getTaskhistory();
			}
			taskhistoryCollection.add(taskhistory);
			newtask.setTaskhistory(taskhistoryCollection);
			mongotemplate.save(newtask);
			newtask.setRiskAnalysis(this.riskNotification(taskhistory, newtask,taskmodel));
			mongotemplate.save(newtask);
			reqtaskservice.updateSum(reqId, newtask);
			return "Todo has been updated";
			
		} else {
			throw new BadRequestException("Task ID " + taskid + " is not found");
		}
	
	}
	
	public String riskNotification(TaskHistory taskhistory,TaskModel newtask, TaskModel taskmodel) {
		long difference=newtask.getStartDate().getTime()-newtask.getEndDate().getTime();
		float daysBetween=(difference/(1000*60*60*24));
		float efficiency=newtask.getEffort()/daysBetween;
		if(taskmodel.getTodo()<efficiency) {
			return "Task is delayed";
		}
		else
			return "Task is on schedule";
	}
	
	/**
	 * Service to update task
	 * @param taskId
	 * @param oldtask
	 * @return oldtask
	 * @throws TaskNotFoundException
	 */
	public String updateTask( Map<String,String> oldtask,String taskId) {
		TaskModel taskmodel = mongotemplate.findById(taskId, TaskModel.class);   
		if(taskmodel==null) {
			throw new BadRequestException("Task ID " + taskId + " is not found");} 
		  Query query = new Query();
	        query.addCriteria(Criteria.where("taskId").is(taskmodel.getTaskId()));
	        Update update = new Update();  
	        for (Map.Entry task : oldtask.entrySet()) {
	        	if(task.getKey().equals("taskName")||task.getKey().equals("taskDescription")||task.getKey().equals("assignedTo")) {
	        		update.set((String)task.getKey(),task.getValue());
	        	}
	        	else {
	        		throw new BadRequestException("Please provide the correct fields to update");
	        	}
	        }
	         mongotemplate.findAndModify(query, update, TaskModel.class);
	         return "Task updated";
}
	

	/**
	 * Service to view all the tasks in the DB
	 * @return tasks in DB
	 */
	public List<TaskModel> viewTasks() {
		return mongotemplate.findAll(TaskModel.class);
	}

	public TaskModel getTask(String taskId) {
		TaskModel taskmodel = mongotemplate.findById(taskId, TaskModel.class);
		if (taskmodel != null) {
		return taskmodel;
	}
		else {
			throw new BadRequestException("Task ID " + taskId + " is not found");
		}
	}
	
	/**
	 * Service to delete a task
	 * @param Id
	 * @throws TaskNotFoundException
	 */
	public void deleteTask(String taskid){
		TaskModel taskmodel = mongotemplate.findById(taskid, TaskModel.class);
		if (taskmodel != null) {
			Query query = new Query();
			query.addCriteria(Criteria.where("taskid").is(taskid));
			mongotemplate.remove(query, TaskModel.class);

		} else {
			throw new BadRequestException("Task ID " + taskid + " is not found");
		}

	}

	public List<TaskHistory> gettaskHistory(String taskid){
		TaskModel taskmodel=mongotemplate.findById(taskid,TaskModel.class);
		if (taskmodel != null) {
			return taskmodel.getTaskhistory();
		}
		else {
			throw new BadRequestException("Task ID " + taskid + " is not found");
		}
		
	}

	

}
