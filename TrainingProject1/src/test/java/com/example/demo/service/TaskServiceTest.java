package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.model.TaskHistory;
import com.example.demo.model.TaskModel;

@SpringBootTest
class TaskServiceTest {
	@Autowired
	private TaskService taskservice;

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
		assertTrue(taskservice.updateTodo("Prj10Req1", "Prj10Req1tsk4", task) instanceof String);
	}

	@Test
	void viewTasksTest() {
		assertTrue(taskservice.viewTasks().get(0) instanceof TaskModel);
	}

	@Test
	void getTaskTest() {
		assertTrue(taskservice.getTask("Prj10Req1tsk4") instanceof TaskModel);
	}

	@Test
	void gettaskHistoryTest() {
		assertTrue(taskservice.gettaskHistory("Prj10Req1tsk4").get(0) instanceof TaskHistory);
	}

	/*
	 * @Test void deleteTaskTest() {
	 * assertTrue(taskservice.deleteTask("Prj6Req1","Prj10Req1tsk1") instanceof
	 * String); }
	 */

}
