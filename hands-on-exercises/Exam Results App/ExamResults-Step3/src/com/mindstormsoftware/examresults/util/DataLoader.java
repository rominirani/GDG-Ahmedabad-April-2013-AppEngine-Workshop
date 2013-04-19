package com.mindstormsoftware.examresults.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import com.mindstormsoftware.examresults.entity.ExamResultEntity;

public class DataLoader {
	
	private final static Logger _logger = Logger.getLogger(DataLoader.class.getName());
	
	private static final String SEPARATOR = ";";
	public static final String EXAMRESULTS_DATA_FILE = "WEB-INF/sampleData/examresults.dat";
	
	public static void loadData() {
		BufferedReader rdr = null;
		try {
			rdr = new BufferedReader(new InputStreamReader(new FileInputStream(EXAMRESULTS_DATA_FILE)));
			String nextLine;
			while ((nextLine = rdr.readLine()) != null) {
				String[] data = nextLine.split(SEPARATOR);
				//Call the ExamResultEntity to insert the record
				ExamResultEntity.createOrUpdateExamResult(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
			}
			_logger.info("Processed the data file examresults.dat successfully");
		}
		catch (Exception ex) {
			_logger.info("ERROR : Could not process the data file examresults.dat. Reason : " + ex.getMessage());
		}
		finally {
			try {
				if (rdr != null) rdr.close();
			}
			catch (Exception ex) {}
		}
	}
}
