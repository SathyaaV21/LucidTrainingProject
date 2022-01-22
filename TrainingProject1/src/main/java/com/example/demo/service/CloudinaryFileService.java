/**
* 	@author RITIKA M
*/
package com.example.demo.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.demo.constants.Constants;
import com.example.demo.exception.FileNotFoundException;
import com.example.demo.model.File;
@Service
public class CloudinaryFileService {
	private MongoTemplate mongoTemplate;

	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	public CloudinaryFileService(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;

	}

	
	/**
	 * Method to add File into database
	 * 
	 * @param File which contains the file details.
	 * @return File with respective status and information.
	 * @throws FileNotFoundException handles Exception.
	 * 
	 */

	 public File addFile(File file) {
	  
	  if (file.getFiledocument().isEmpty()) { throw new
	  FileNotFoundException("Could not read any file"); } else {
	  
	  try { Update update = new Update().addToSet(Constants.FILE_DOCUMENT,
	  file.getFiledocument().get(0));
	  
	  Query q = new Query();
	  q.addCriteria(Criteria.where(Constants.DEFECT_ID).is(file.getDefect_id()));
	  
	  return mongoOperations.findAndModify(q, update, options().returnNew(true).upsert(true), File.class); } 
	  catch (Exception e) {
	  throw new FileNotFoundException("File not found");
	  
	  }
	  
	  } }
	 
		/**
		 * Method to get all Files from database
		 * 
		 * @return List<File> with respective status and information.
		 * @throws FileNotFoundException handles Exception.
		 */
	 
	 
	public List<File> getAllFiles() {

		return mongoTemplate.findAll(File.class);
	}
	
	
	
	/**
	 * Method to get all Files for a defect_id from database
	 * @param defect ID of the file
	 * @return File with respective status and information.
	 * @throws FileNotFoundException handles Exception.
	 */
	public File getFileById(String defect_id) {

		Query q = new Query();
		q.addCriteria(Criteria.where(Constants.DEFECT_ID).is(defect_id));

		File reqEntity = mongoTemplate.findOne(q, File.class);
		if (reqEntity != null) {
			return reqEntity;
		} else {
			throw new FileNotFoundException("File not found");
		}
	}
	

	/**
	 * Method to delete all files with defect_id and unique asset_id.
	 * 
	 * @param defect ID of the file
	 * @return String with respective status and information.
	 * @throws BadRequestException handles Exception.
	 */
	public String deleteAllFiles(String defect_id) {

		Query q = new Query();
		q.addCriteria(Criteria.where(Constants.DEFECT_ID).is(defect_id));
		File deletedEntity = mongoTemplate.findAndRemove(q, File.class);

		if (deletedEntity != null) {
			return "File is deleted";
		} else {
			throw new FileNotFoundException("File not found");
		}

	}


}