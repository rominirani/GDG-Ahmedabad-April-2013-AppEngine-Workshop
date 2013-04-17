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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;
import com.mindstormsoftware.examresults.dao.ExamResultDAO;
import com.mindstormsoftware.examresults.entity.ExamResult;

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
			ExamResult ER = ExamResultDAO.INSTANCE.getExamResult(seatNumber);
			strCallResult = "Seat Number : " + ER.getSeatNumber().getName() + "\r\n";
			strCallResult += "Student Name : " + ER.getStudentName() + "\r\n";
			strCallResult += "Mathematics : " + ER.getMarks_Math() + "\r\n";
			strCallResult += "Communication Skills : " + ER.getMarks_CommSkills() + "\r\n";
			strCallResult += "Electronic Circuits : " + ER.getMarks_ElectronicCircuits() + "\r\n";
			strCallResult += "Programming Languages : " + ER.getMarks_Programming() + "\r\n";
			strCallResult += "Total Marks : " + ER.getMarks_Total() + "/400" + "\r\n";
			strCallResult += "Percentage : " + ER.getMarks_Percentage() + "%" + "\r\n";
			strCallResult += "=================================" + "\r\n";
			strCallResult += "Thank you for using the Exam Helper Bot" + "\r\n";
				
			//Send out Email
			MimeMessage outMessage = new MimeMessage(session);
			outMessage.setFrom(new InternetAddress("admin@exam-results.appspotemail.com"));
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
