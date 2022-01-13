package com.example.demo;


import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.defect.model.DefectModel;
import com.example.defect.service.DefectService;

@RunWith(SpringRunner.class)
@SpringBootTest
class DefectApplicationTests {
	@Autowired 
	private DefectService defectService;


	@Test
	void addDefecttest() {
		DefectModel def=new com.example.defect.model.DefectModel(); 
		def.setDescription("Memory Exceeded");
		def.setActualResult("Time Limit Exceeded");
		def.setProjectID("P11");
		def.setAssignedUser("U1");
		def.setSeverity(2);
		assertTrue(defectService.addDefect(def) instanceof String);
	} 
	
	@Test
	void getDefectTest() {
		assertTrue(defectService.getDefect("Def7") instanceof DefectModel);
	}
	
	
	}

