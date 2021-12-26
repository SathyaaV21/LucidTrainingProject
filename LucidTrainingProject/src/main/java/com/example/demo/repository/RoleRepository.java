package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.RoleModel;



public interface RoleRepository extends MongoRepository<RoleModel,Integer>{

}
