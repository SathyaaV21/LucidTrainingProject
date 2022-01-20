/**
* 	@author SHAJIND C
*/

package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.Comments;
import com.example.demo.model.DefectModel;
import com.example.demo.model.Sequence;
import com.example.demo.model.Status;

@Service
public class DefectService {

	private static final Logger logger = LoggerFactory.getLogger(DefectService.class);

	@Autowired
	private MongoTemplate mongotemplate;

	@Autowired
	private SequenceGenService service;

	/**
	 * Method to create a new defect
	 *
	 * 
	 * @param DefectModel object
	 * @return The ID of the newly created Defect.
	 */

	public String addDefect(DefectModel defect) {
		defect.setId("Def" + service.getCount(Sequence.getSequenceName1()));
		List<Comments> commentList = new ArrayList<>();
		commentList.add(new Comments(defect.getAssignedUser(), "The defect has been stored", LocalDateTime.now()));
		List<Status> statusList = new ArrayList<>();
		statusList.add(
				new Status(defect.getAssignedUser(), null, "New", "The defect has been stored", LocalDateTime.now()));
		defect.setDefectHistory(statusList);
		defect.setComments(commentList);
		defect.setPresentStatus("New");
		mongotemplate.save(defect);
		logger.info("Defect is created successfully");
		return "Added successfully in the database with defectID " + defect.getId();
	}

	/**
	 * Method to update details of Defect
	 *
	 * @param Filters                      for search if any,whose status is
	 *                                     cancelled.
	 * @param ID(String),DefectModelHolder Object
	 * @return A string of acknowledgement.
	 * @throws BadRequestException for deleted defect or not present.
	 */

	public String updateDefect(Map<String, String> defectModelHolder, String id) {
		DefectModel oldDefect = mongotemplate.findById(id, DefectModel.class);
		if (oldDefect == null) {
			logger.warn("The document is present in database");
			throw new BadRequestException("ID not present");
		}
		List<Status> oldStatus = oldDefect.getDefectHistory();
		List<Comments> oldComments = oldDefect.getComments();
		if (oldDefect.getPresentStatus().equals("Cancelled")) {
			logger.warn("The defect is in cancelled status");
			throw new BadRequestException("The specified Defect ID cannot be updated");
		}

		else {
			Query query = new Query();
			query.addCriteria(Criteria.where("id").is(id));
			Update update = new Update();
			if (defectModelHolder.containsKey("comment")) {

				for (Map.Entry defect : defectModelHolder.entrySet()) {
					if (!defect.getKey().equals("comment") && !defect.getKey().equals("status")) {
						update.set((String) defect.getKey(), defect.getValue());
						if (defectModelHolder.get("presentStatus") == null) {
							update.set("presentStatus", defectModelHolder.get("status"));
						}
					} else if (defect.getKey().equals("comment")) {
						Comments comment = new Comments();
						if (defectModelHolder.get("assignedUser") != null) {
							comment.setAssignedUser(defectModelHolder.get("assignedUser"));
						} else {
							comment.setAssignedUser(oldDefect.getAssignedUser());
						}
						comment.setComment((String) defect.getValue());
						comment.setTimestamp(LocalDateTime.now());
						oldComments.add(comment);
						logger.info("Comments collection have been updated");

					} else if (defect.getKey().equals("status")) {
						if (defect.getValue().equals("New") || defect.getValue().equals("Open")
								|| defect.getValue().equals("Fixed") || defect.getValue().equals("Retest")
								|| defect.getValue().equals("Cancelled")) {
							if (!(defect.getValue().equals(oldDefect.getPresentStatus()))) {
								update.set("presentStatus", defect.getValue());
								Status status = new Status();
								if (defectModelHolder.get("assignedUser") != null) {
									status.setAssignedUser(defectModelHolder.get("assignedUser"));
								} else {
									status.setAssignedUser(oldDefect.getAssignedUser());
								}
								status.setStatusBefore(oldDefect.getPresentStatus());
								status.setCurrentStatus((String) defect.getValue());
								status.setComment(defectModelHolder.get("comment"));
								status.setTimestamp(LocalDateTime.now());
								oldStatus.add(status);
								logger.info("Defect History collection have been updated successfully");
							} else {
								continue;
							}
						} else {
							logger.warn("The status is not valid");
							throw new BadRequestException("The specified Status is invalid!!");
						}
					}
				}

				update.set("Comments", oldComments);
				update.set("defectHistory", oldStatus);
				if (!(defectModelHolder.containsKey("status")))
					update.set("presentStatus", oldDefect.getPresentStatus());
				logger.info("Updating the changes");
				mongotemplate.findAndModify(query, update, DefectModel.class);
			}

			else {
				logger.warn("Comments are mandatory");
				throw new BadRequestException("Please specify some comments");
			}
			logger.info("Defect Id has been updated");
			return id + " has been updated successfully";
		}
	}

