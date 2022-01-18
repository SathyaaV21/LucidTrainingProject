package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals
;
import static org.mockito.Mockito.when;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.example.demo.constants.Constants;
import com.example.demo.exception.FileNotFoundException;
import com.example.demo.model.FileCount;
import com.example.demo.model.File;
import com.example.demo.service.CloudinaryFileService;
import com.example.demo.service.Cloudinary;

@SpringBootTest
public class CloudinaryFileTests {

	@SpyBean
	private MongoTemplate mongo;
	
	@Autowired
	private CloudinaryFileService service;
	MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE,
			"Hello, World!".getBytes());
	
	@Test
	public void addFileTest() throws IOException
	{
		CloudinaryFileService mockService=org.mockito.Mockito.mock(CloudinaryFileService.class);
		File testFile=Cloudinary.uploadToCloudinary(file, "def_15");
		when(mockService.addFile(testFile)).thenReturn(testFile);
		File file=service.addFile(testFile);
		assertEquals(file.getDefect_id(),testFile.getDefect_id());
	}
	
	@Test
	public void getFileByDefectIdTest() {

		Query query = new Query();
		query.addCriteria(Criteria.where(Constants.DEFECT_ID).is("DEF_3"));

		File testFile = new File();

		when(mongo.findOne(query, File.class)).thenReturn( new File());
		assertEquals(testFile.getDefect_id(), service.getFileById("DEF_3").getDefect_id());

	}
	
	@Test
	public void getAllFilesTest() {
		when(mongo.findAll(File.class)).thenReturn(Stream.of(new File()).collect(Collectors.toList()));
		assertEquals(1,service.getAllFiles().size());
	}
	
	@Test
	public void deleteFilebyDefectIdTest() {
		Assertions.assertThrows(FileNotFoundException.class, () -> service.deleteAllFiles("DEF_2"));
	}
	
	@Test
	void uploadToCloudinaryTest() throws IOException {

		assertEquals("DEF_15", Cloudinary.uploadToCloudinary(file, "DEF_15").getDefect_id());

	}
	
	
	
	
}
