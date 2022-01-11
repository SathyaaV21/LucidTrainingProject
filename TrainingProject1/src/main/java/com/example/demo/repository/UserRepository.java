package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Role;

import com.example.demo.model.UserModel;

public interface UserRepository extends MongoRepository<UserModel,Integer>{
	Optional<UserModel> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	
	default Boolean existByRoles(Role role, UserModel user) {
		for (Object object : user.getRoles()) {
			if(object == role)
				return true;
		}
		return false;
	};

}
