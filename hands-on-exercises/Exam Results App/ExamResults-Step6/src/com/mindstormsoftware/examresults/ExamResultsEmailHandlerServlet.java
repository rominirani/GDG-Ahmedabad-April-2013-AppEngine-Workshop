package com.mindstormsoftware.examresults;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
import com.mindstormsoftware.examresults.entity.ExamResultEntity;

public class ExamResultsEmailHandlerServlet  extends HttpServlet {
	public static final Logger _log = Logger.getLogger(ExamResultsEmailHandlerServlet.class.getName());

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		try {
			MimeMessage message = new MimeMessage(session, req.getInputStream());
			String contentType = message.getContentType();
			Address[] fromAddresses = message.getFrom();
			String seatNumber  = message.getSubject();

			//Retrieve the results
			String strCallResult = "";
			Entity ER = ExamResultEntity.getExamResult(seatNumber);
			strCallResult = "Seat Number : " + ER.getKey().getName() + "\r\n";
			strCallResult += "Student Name : " + ER.getProperty("studentName") + "\r\n";
			strCallResult += "Mathematics : " + ER.getProperty("marks_Math") + "\r\n";
			strCallResult += "Communication Skills : " + ER.getProperty("marks_CommSkills") + "\r\n";
			strCallResult += "Electronic Circuits : " + ER.getProperty("marks_ElectronicCircuits") + "\r\n";
			strCallResult += "Programming Languages : " + ER.getProperty("marks_Programming") + "\r\n";
			strCallResult += "Total Marks : " + ER.getProperty("marks_Total") + "/400" + "\r\n";
			strCallResult += "Percentage : " + ER.getProperty("marks_Percentage") + "%" + "\r\n";
			strCallResult += "=================================" + "\r\n";
			strCallResult += "Thank you for using the Exam Helper Mail Service" + "\r\n";
				
			//Send out Email
			MimeMessage outMessage = new MimeMessage(session);
			//Change the email below to : admin@APP_ID.appspotmail.com 
			outMessage.setFrom(new InternetAddress("admin@testromin123.appspotmail.com"));
			outMessage.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(fromAddresses[0].toString()));
			outMessage.setSubject("Your Exam Results");
			outMessage.setText(strCallResult);
			Transport.send(outMessage);
		}
		catch (MessagingException e) { 
			_log.info("ERROR: Could not send out Email Results response : " + e.getMessage());
		}
	}
}
