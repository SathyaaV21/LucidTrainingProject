/**
* 	@author SHAJIND C
*/

package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DefectModel;
import com.example.demo.model.ResponseModel;
import com.example.demo.model.Status;
import com.example.demo.service.DefectService;

@RequestMapping("/api/v1")
@RestController
public class DefectController {

	private static final Logger logger = LoggerFactory.getLogger(DefectController.class);

	@Autowired
	private DefectService defectService;

	/**
	 * Method to create a new defect
	 *
	 * 
	 * @param Defect object
	 * @return The ID of the newly created Defect.
	 */

	@PostMapping("/adddefect")
	public ResponseEntity<?> addDefect(@RequestBody DefectModel defect) {
		logger.info("Creating a new defect");
		return ResponseEntity.ok(new ResponseModel(defectService.addDefect(defect)));
	}

	/**
	 * Method to update defect parameters using their ID
	 *
	 * 
	 * @param DefectModelHolder Object,Defect ID(String)
	 * @return A string of acknowledgement
	 */

	@PutMapping("/updatedefect/{id}")
	public ResponseEntity<?> updateDefect(@RequestBody Map<String, String> defectModelHolder, @PathVariable String id) {
		logger.info("Updating a defect");
		return ResponseEntity.ok(new ResponseModel(defectService.updateDefect(defectModelHolder, id)));
	}

	/**
	 * Method to get defects of project ID
	 *
	 * 
	 * @param Project ID(String)
	 * @return List of DefectModel Object
	 */
	@GetMapping("/getdetailsdefect/{projectID}")
	public List<DefectModel> getProjectDefect(@PathVariable String projectID) {
		logger.info("Getting all defects of a project");
		return defectService.getProjectDefect(projectID);
	}

	/**
	 * Method to retrieve all the defects
	 *
	 * 
	 * 
	 * @return A list of all the defects in the collection
	 */
	@GetMapping("/getalldefect")
	public List<DefectModel> getAlldefects() {
		logger.info("Getting all defects from the database");
		return defectService.getAlldefects();
	}

	/**
	 * Method to retrieve details of Defect
	 *
	 * 
	 * @param ID(String)
	 * @return List of DefectModel Object
	 */
	@GetMapping("/getidDefect/{id}")
	public DefectModel getDefect(@PathVariable String id) {
		logger.info("Getting a specified defect");
		return defectService.getDefect(id);
	}

	/**
	 * Method to delete details of Defect
	 *
	 * 
	 * @param ID(String)
	 * @return A string of acknowledgement
	 */
	@DeleteMapping("/deleteiddefect/{id}")
	public ResponseEntity<?> deleteDefect(@PathVariable String id) {
		logger.info("Deleting a defect");
		return ResponseEntity.ok(new ResponseModel(defectService.deleteDefect(id)));
	}

	/**
	 * Method to get the history of the defect
	 *
	 * 
	 * @param ID(String)
	 * @return A list of Status.
	 */
	@GetMapping("/gethistorydefect/{id}")
	public List<Status> getHistoryByID(@PathVariable String id) {
		logger.info("Getting the history of a defect");
		return defectService.getHistoryByID(id);
	}

}