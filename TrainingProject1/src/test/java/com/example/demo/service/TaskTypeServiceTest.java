package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.TaskTypeModel;

class TaskTypeServiceTest {

	@Autowired
	private TaskTypeService tasktypeservice;
	
	@Test
	void savetaskTypetest() {
		TaskTypeModel tasktype=new TaskTypeModel();
		tasktype.setTasktypeId(1);
		tasktype.setTasktypeDescription("Testing");
		tasktype.setTasktypeName("Testing");
		assertTrue(tasktypeservice.savetaskType(tasktype) instanceof String);
	}
   
	@Test
	void viewtaskTypeTest() {
		assertTrue(tasktypeservice.viewtaskType().get(0) instanceof TaskTypeModel);
	}
	
	@Test
	void deletetaskTypeTest() {
		assertTrue(tasktypeservice.deletetaskType(1) instanceof String);
	}
}
