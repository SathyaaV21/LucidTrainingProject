package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.TaskModel;
import com.example.demo.model.DatabaseSequence;
import com.example.demo.model.TaskHistory;

@Service
public class TaskService {
	
	@Autowired
    private SequenceGeneratorService service;
	
	@Autowired
	private MongoTemplate mongotemplate;
		
	/**
 	* Service to create to new task
 	* @param newtask
 	*/
	public void saveTask(TaskModel newtask) {
		newtask.setTaskId("task-"+service.getCount(DatabaseSequence.getSequenceName()));
		newtask.setTaskStatus("New");
		newtask.setRiskAnalysis("No risk analysed");
		newtask.setTodo(newtask.getEffort());
		mongotemplate.save(newtask);
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
	public TaskModel updateTask(String taskId, TaskModel oldtask) {
		TaskModel taskmodel = mongotemplate.findById(taskId, TaskModel.class);
		if (taskmodel != null) {
			mongotemplate.save(oldtask);
			return oldtask;
		} else {
			throw new TaskNotFoundException("Task ID " + taskId + " is not found");
		}
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
	/*public TaskModel getTask(int taskId) {
		// TODO Auto-generated method stub
		//TaskModel taskmodel = 
		return mongotemplate.findById(taskId, TaskModel.class);
	}*/
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
