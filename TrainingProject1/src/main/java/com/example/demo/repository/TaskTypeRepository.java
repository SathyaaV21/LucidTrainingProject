package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.TaskTypeModel;

public interface TaskTypeRepository extends MongoRepository<TaskTypeModel,Integer> {

}
