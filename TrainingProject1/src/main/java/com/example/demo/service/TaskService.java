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
import com.example.demo.model.Sequence;
import com.example.demo.model.TaskHistory;
import com.example.demo.model.TaskModel;
import com.example.demo.model.TaskTypeModel;

@Service
public class TaskService {
	
	@Autowired
    private SequenceGenService service;
	
	@Autowired
	private MongoTemplate mongotemplate;
		
	/**
 	* Service to create to new task
 	* @param newtask
 	*/
	public String saveTask(TaskModel newtask) {
		newtask.setTaskId("task-"+service.getCount(Sequence.getSequenceName3()));
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
		return "Task added";
	}

	/**
	 * Service to update todo in task 
	 * @param taskid
	 * @throws TaskNotFoundException
	 */
	public TaskModel updateTodo(String taskid, TaskModel taskmodel) {
		TaskModel task = mongotemplate.findById(taskid,TaskModel.class);
		if (task != null) {
			List<TaskHistory> taskhistoryCollection=new ArrayList<TaskHistory>();
			TaskHistory taskhistory=new TaskHistory();
			taskhistory.setTodoBefore(task.getTodo());
			task.setTodo(task.getEffort()-taskmodel.getTodo());
			mongotemplate.save(task);
			TaskModel newtask = mongotemplate.findById(taskid,TaskModel.class);
			int curtodo=taskmodel.getTodo();
			taskhistory.setTodoNow(task.getEffort()-taskmodel.getTodo());
			Date curDate=new Date();
			taskhistory.setDateandTime(curDate);
			newtask.setTaskStatus("In Progress");
			newtask.setRiskAnalysis("Task is on schedule");
			if (curtodo==newtask.getEffort()) {
				newtask.setTaskStatus("Completed");
			}
			if(task.getTaskhistory()!=null) {
				taskhistoryCollection=task.getTaskhistory();
			}
			taskhistoryCollection.add(taskhistory);
			newtask.setTaskhistory(taskhistoryCollection);
			mongotemplate.save(newtask);
			newtask.setRiskAnalysis(this.riskNotification(taskhistory, newtask));
			mongotemplate.save(newtask);
			return newtask;
			
		} else {
			throw new TaskNotFoundException("Task ID " + taskid + " is not found");
		}
	
	}
	
	public String riskNotification(TaskHistory taskhistory,TaskModel newtask) {
		long difference=newtask.getStartDate().getTime()-newtask.getEndDate().getTime();
		float daysBetween=(difference/(1000*60*60*24));
		float efficiency=newtask.getEffort()/daysBetween;
		if(newtask.getTodo()-taskhistory.getTodoBefore()==efficiency) {
			return "Task is delayed";
		}
		return null;
	}
	
	/**
	 * Service to update task
	 * @param taskId
	 * @param oldtask
	 * @return oldtask
	 * @throws TaskNotFoundException
	 */
	public void updateTask( Map<String,String> oldtask,String taskId) {
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
			throw new TaskNotFoundException("Task ID " + taskId + " is not found");
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
			throw new TaskNotFoundException("Task ID " + taskid + " is not found");
		}

	}

	public List<TaskHistory> gettaskHistory(String taskid){
		TaskModel taskmodel=mongotemplate.findById(taskid,TaskModel.class);
		if (taskmodel != null) {
			return taskmodel.getTaskhistory();
		}
		else {
			throw new TaskNotFoundException("Task ID " + taskid + " is not found");
		}
		
	}

	

}
