package com.example.demo.controller;
import java.util.List;

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
import com.example.demo.model.TaskHistory;
import com.example.demo.model.TaskModel;

import com.example.demo.service.TaskService;
@RestController
@RequestMapping("/api/v1")
public class TaskController {
	
	@Autowired
	private TaskService taskservice;
	
	/**
	 * Controller method to create task
	 * @param newtask
	 * @return String
	 */
	@PostMapping("/createtask")
	public String createTask(@RequestBody TaskModel newtask) {
		taskservice.saveTask(newtask);
		return "Task added";
	}
	
	/**
	 * Controller method to update task
	 * @param task
	 * @param taskId
	 * @return String and HTTP status update
	 * @throws TaskNotFoundException
	 */
	@PutMapping("/updatetask/{taskId}")
    public ResponseEntity<Object> updateTask(@RequestBody TaskModel task, @PathVariable String  taskId){
        taskservice.updateTask(taskId, task);
        return new ResponseEntity<Object>("Task of task id "+taskId+" has been updated", HttpStatus.OK);
    }
	
	/**
	 * Controller method to update todo
	 * @param taskid
	 * @return String and HTTP status update
	 * @throws TaskNotFoundException
	 */
	@PutMapping("/updatetodo/{taskid}")
	public ResponseEntity<Object> updateTodo(@PathVariable String taskid, @RequestBody TaskModel taskmodel) {
		taskservice.updateTodo(taskid,taskmodel);
		return new ResponseEntity<Object>("TODO of task "+taskid+" has been updated", HttpStatus.OK);
	}
	
	/**
	 * Controller method to view all tasks in DB
	 * @return Calls Service method getAllTasks
	 */
	@GetMapping("/getalltasks")
	public List<TaskModel> getAllTasks() {
		return taskservice.viewTasks();
	}
	
	/**
	 * Controller method to get task by Id
	 * @param taskid
	 * @return String and HTTP Status update
	 * @throws TaskNotFoundException
	 */
	@GetMapping("/gettask/{taskid}")
	public TaskModel getTask(@PathVariable String taskid){
		return taskservice.getTask(taskid);
	}
	
	/**
	 * Controller method to get task history
	 * @param taskid
	 * @return
	 */
	@GetMapping("/gettaskhistory/{taskid}")
	public List<TaskHistory> viewtaskHistory(@PathVariable String taskid){
		return taskservice.gettaskHistory(taskid);
		
	}
	
	/**
	 * Controller method to delete task by Id
	 * @param taskid
	 * @return String and HTTP Status update
	 * @throws TaskNotFoundException
	 */
	@DeleteMapping("/delete/{taskid}")
	public ResponseEntity<Object> deleteTask(@PathVariable String taskid){
		taskservice.deleteTask(taskid);
		return new ResponseEntity<Object>("Deleted task of task"+taskid, HttpStatus.OK);
	}
	
}
