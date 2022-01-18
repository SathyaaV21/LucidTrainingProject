package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.example.demo.model.RTM;
import com.example.demo.model.File;
import com.example.demo.service.DashboardService;

import com.example.demo.exception.ProjectNotFoundException;

public class DashboardTest {
	
	@SpyBean
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private DashboardService dashboardservice;
	
	
	@Test
	public void getRTMTest() throws ProjectNotFoundException {
		dashboardservice.getRTM();
		
	}
}
