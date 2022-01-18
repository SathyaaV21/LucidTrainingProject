package com.example.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.model.TaskTypeModel;
@Service
public class TaskTypeService {

	@Autowired
	private MongoTemplate mongotemplate;
	
	public String savetaskType(TaskTypeModel newtype) {
		mongotemplate.save(newtype);
		return "New Task type saved";
	}
	
	public List<TaskTypeModel> viewtaskType(){
		return mongotemplate.findAll(TaskTypeModel.class);
	}
	
	public String deletetaskType(int id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		mongotemplate.remove(query, TaskTypeModel.class);
		return "Delete task type";
	}
}
