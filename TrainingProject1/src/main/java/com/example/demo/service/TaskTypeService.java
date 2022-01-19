package com.example.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.TaskTypeModel;
@Service
public class TaskTypeService {

	@Autowired
	private MongoTemplate mongotemplate;
	
	/**
	 * Service to create a new task type
	 * @param newtype
	 * @return String
	 */
	public String savetaskType(TaskTypeModel newtype) {
		mongotemplate.save(newtype);
		return "New Task type saved";
	}
	
	/**
	 * Service to view all the available task types
	 * @return list of TaskTypes
	 */
	public List<TaskTypeModel> viewtaskType(){
		return mongotemplate.findAll(TaskTypeModel.class);
	}
	
	/**
	 * Service to delete a task type
	 * @param id
	 * @return String
	 */
	public String deletetaskType(int id) {
		if(mongotemplate.findById(id, TaskTypeModel.class)==null)
			throw new BadRequestException("Task type is not found");
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		mongotemplate.remove(query, TaskTypeModel.class);
		return "Delete task type";
	}
}
