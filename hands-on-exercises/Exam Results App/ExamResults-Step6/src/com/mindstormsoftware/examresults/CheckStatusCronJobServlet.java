package com.mindstormsoftware.examresults;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.appengine.api.xmpp.JID;
import com.google.appengine.api.xmpp.Message;
import com.google.appengine.api.xmpp.MessageBuilder;
import com.google.appengine.api.xmpp.SendResponse;
import com.google.appengine.api.xmpp.XMPPService;
import com.google.appengine.api.xmpp.XMPPServiceFactory;


@SuppressWarnings("serial")
public class CheckStatusCronJobServlet extends HttpServlet {
	private static final Logger _logger = Logger.getLogger(CheckStatusCronJobServlet.class.getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		try {
			_logger.info("Executing Cron Job .... ");
			URL url = new URL("http://search.twitter.com/search.json?q=GDGAhmedabad");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer SB = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                SB.append(line);
            }
            reader.close();
            //System.out.println(SB.toString());
            JSONObject response = new JSONObject(SB.toString());
            JSONArray results = response.getJSONArray("results");
            //System.out.println("There are " + results.length() + " Tweets");
            JSONObject recentTweet = (JSONObject)results.get(0);
            String msg = "Most recent tweet by " + recentTweet.getString("from_user_name") + " : " + recentTweet.getString("text");
            
            //Send out IM
    		XMPPService xmpp = null;
    		JID toJid = new JID("romin.k.irani@gmail.com");
   			xmpp = XMPPServiceFactory.getXMPPService();
	        Message replyMessage = new MessageBuilder().withRecipientJids(toJid).withBody(msg).build();
	        xmpp.sendMessage(replyMessage);
        }
		catch (Exception ex) {
			String logMsg = "Exception in executing Cron Job : " + ex.getMessage();
			_logger.log(Level.INFO,logMsg);
		}
	}
}
