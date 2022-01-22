package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.model.RequirementSummarizationModel;
import com.example.demo.model.TaskHistory;
import com.example.demo.model.TaskModel;

@SpringBootTest
class TaskServiceTest {
	@Autowired
	private TaskService taskservice;
	
	@InjectMocks
	private TaskService taskService;
	
	@Mock
	TaskModel taskModel;
	
	@Mock
	MongoTemplate mongoTemplate;
	@Test
	void saveTaskTest() {
		TaskModel task = new TaskModel();
		task.setAssignedTo("Ramapriya");
		task.setEffort(100);
		task.setTaskName("Testing");
		task.setTaskType("Testing");
		task.setTaskDescription("Testing the module");
		assertTrue(taskservice.saveTask(task, "Prj10Req1", "Prj10") instanceof String);
	}

	@Test
	void updateTaskTest() {
		Map<String, String> taskmap = new HashMap<String, String>();
		taskmap.put("taskName", "Testing");
		taskmap.put("taskDescription", "Testing the module");
		taskmap.put("assignedTo", "Ramapriya");
		assertTrue(taskservice.updateTask(taskmap, "Prj10Req1", "Prj10Req1tsk4") instanceof String);
	}

	@Test
	void updateTodoTest() {
		TaskModel task = new TaskModel();
		task.setTodo(1);
		assertTrue(taskservice.updateTodo("Prj6Req1", "Prj6Req1tsk14", task) instanceof String);
	}

	@Test
	void viewTasksTest() {
		assertTrue(taskservice.viewTasks().get(0) instanceof TaskModel);
	}

	@Test
	void getTaskTest() {
		assertTrue(taskservice.getTask("Prj6Req1tsk14") instanceof TaskModel);
	}

	@Test
	void gettaskHistoryTest() {
		assertTrue(taskservice.gettaskHistory("Prj6Req1tsk14").get(0) instanceof TaskHistory);
	}

	
	@Test
	void deleteTaskTest() {
		TaskModel task=new TaskModel();
		task.setTaskId("Prj10Req1tsk1");
		task.setAssignedTo("Ramapriya");
		task.setEffort(100);
		task.setTaskName("Testing");
		task.setTaskType("Testing");
		task.setTaskStatus("Completed");
		
		RequirementSummarizationModel reqModel= new RequirementSummarizationModel();
		reqModel.setReq_Id("Prj5Req13");
		reqModel.setNo_of_task_completed(10);
		reqModel.setNo_of_tasks(10);
		reqModel.setCompletionPercentage(75.50);
		
		task.setTaskDescription("Testing the module");
		List<TaskModel> tasklist=new ArrayList<TaskModel>();
		tasklist.add(task);
		reqModel.setReqTasks(tasklist);
		Mockito.when(mongoTemplate.findById("Prj10Req1tsk1", TaskModel.class)).thenReturn(task);
		Mockito.when(mongoTemplate.remove(task)).thenReturn(null);
		Mockito.when(mongoTemplate.findById("Prj6Req1",RequirementSummarizationModel.class)).thenReturn(reqModel);
		Mockito.when(mongoTemplate.save(reqModel)).thenReturn(reqModel);
		
		assertTrue(taskService.deleteTask("Prj6Req1", "Prj10Req1tsk1") instanceof String);
	}

}



