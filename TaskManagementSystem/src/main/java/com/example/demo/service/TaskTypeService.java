package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TaskTypeModel;
import com.example.demo.repository.TaskTypeRepository;
@Service
public class TaskTypeService {

	@Autowired
	private TaskTypeRepository tasktyperepo;
	public void savetaskType(TaskTypeModel newtype) {
		tasktyperepo.save(newtype);
	}
	
	public List<TaskTypeModel> viewtaskType(){
		return tasktyperepo.findAll();
	}
	
	public void deletetaskType(int id) {
		/*Optional<TaskTypeModel>tasktypemodel=tasktyperepo.findById(id);*/
		tasktyperepo.deleteById(id);
	}
}
