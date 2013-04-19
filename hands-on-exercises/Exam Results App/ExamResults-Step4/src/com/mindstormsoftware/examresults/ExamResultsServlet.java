package com.mindstormsoftware.examresults;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.*;
import com.google.appengine.api.datastore.Entity;
import com.mindstormsoftware.examresults.entity.ExamResultEntity;


@SuppressWarnings("serial")
public class ExamResultsServlet extends HttpServlet {
	private static final Logger _logger = Logger.getLogger(ExamResultsServlet.class.getName());
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		//Extract out the input parameters
		String seatNumber = req.getParameter("seatnumber");
		_logger.info("Received a request for seat number = " + seatNumber);
		
		try {
			//Check if a Seat Number that is provided is not null or empty
			if (seatNumber == null) throw new Exception("Seat Number needs to be provided.");
			if (!seatNumber.isEmpty()) {
					//Retrieve the results - currently this will be dummy
					Entity _result = ExamResultEntity.getExamResult(seatNumber);
					req.getSession().setAttribute("result", _result);
					resp.sendRedirect("results.jsp");
			}
			else {
				throw new Exception("Seat Number needs to be provided.");
			}
		}
		catch (Exception ex) {
			String logMsg = "Exception in processing request : " + ex.getMessage();
			_logger.log(Level.INFO,logMsg);
			throw new IOException(logMsg);
		}
	}
	
/*	private ExamResult getDummyResult(String seatNumber) {
		ExamResult ER = new ExamResult();
		ER.setSeatNumber(seatNumber);
		ER.setStudentName("MR. GOOGLE APP ENGINE");
		ER.setMarks_Math("80");
		ER.setMarks_CommSkills("70");
		ER.setMarks_ElectronicCircuits("60");
		ER.setMarks_Programming("90");
		ER.setMarks_Percentage("75");
		ER.setMarks_Total("300");
		return ER;
	}
*/}
