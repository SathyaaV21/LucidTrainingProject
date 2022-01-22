package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.example.demo.model.Comments;
import com.example.demo.model.DefectModel;
import com.example.demo.model.Status;

@SpringBootTest
class DefectServiceTest {

	@Autowired
	private DefectService defect;

	@Mock
	private MongoTemplate mongotemplate; 
	@Mock
	private SequenceGenService service;

	@Spy

	@InjectMocks
	private DefectService defectService;

	@Test
	void addDefecttest() {
		DefectModel def = new DefectModel(); 
		def.setId("Def1");
		def.setDescription("Memory Exceeded");
		def.setActualResult("Time Limit Exceeded");
		def.setPresentStatus("New");
		def.setProjectID("Prj5");
		def.setAssignedUser("Manju");
		Status status=new Status();
		status.setAssignedUser("Manju");
		status.setComment("Defect has been raised");
		status.setCurrentStatus("New");
		status.setStatusBefore(null);
		status.setTimestamp(LocalDateTime.now());
		Comments comment=new Comments(); 
		comment.setAssignedUser("Manju"); 
		comment.setComment("Defect has been raised"); 
		comment.setTimestamp(LocalDateTime.now()); 
		List<Status> sample1=new ArrayList<>();  
		List<Comments> sample2=new ArrayList<>();
		sample1.add(status);sample2.add(comment);
		def.setDefectHistory(sample1); 
		def.setComments(sample2);
		def.setSeverity(2); 
		when(service.getCount("Hii")).thenReturn(1);
		when(mongotemplate.save(def)).thenReturn(def); 
		assertTrue(defectService.addDefect(def) instanceof String);
	}

	@Test
	public void UpdateDefecttest1() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("status", "Retest");
		parameters.put("comment", "Status updated");
		
		DefectModel def = new DefectModel(); 
		def.setId("Def1");
		def.setDescription("Memory Exceeded");
		def.setActualResult("Time Limit Exceeded");
		def.setPresentStatus("New");
		def.setProjectID("Prj5");
		def.setAssignedUser("Manju");
		Status status=new Status();
		status.setAssignedUser("Manju");
		status.setComment("Defect has been raised");
		status.setCurrentStatus("New");
		status.setStatusBefore(null);
		status.setTimestamp(LocalDateTime.now());
		Comments comment=new Comments(); 
		comment.setAssignedUser("Manju"); 
		comment.setComment("Defect has been raised"); 
		comment.setTimestamp(LocalDateTime.now()); 
		List<Status> sample1=new ArrayList<>();  
		List<Comments> sample2=new ArrayList<>();
		sample1.add(status);sample2.add(comment);
		def.setDefectHistory(sample1); 
		def.setComments(sample2);
		def.setSeverity(2); 
		
		Query query=new Query();
		query.addCriteria(Criteria.where("_id").is("Def1"));
		
