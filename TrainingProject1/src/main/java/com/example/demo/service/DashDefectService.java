
package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.model.FileCount;
import com.example.demo.model.File;


@Service
public class DashDefectService {
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private DefectService defectservice;

	@Autowired
	public DashDefectService(MongoTemplate mongoTemplate) {

		this.mongoTemplate = mongoTemplate;
	}
	public long countFiles() {
		Query query=new Query();
		return mongoTemplate.count(query, File.class);
	}
	public String addEntry(FileCount count) {
		mongoTemplate.save(count);
		return count.getTime() + " " + count.getFilesCount() + " added";

	}
}
