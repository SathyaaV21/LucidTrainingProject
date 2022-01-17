/**
 * @author Ramapriya
 */

package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.RequirementSummarizationModel;
import com.example.demo.model.TaskHistory;
import com.example.demo.model.TaskModel;
import com.example.demo.service.ReqTaskService;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

	@Autowired
	private ReqTaskService reqtaskservice;
	@Autowired
	private TaskService taskservice;


	/**
	 * Controller method to create task
	 * 
	 * @param newtask
	 * @return String
	 */
	@PostMapping("/{reqId}/addtask")
	public String addTask(@PathVariable String reqId, @RequestBody TaskModel taskmodel) {
		reqtaskservice.addTask(reqId, taskmodel);
		return "Task is added";
	}

	/**
	 * Controller method to update task
	 * 
	 * @param task
	 * @param taskId
	 * @return String and HTTP status update
	 * @throws TaskNotFoundException
	 */
	@PutMapping("/updatetask/{taskId}")
	public ResponseEntity<Object> updateTask(@RequestBody Map<String,String> task, @PathVariable String taskId) {
		taskservice.updateTask(task, taskId);
		return new ResponseEntity<Object>("Task of task id " + taskId + " has been updated", HttpStatus.OK);
	}

	/**
	 * Controller method to update todo
	 * 
	 * @param taskid
	 * @return String and HTTP status update
	 * @throws TaskNotFoundException
	 */
	@PutMapping("/{reqId}/updatetodo/{taskid}")
	public ResponseEntity<Object> updateTodo(@PathVariable String reqId, @RequestBody TaskModel taskmodel,
			@PathVariable String taskid) {
		taskservice.updateTodo(taskid, taskmodel);
		reqtaskservice.updateSum(reqId, taskmodel);

		return new ResponseEntity<Object>("TODO of task " + taskid + " has been updated", HttpStatus.OK);
	}

	/**
	 * Controller method to view all tasks in DB
	 * 
	 * @return Calls Service method getAllTasks
	 */
	@GetMapping("/{reqId}/getalltasks")
	public List<TaskModel> getAllTasks(@PathVariable String reqId) {
		return reqtaskservice.getallreqTasks(reqId);

	}

	/**
	 * Controller method to get all the requirement summarizations
	 * 
	 * @return Requirement summarization model
	 */
	@GetMapping("/getallreqsum")
	public List<RequirementSummarizationModel> getallreqSum() {
		return reqtaskservice.getallreqSum();
	}
	
	/**
	 * Controller method to get requirement summarization of specific requirement
	 * @param reqId
	 * @return Requirement summarization model
	 */
	@GetMapping("/{reqId}/getsum")
	public RequirementSummarizationModel getreqSum(@PathVariable String reqId) {
		return reqtaskservice.getreqSum(reqId);
	}
	
	/**
	 * Controller method to get task by Id
	 * 
	 * @param taskid
	 * @return TaskModel
	 * @throws TaskNotFoundException
	 */
	@GetMapping("/gettask/{taskid}")
	public TaskModel getTask(@PathVariable String taskid) {
		return taskservice.getTask(taskid);
	}

	/**
	 * Controller method to get task history
	 * 
	 * @param taskid
	 * @return
	 */
	@GetMapping("/gettaskhistory/{taskid}")
	public List<TaskHistory> viewtaskHistory(@PathVariable String taskid) {
		return taskservice.gettaskHistory(taskid);

	}

	/**
	 * Controller method to delete task by Id
	 * 
	 * @param taskid
	 * @return String and HTTP Status update
	 * @throws TaskNotFoundException
	 */
	@DeleteMapping("/delete/{taskid}")
	public ResponseEntity<Object> deleteTask(@PathVariable String taskid) {
		taskservice.deleteTask(taskid);
		return new ResponseEntity<Object>("Deleted task of task" + taskid, HttpStatus.OK);
	}

}
