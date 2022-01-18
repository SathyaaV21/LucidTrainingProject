package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.example.demo.model.Attachments;
import com.example.demo.model.DefectModel;
import com.example.demo.model.Status;

@SpringBootTest
class DefectServiceTest {

	@Autowired
	private DefectService defect;

	@Mock
	private MongoTemplate mongotemplate;

	@Spy
	@InjectMocks
	private DefectService defectService;

	@Test
	void addDefecttest() {
		DefectModel def = new DefectModel();
		// def.setId("Def100");
		def.setDescription("Memory Exceeded");
		def.setActualResult("Time Limit Exceeded");
		def.setPresentStatus("New");
		def.setProjectID("Prj5");
		def.setAssignedUser("Manju");
		def.setAttachment(List.of("1", "2"));
		def.setSeverity(2);
//		Attachments attach=new Attachments();
//		attach.setDefectId(def.getId());
//		attach.setAttachments(List.of("1","2"));

		// assertTrue(attach instanceof Attachments);
		// when(mongotemplate.insert(Attachments.class)).thenReturn(null);
		// assertEquals(def.getAttachment().get(0),"1");
		assertTrue(defect.addDefect(def) instanceof String);
	}

//	@Test 
//	public void StatusTest() {
//		assertTrue(defect.addDefect(defect))
//	}

	@Test
	public void testUpdateDefect() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("status", "Retest");
		parameters.put("comment", "Status updated");
		assertTrue(defect.updateDefect(parameters, "Def1") instanceof String);
	}

	@Test
	public void testGetAllDefects() {
		assertTrue(defect.getAlldefects().get(0) instanceof DefectModel);
	}

	@Test
	public void testGetDefectById() {
		DefectModel def = new DefectModel();
		def.setId("Def10");
		def.setDescription("Memory Exceeded");
		def.setActualResult("Time Limit Exceeded");
		def.setPresentStatus("New");
		def.setProjectID("P11");
		def.setAssignedUser("U1");
		def.setSeverity(2);
		// when(mongotemplate.findById("Def1", DefectModel.class));
		// assertEquals(1,defectService.getDefect("Def1"));
		when(mongotemplate.findById("Def10", DefectModel.class)).thenReturn(def);
		assertEquals(def, defectService.getDefect("Def10"));
		// assertTrue(defectService.getDefect("Def1") instanceof DefectModel);
	}

	@Test
	public void getProjectDefectTest() {
		assertTrue(defect.getProjectDefect("Prj5").get(0) instanceof DefectModel);
	}

	@Test
	public void openDefectsCounttest() {
		assertTrue(Integer.toString(defect.openDefectsCount()) instanceof String);
	}

	@Test
	public void closedDefectsCounttest() {
		assertTrue(Integer.toString(defect.closedDefectsCount()) instanceof String);
	}

	@Test
	public void getOpendefectsTest() {
		assertTrue(defect.getOpendefects().get(0) instanceof DefectModel);
	}

//It can be included finally
//	@Test
//	public void deleteDefecttest() {
//		assertTrue(defect.deleteDefect("Def1") instanceof String);
//	}

	@Test
	public void getCloseddefectsTest() {
		assertTrue(defect.getCloseddefects().get(0) instanceof DefectModel);
	}

	@Test
	public void getHistoryByIDtest() {
		assertTrue(defect.getHistoryByID("Def2").get(0) instanceof Status);
	}

}