	/**
	 * Method to delete details of Defect
	 *
	 * @param Filters    for search if any,whose status is not cancelled
	 * @param ID(String)
	 * @return A string of acknowledgement
	 * @throws BadRequestException for deleted defect.
	 **/

	public String deleteDefect(String id) {
		DefectModel oldDefect = mongotemplate.findById(id, DefectModel.class);
		if (oldDefect == null) {
			logger.warn("The defect is not present in database");
			throw new BadRequestException("ID not available");
		}
		List<Status> oldStatus = oldDefect.getDefectHistory();
		List<Comments> oldComments = oldDefect.getComments();
		logger.info("Checking the status of the defect..");
		if (oldDefect != null && (!oldDefect.getPresentStatus().equals("Cancelled"))) {
			logger.info("Changing to Cancelled status..");
			Status status = new Status();
			status.setAssignedUser(oldDefect.getAssignedUser());
			status.setStatusBefore(oldDefect.getPresentStatus());
			status.setCurrentStatus("Cancelled");
			status.setComment("The ID is deleted successfully");
			status.setTimestamp(LocalDateTime.now());
			oldStatus.add(status);
			Comments comment = new Comments();
			comment.setAssignedUser(oldDefect.getAssignedUser());
			comment.setComment("The ID is deleted successfully");
			comment.setTimestamp(LocalDateTime.now());
			oldComments.add(comment);
			oldDefect.setComments(oldComments);
			oldDefect.setDefectHistory(oldStatus);
			oldDefect.setPresentStatus("Cancelled");
			mongotemplate.save(oldDefect);
			logger.info("Comments and defect history collection has been updated");
		} else {
			logger.warn("The defect is already Cancelled");
			throw new BadRequestException("The defect ID cannot be deleted");
		}
		logger.info("Defect has been deleted successfully");
		return id + " is successfully deleted";
	}

	/**
	 * Method to retrieve details of Defect
	 *
	 * @param Filters    for search if any,whose status is cancelled
	 * @param ID(String)
	 * @return A DefectModel Object of defect ID
	 * @throws BadRequestException for deleted defect
	 */

	public DefectModel getDefect(String id) {
		DefectModel defect = mongotemplate.findById(id, DefectModel.class);
		if (defect == null) {
			logger.warn("The Id is unavailable");
			throw new BadRequestException("The ID is unavailable");
		}
		if (defect.getPresentStatus().equals("Cancelled")) {
			logger.warn("The Id is in Cancelled status");
			throw new BadRequestException("The specified Defect ID is cancelled...Cannot be displayed");
		}
		logger.info("Defect is retrieved from database");
		return defect;
	}

	/**
	 * Method to retrieve all the defects
	 *
	 * @param Filters for search if any,whose status is not Cancelled
	 * @throws BadRequestException for no active defects.
	 */

	public List<DefectModel> getAlldefects() {
		List<DefectModel> allDefects = mongotemplate.findAll(DefectModel.class);
		List<DefectModel> resultedDefects = new ArrayList<DefectModel>();
		for (DefectModel defect : allDefects) {
			List<Status> status = defect.getDefectHistory();
			if (!(status.get((status.size()) - 1).getCurrentStatus().equals("Cancelled"))) {
				resultedDefects.add(defect);
			}
		}
		if (resultedDefects.isEmpty()) {
			logger.warn("There are no Open defects in database");
			throw new BadRequestException("There are no defects available at present!!");
		}
		logger.info("All the Open defects have been displayed");
		return resultedDefects;

	}

