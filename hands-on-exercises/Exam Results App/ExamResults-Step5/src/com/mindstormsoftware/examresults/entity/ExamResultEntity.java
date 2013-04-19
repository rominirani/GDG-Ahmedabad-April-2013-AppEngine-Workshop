package com.mindstormsoftware.examresults.entity;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.mindstormsoftware.examresults.services.DBUtil;

/**
 * This class handles all the CRUD operations related to
 * ExamResult entity.
 *
 */

public class ExamResultEntity {

  /**
   * Create or Update the ExamResult. 	
   * @param seatnumber
   * @param studentName
   * @param marks_Math
   * @param marks_CommSkills
   * @param marks_Programming
   * @param marks_ElectronicCircuits
   * @param marks_Total
   * @param marks_Percentage
   */
  public static void createOrUpdateExamResult(String seatnumber, String studentName, String marks_Math, String marks_CommSkills, String marks_Programming, String marks_ElectronicCircuits, String marks_Total,String marks_Percentage) {
    Entity examresult = getExamResult(seatnumber);
  	if (examresult == null) {
  		examresult = new Entity("ExamResult", seatnumber);
  		examresult.setProperty("studentName", studentName);
  		examresult.setProperty("marks_Math", marks_Math);
  		examresult.setProperty("marks_CommSkills", marks_CommSkills);
  		examresult.setProperty("marks_Programming", marks_Programming);
  		examresult.setProperty("marks_ElectronicCircuits", marks_ElectronicCircuits);
  		examresult.setProperty("marks_Total", marks_Total);
  		examresult.setProperty("marks_Percentage", marks_Percentage);
  	} else {
  		examresult.setProperty("studentName", studentName);
  		examresult.setProperty("marks_Math", marks_Math);
  		examresult.setProperty("marks_CommSkills", marks_CommSkills);
  		examresult.setProperty("marks_Programming", marks_Programming);
  		examresult.setProperty("marks_ElectronicCircuits", marks_ElectronicCircuits);
  		examresult.setProperty("marks_Total", marks_Total);
  		examresult.setProperty("marks_Percentage", marks_Percentage);
  	}
  	DBUtil.persistEntity(examresult);
  }

  /**
   * Return all the ExamResult
   * @param kind : of kind ExamResult
   * @return  products
   */
  public static Iterable<Entity> getAllExamResults(String kind) {
    return DBUtil.listEntities(kind, null, null);
  }

  /**
   * Get ExamResult entity
   * @param name : seatnumber of the ExamResult
   * @return: examresult entity
   */
  public static Entity getExamResult(String name) {
  	Key key = KeyFactory.createKey("ExamResult",name);
  	return DBUtil.findEntity(key);
  }

  /**
   * Delete ExamResult entity
   * @param examresultkey: ExamResult to be deleted
   * @return status string
   */
  public static String deleteExamResult(String examresultkey)
  {
	  Key key = KeyFactory.createKey("ExamResult",examresultkey);	   
	  
	  DBUtil.deleteEntity(key);
	  return "ExamResult deleted successfully";
  }
}
