package com.mindstormsoftware.examresults;

import java.io.IOException;
import java.util.logging.Logger;

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

public class ExamResultsBotServlet  extends HttpServlet {
	public static final Logger _log = Logger.getLogger(ExamResultsBotServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String strCallResult="";
		resp.setContentType("text/plain");
		XMPPService xmpp = null;
		JID fromJid = null;
		try {
	
			//STEP 1 - Extract out the message and the Jabber Id of the user sending us the message via the Google Talk client
			xmpp = XMPPServiceFactory.getXMPPService();
			Message msg = xmpp.parseMessage(req);

			fromJid = msg.getFromJid();
			String msgBody = msg.getBody();
			
			String strCommand = msgBody;
			
			_log.info("Received a message : " + strCommand + " from " + fromJid);
			
			//Do validations here. Only basic ones i.e. cannot be null/empty
			if (strCommand == null) throw new Exception("You must give a command.");
			
			//Trim the stuff
			strCommand = strCommand.trim();
			if (strCommand.length() == 0) throw new Exception("You must give a command.");
			
			String[] words = strCommand.split(" ");
			if (words.length == 1) {
				//This command will print the list of commands that the Bot understands
				if (words[0].equalsIgnoreCase("help")) {
					//Print out help
					StringBuffer SB = new StringBuffer();
					SB.append("***** Welcome to ExamResults Bot *****");
					SB.append("\r\nI understand the following commands:");
					SB.append("\r\n1. Type help to get the list of commands.");
					SB.append("\r\n2. Type <seatnumber> to get exam results for the seat number.");
					SB.append("\r\n3. Type about to get more information about this Agent.");
					strCallResult = SB.toString();
				}
				//This command will print out a brief message about the Bot
				else if (words[0].equalsIgnoreCase("about")) {
					strCallResult = "Hello! I am the ExamResults Bot version 1.0" + "\r\n";
				}
				//This command will subscribe a particular GTalk user to minor and major status of ExamResults
				else {
					//Retrieve the results
					ExamResult ER = ExamResultDAO.INSTANCE.getExamResult(words[0]);
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
				}
			}
			else {
				strCallResult = "Sorry! Could not understand your command.";
			}
			
			//Send out the Response message on the same XMPP channel. This will be delivered to the user via the Google Talk client.
	        Message replyMessage = new MessageBuilder().withRecipientJids(fromJid).withBody(strCallResult).build();
                
	        boolean messageSent = false;
	        //if (xmpp.getPresence(fromJid).isAvailable()) {
	        SendResponse status = xmpp.sendMessage(replyMessage);
	        messageSent = (status.getStatusMap().get(fromJid) == SendResponse.Status.SUCCESS);
	        //}
		}
		catch (Exception ex) {
			
			//If there is an exception then we send back a generic message to the client i.e. ExamResults Status Bot could not understand your command. Please
			//try again. We log the exception internally.
			_log.info("Something went wrong. Please try again!" + ex.getMessage());
	        Message replyMessage = new MessageBuilder()
            .withRecipientJids(fromJid)
            .withBody("ExamResults Status Bot could not understand your command. Please try again.")
            .build();
                
	        boolean messageSent = false;
	        //The condition is commented out so that it can work over non Google Talk XMPP providers also.
	        //if (xmpp.getPresence(fromJid).isAvailable()) {
	        SendResponse status = xmpp.sendMessage(replyMessage);
	        messageSent = (status.getStatusMap().get(fromJid) == SendResponse.Status.SUCCESS);
	        //}
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	


}