	/**
	 * Method to get defects of project ID.
	 *
	 * 
	 * @param Project ID(String)
	 * @return List of DefectModel Object.
	 * @throws BadRequestException for no active defects.
	 */
	public List<DefectModel> getProjectDefect(String projectID) {
		List<DefectModel> defects = mongotemplate.findAll(DefectModel.class);
		List<DefectModel> resultedDefects = new ArrayList<>();
		for (DefectModel defect : defects) {
			if (defect.getProjectID().equals(projectID) && !(defect.getDefectHistory()
					.get((defect.getDefectHistory().size()) - 1).getCurrentStatus().equals("Cancelled"))) {
				resultedDefects.add(defect);
			}
		}
		if (resultedDefects.isEmpty()) {
			logger.warn("There are no active defects for the project Id");
			throw new BadRequestException("There are no active defects for the specified Project ID");
		}
		logger.info("All the defects of the project have been displayed");
		return resultedDefects;
	}

	/**
	 * Method to get number of defects with Open Status.
	 *
	 * 
	 * @param
	 * @return Count of Open Status Defects.
	 */
	public int openDefectsCount() {
		int count = 0;
		List<DefectModel> defects = mongotemplate.findAll(DefectModel.class);
		for (DefectModel defect : defects) {
			if (!(defect.getPresentStatus().equals("Cancelled"))) {
				count++;
			}
		}
		logger.info("Returning count of open defects");
		return count;
	}

	/**
	 * Method to get all defects with Closed Status.
	 *
	 * 
	 * @param
	 * @return Count of Closed Status Defects.
	 */
	public int closedDefectsCount() {
		int count = 0;
		List<DefectModel> defects = mongotemplate.findAll(DefectModel.class);
		for (DefectModel defect : defects) {
			if (defect.getPresentStatus().equals("Cancelled")) {
				count++;
			}
		}
		logger.info("Returning count of closed defects");
		return count;
	}

	/**
	 * Method to get all defects with Open Status.
	 *
	 * 
	 * @param
	 * @return List of DefectModel Object.
	 * @throws BadRequestException for no active defects.
	 */
	public List<DefectModel> getOpendefects() {
		Query q = new Query();
		q.addCriteria(Criteria.where("presentStatus").ne("Cancelled"));
		List<DefectModel> defects = mongotemplate.find(q, DefectModel.class);
		if (defects.isEmpty()) {
			logger.warn("There are no open defects");
			throw new BadRequestException("Sorry!! There are no Open defects at present..");
		} else {
			logger.info("All the open defects are displayed");
			return defects;
		}
	}

	/**
	 * Method to get all defects with Closed Status.
	 *
	 * 
	 * @param
	 * @return List of DefectModel Object.
	 * @throws BadRequestException for no closed defects.
	 */
	public List<DefectModel> getCloseddefects() {
		Query q = new Query();
		q.addCriteria(Criteria.where("presentStatus").is("Cancelled"));
		List<DefectModel> defects = mongotemplate.find(q, DefectModel.class);
		if (defects.isEmpty()) {
			logger.warn("There are no closed defects");
			throw new BadRequestException("Sorry!! All the defects are in Open Status..");
		} else {
			logger.info("All the closed defects are displayed");
			return defects;
		}
	}

	/**
	 * Method to get defect history.
	 *
	 * 
	 * @param String id.
	 * @return List of Status.
	 * @throws BadRequestException for invalid defect ID.
	 */
	public List<Status> getHistoryByID(String id) {
		DefectModel defect = mongotemplate.findById(id, DefectModel.class);
		if (defect == null) {
			logger.warn("Id is not present in database");
			throw new BadRequestException("The ID is unavailable");
		}
		logger.info("The history of the defect is displayed");
		return defect.getDefectHistory();
	}

}