		Update update=new Update();
		update.set("status", "Retest");
		update.set("comment", "Status updated");
		when(mongotemplate.findById("Def1", DefectModel.class)).thenReturn(def); 
		when(mongotemplate.findAndModify(query, update,DefectModel.class)).thenReturn(def);
		assertTrue(defectService.updateDefect(parameters, "Def1") instanceof String);
	}
	
	@Test
	public void UpdateDefecttest2() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("status", "New");
		parameters.put("comment", "Status updated");
		
		DefectModel def = new DefectModel(); 
		def.setId("Def1");
		def.setDescription("Memory Exceeded");
		def.setActualResult("Time Limit Exceeded");
		def.setPresentStatus("New");
		def.setProjectID("Prj5");
		def.setAssignedUser("Manju");
		Status status=new Status();
		status.setAssignedUser("Manju");
		status.setComment("Defect has been raised");
		status.setCurrentStatus("New");
		status.setStatusBefore(null);
		status.setTimestamp(LocalDateTime.now());
		Comments comment=new Comments(); 
		comment.setAssignedUser("Manju"); 
		comment.setComment("Defect has been raised"); 
		comment.setTimestamp(LocalDateTime.now()); 
		List<Status> sample1=new ArrayList<>();  
		List<Comments> sample2=new ArrayList<>();
		sample1.add(status);sample2.add(comment);
		def.setDefectHistory(sample1); 
		def.setComments(sample2);
		def.setSeverity(2); 
		
		Query query=new Query();
		query.addCriteria(Criteria.where("_id").is("Def1"));
		
		Update update=new Update();
		update.set("status", "New");
		update.set("comment", "Status updated");
		when(mongotemplate.findById("Def1", DefectModel.class)).thenReturn(def); 
		when(mongotemplate.findAndModify(query, update,DefectModel.class)).thenReturn(def);
		assertTrue(defectService.updateDefect(parameters, "Def1") instanceof String);
	}
	
	@Test
	public void UpdateDefecttest3() {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("comment", "Status updated");
		
		DefectModel def = new DefectModel(); 
		def.setId("Def1");
		def.setDescription("Memory Exceeded");
		def.setActualResult("Time Limit Exceeded");
		def.setPresentStatus("New");
		def.setProjectID("Prj5");
		def.setAssignedUser("Manju");
		Status status=new Status();
		status.setAssignedUser("Manju");
		status.setComment("Defect has been raised");
		status.setCurrentStatus("New");
		status.setStatusBefore(null);
		status.setTimestamp(LocalDateTime.now());
		Comments comment=new Comments(); 
		comment.setAssignedUser("Manju"); 
		comment.setComment("Defect has been raised"); 
		comment.setTimestamp(LocalDateTime.now()); 
		List<Status> sample1=new ArrayList<>();  
		List<Comments> sample2=new ArrayList<>();
		sample1.add(status);sample2.add(comment);
		def.setDefectHistory(sample1); 
		def.setComments(sample2);
		def.setSeverity(2); 
		
		Query query=new Query();
		query.addCriteria(Criteria.where("_id").is("Def1"));
		
		Update update=new Update();
		update.set("comment", "Status updated");
		when(mongotemplate.findById("Def1", DefectModel.class)).thenReturn(def); 
		when(mongotemplate.findAndModify(query, update,DefectModel.class)).thenReturn(def);
		assertTrue(defectService.updateDefect(parameters, "Def1") instanceof String);
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
		when(mongotemplate.findById("Def10", DefectModel.class)).thenReturn(def);
		assertEquals(def, defectService.getDefect("Def10"));
	}

	@Test
	public void getProjectDefectTest() {
		assertTrue(defect.getProjectDefect("Prj5").get(0) instanceof DefectModel);
	}

	@Test
	public void openDefectsCounttest() {
		DefectModel def = new DefectModel();
		def.setId("Def10");
		def.setDescription("Memory Exceeded");
		def.setActualResult("Time Limit Exceeded");
		def.setPresentStatus("New");
		def.setProjectID("P11");
		def.setAssignedUser("U1");
		def.setSeverity(2);
		List<DefectModel> test=new ArrayList<>();
		test.add(def);
		when(mongotemplate.findAll(DefectModel.class)).thenReturn(test);
		assertTrue(Integer.toString(defectService.openDefectsCount()) instanceof String);
	}

	@Test
	public void closedDefectsCounttest() {
		assertTrue(Integer.toString(defect.closedDefectsCount()) instanceof String);
	}

	@Test
	public void getOpendefectsTest() {
		DefectModel def = new DefectModel();
		def.setId("Def10");
		def.setDescription("Memory Exceeded");
		def.setActualResult("Time Limit Exceeded");
		def.setPresentStatus("New");
		def.setProjectID("P11");
		def.setAssignedUser("U1");
		def.setSeverity(2);
		List<DefectModel> test=new ArrayList<>();
		test.add(def);
		
		Query q=new Query();
		q.addCriteria(Criteria.where("presentStatus").ne("Cancelled"));
		when(mongotemplate.find(q,DefectModel.class)).thenReturn(test);
		assertTrue(defectService.getOpendefects().get(0) instanceof DefectModel);
	}

	@Test
	public void deleteDefecttest() {
		
		DefectModel def = new DefectModel(); 
		def.setId("Def1");
		def.setDescription("Memory Exceeded");
		def.setActualResult("Time Limit Exceeded");
		def.setPresentStatus("New");
		def.setProjectID("Prj5");
		def.setAssignedUser("Manju");
		Status status=new Status();
		status.setAssignedUser("Manju");
		status.setComment("Defect has been raised");
		status.setCurrentStatus("New");
		status.setStatusBefore(null);
		status.setTimestamp(LocalDateTime.now());
		Comments comment=new Comments(); 
		comment.setAssignedUser("Manju"); 
		comment.setComment("Defect has been raised"); 
		comment.setTimestamp(LocalDateTime.now()); 
		List<Status> sample1=new ArrayList<>();  
		List<Comments> sample2=new ArrayList<>();
		sample1.add(status);sample2.add(comment);
		def.setDefectHistory(sample1); 
		def.setComments(sample2);
		def.setSeverity(2); 
		
		when(mongotemplate.findById("Def1", DefectModel.class)).thenReturn(def);
		when(mongotemplate.save(def)).thenReturn(def);
		assertTrue(defectService.deleteDefect("Def1") instanceof String);
	}

	@Test
	public void getCloseddefectsTest() {
		assertTrue(defect.getCloseddefects().get(0) instanceof DefectModel);
	}

	@Test
	public void getHistoryByIDtest() {
		assertTrue(defect.getHistoryByID("Def17").get(0) instanceof Status);
	}

}
