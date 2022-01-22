
/**
* 	@author RITIKA M
*/
package com.example.demo.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.File;
import com.example.demo.service.Cloudinary;
import com.example.demo.service.CloudinaryFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1")
public class CloudinaryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CloudinaryController.class);

	@Autowired
	private CloudinaryFileService cloudinaryfileservice;

	/**
	 * Method to get all files
	 *
	 * @return List of Files with respective fields and information.
	 */

	@GetMapping("/getallfiles")
	public List<File> getAllFiles() {
		LOGGER.info("IN GET ALL FILES");

		return cloudinaryfileservice.getAllFiles();
	}



	/**
	 * Method to upload a file by defect_id
	 *
	 * @param defect_id as HashMap.
	 * @param Multipart file
	 * @return File with respective status and information.
	 */
	  @PostMapping(value = "/uploadfile", consumes = {
	  MediaType.MULTIPART_FORM_DATA_VALUE }) public File uploadFile(@RequestPart("file") MultipartFile file, @RequestPart("defect_id")
	  String defect_id) throws IOException {
	  
	  return cloudinaryfileservice.addFile(Cloudinary.uploadToCloudinary(file,
	  defect_id));
	  
	  }

	/**
	 * Method to get all files by defect_id
	 * @param defect_id as HashMap.
	 * @return File with respective status and information.
	 */
	
	@GetMapping("/getfilebyid")
	public File getFileById(@RequestBody HashMap<String, String> dataHashMap) {

		return cloudinaryfileservice.getFileById(dataHashMap.get("defect_id"));
	}

	/**
	 * Method to delete all files by defect_id
	 *
	 * @param defect_id as HashMap.
	 * @return String stating the message.
	 */
	
	@PreAuthorize("hasAuthority('ROLE_TESTER')")
	@DeleteMapping("/deleteallfilesbyid")
	public String deleteAllFiles(@RequestBody HashMap<String, String> dataHashMap) {
		return cloudinaryfileservice.deleteAllFiles(dataHashMap.get("defect_id"));

	}
}