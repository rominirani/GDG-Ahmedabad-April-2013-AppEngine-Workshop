package com.mindstormsoftware.examresults.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.mindstormsoftware.examresults.entity.ExamResult;
import com.mindstormsoftware.examresults.services.PMF;

public enum  ExamResultDAO {
	INSTANCE;
	public List<ExamResult> getExamResults() {
		List<ExamResult> ExamResults;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(ExamResult.class);
		try {
			ExamResults = (List<ExamResult>) query.execute();
			System.out.println("Number of Exam Results: " + ExamResults.size());
        } finally {
            pm.close();
        }

		return (ExamResults);
	}

	public void deleteAll() {
		List<ExamResult> ExamResults;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(ExamResult.class);
		try {
			ExamResults = (List<ExamResult>) query.execute();
			pm.deletePersistentAll(ExamResults);
        } finally {
            pm.close();
        }
	}

	public String add(String seatNumber, String studentName, String marks_Math, String marks_CommSkills, String marks_Programming, String marks_ElectronicCircuits, String marks_Total, String marks_Percentage) {
		Key key = KeyFactory.createKey(ExamResult.class.getSimpleName(), seatNumber);
		ExamResult ExamResult = new ExamResult();
		ExamResult.setSeatNumber(key);
		ExamResult.setStudentName(studentName);
		ExamResult.setMarks_Math(marks_Math);
		ExamResult.setMarks_CommSkills(marks_CommSkills);
		ExamResult.setMarks_Programming(marks_Programming);
		ExamResult.setMarks_ElectronicCircuits(marks_ElectronicCircuits);
		ExamResult.setMarks_Total(marks_Total);
		ExamResult.setMarks_Percentage(marks_Percentage);
		synchronized (this) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
	            pm.makePersistent(ExamResult);
	            System.out.println(ExamResult.getSeatNumber().getName());
	        } finally {
	            pm.close();
	        }
		}
		
		return (ExamResult.getSeatNumber().getName());
	}

	public void update(String seatNumber, String studentName, String marks_Math, String marks_CommSkills, String marks_Programming, String marks_ElectronicCircuits, String marks_Total, String marks_Percentage) {
		ExamResult ExamResult;
		synchronized (this) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Key k = KeyFactory.createKey(ExamResult.class.getSimpleName(), seatNumber);
			try {
				ExamResult = pm.getObjectById(ExamResult.class, k);
				ExamResult.setStudentName(studentName);
				ExamResult.setMarks_Math(marks_Math);
				ExamResult.setMarks_CommSkills(marks_CommSkills);
				ExamResult.setMarks_Programming(marks_Programming);
				ExamResult.setMarks_ElectronicCircuits(marks_ElectronicCircuits);
				ExamResult.setMarks_Total(marks_Total);
				ExamResult.setMarks_Percentage(marks_Percentage);
			} finally {
	            pm.close();
	        }
		}
	}

	public void remove(String ExamResultKey) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k = KeyFactory.createKey(ExamResult.class.getSimpleName(), ExamResultKey);
		ExamResult ExamResult;
		try {
			ExamResult = pm.getObjectById(ExamResult.class, k);
			pm.deletePersistent(ExamResult);
		} finally {
            pm.close();
        }
	}

	public ExamResult getExamResult(String ExamResultKey) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k = KeyFactory.createKey(ExamResult.class.getSimpleName(), ExamResultKey);
		ExamResult ExamResult = null;
		try {
			ExamResult = pm.getObjectById(ExamResult.class, k);
		} finally {
            pm.close();
        }
		
		return (ExamResult);
	}

}
