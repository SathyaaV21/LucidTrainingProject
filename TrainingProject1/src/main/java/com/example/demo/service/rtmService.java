package com.example.demo.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.constants.Constants;
import com.example.demo.model.RTM;
import com.example.demo.model.TestCase;

@Service
public class rtmService {
		private MongoTemplate mongoTemplate;
		
		
		@Autowired
		private MongoOperations mongoOperations;
		
		public rtmService(MongoTemplate mongoTemplate) {
			this.mongoTemplate = mongoTemplate;
			}
		public RTM addRequirement(RTM rtm)
		{
			/*
			Update update = new Update().addToSet(rtm.getRequirement_Id(),rtm.getTestCase());
			Query q = new Query();
			q.addCriteria(Criteria.where(Constants.REQUIREMENT_ID).is(rtm.getRequirement_Id()));
			return mongoOperations.findAndModify(q, update, options().returnNew(true).upsert(true),RTM.class);
		*/
			return mongoTemplate.save(rtm);
		}
		public List<RTM> getRTM() {
			return mongoTemplate.findAll(RTM.class);
		}
	
	
	
}
