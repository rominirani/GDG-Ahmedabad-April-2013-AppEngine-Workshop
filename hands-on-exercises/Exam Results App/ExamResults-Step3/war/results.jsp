<%@ page import="com.google.appengine.api.datastore.Entity" %>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Exam Results</title>
  </head>

  <body>
    <h1>Welcome to Exam Results Portal</h1>
    <hr/>
    <%
      Entity ER = (Entity)session.getAttribute("result");
      if (ER != null) {
    	  %>
    	   <table>
	      <tr>
	        <td style="font-weight:bold;">Seat Number:</td>        
	        <td><%=ER.getKey().getName()%></td>
	      </tr>
	      <tr>
	        <td style="font-weight:bold;">Full Name:</td>        
	        <td><%=ER.getProperty("studentName")%></td>
	      </tr>
	      <tr>
	        <td style="font-weight:bold;">Mathematics:</td>        
	        <td><%=ER.getProperty("marks_Math")%></td>
	      </tr>
	      <tr>
	        <td style="font-weight:bold;">Communication Skills:</td>        
	        <td><%=ER.getProperty("marks_CommSkills")%></td>
	      </tr>
	      <tr>
	        <td style="font-weight:bold;">Programming Languages:</td>        
	        <td><%=ER.getProperty("marks_Programming")%></td>
	      </tr>
	      <tr>
	        <td style="font-weight:bold;">Electronic Circuits:</td>        
	        <td><%=ER.getProperty("marks_ElectronicCircuits")%></td>
	      </tr>
	      <tr>
	        <td style="font-weight:bold;">Grand Total:</td>        
	        <td><%=ER.getProperty("marks_Total")%>/400</td>
	      </tr>
	      <tr>
	        <td style="font-weight:bold;">Percentage:</td>        
	        <td><%=ER.getProperty("marks_Percentage")%>%</td>
	      </tr>
	    </table>
    	  <%
      }
      else {
    	  %>
	    <h3>We could not provide you with the results.</h3>
    	  <%
      }
    %>
    <hr/>
    <a href="index.html">Back to Home</a>
  </body>
</html>
