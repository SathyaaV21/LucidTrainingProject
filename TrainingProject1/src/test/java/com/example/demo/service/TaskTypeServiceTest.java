package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.example.demo.model.TaskTypeModel;

@SpringBootTest
class TaskTypeServiceTest {

	@Mock
	private MongoTemplate mongotemplate;

	@Autowired
	private TaskTypeService service;
	@Spy
	@InjectMocks
	private TaskTypeService tasktypeservice;

	@Test
	void savetaskTypetest() {
		TaskTypeModel tasktype = new TaskTypeModel();
		tasktype.setTasktypeId(3);
		tasktype.setTasktypeDescription("Testing");
		tasktype.setTasktypeName("Testing");
		when(mongotemplate.save(tasktype)).thenReturn(tasktype);
		assertEquals("New Task type saved", tasktypeservice.savetaskType(tasktype));
	}

	@Test
	void viewtaskTypeTest() {
		assertTrue(service.viewtaskType().get(0) instanceof TaskTypeModel);
	}

//
//	  @Test void deletetaskTypeTest() {
//	  assertTrue(tasktypeservice.deletetaskType(1) instanceof String); }
	 
}













