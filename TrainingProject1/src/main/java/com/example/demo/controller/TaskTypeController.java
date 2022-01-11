package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TaskTypeModel;
import com.example.demo.service.TaskTypeService;

@RestController
@RequestMapping("/api/v1/tasktype")

public class TaskTypeController {

	@Autowired
	private TaskTypeService tasktypeservice;
	
	/**
	 * Controller method to create task type
	 * @param newtype
	 * @return String
	 */
	@PostMapping("/create")
	public String createtaskType(@RequestBody TaskTypeModel newtype) {
		tasktypeservice.savetaskType(newtype);
		return "Task Type added";
	}
	
	/**
	 * Controller method to view available task types
	 * @return Call Service method viewTaskType
	 */
	@GetMapping("/view")
	public List<TaskTypeModel> gettaskType(){
		return tasktypeservice.viewtaskType();
	}
	
	/**
	 * Controller method to delete task type
	 * @param tasktypeid
	 * @return String
	 */
	@DeleteMapping("/delete/{tasktypeid}")
	public String deletetaskType(@PathVariable int tasktypeid) {
		tasktypeservice.deletetaskType(tasktypeid);
		return "Task Type is deleted";
	}
}
