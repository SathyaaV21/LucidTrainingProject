package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TaskTypeModel;
import com.example.demo.repository.TaskTypeRepository;
@Service
public class TaskTypeService {

	@Autowired
	private TaskTypeRepository tasktyperepo;
	public String savetaskType(TaskTypeModel newtype) {
		tasktyperepo.save(newtype);
		return "New Task type saved";
	}
	
	public List<TaskTypeModel> viewtaskType(){
		return tasktyperepo.findAll();
	}
	
	public String deletetaskType(int id) {
		tasktyperepo.deleteById(id);
		return "Delete task type";
	}
}
