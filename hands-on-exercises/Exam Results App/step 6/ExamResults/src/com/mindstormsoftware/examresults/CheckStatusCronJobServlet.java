package com.mindstormsoftware.examresults;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.*;


@SuppressWarnings("serial")
public class CheckStatusCronJobServlet extends HttpServlet {
	private static final Logger _logger = Logger.getLogger(CheckStatusCronJobServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		try {
			_logger.info("Executing Cron Job .... ");
		}
		catch (Exception ex) {
			String logMsg = "Exception in executing Cron Job : " + ex.getMessage();
			_logger.log(Level.INFO,logMsg);
		}
	}
}
