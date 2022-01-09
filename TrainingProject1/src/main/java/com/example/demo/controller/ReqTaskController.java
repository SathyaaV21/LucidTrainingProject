package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.RequirementSummarizationModel;
import com.example.demo.model.TaskModel;
import com.example.demo.service.ReqTaskService;

@RestController
@RequestMapping("/api/v1")
public class ReqTaskController {
//this is ramapriya project
	@Autowired
	private ReqTaskService reqtaskservice;
	
	@PostMapping("/createsum")
	public String createreqSum(@RequestBody RequirementSummarizationModel reqsummodel) {
		reqtaskservice.createreqSum(reqsummodel);
		return "Requirement Summarization is created";
	}
	
	@PostMapping("/{reqId}/addtask")
	public String addTask(@PathVariable String reqId, @RequestBody TaskModel taskmodel) {
		reqtaskservice.addTask(reqId,taskmodel);
		return "Task is added";
	}
}
