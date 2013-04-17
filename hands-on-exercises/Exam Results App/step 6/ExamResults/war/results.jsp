<%@ page import="com.mindstormsoftware.examresults.entity.*" %>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Exam Results</title>
  </head>

  <body>
    <h1>Welcome to Exam Results Portal</h1>
    <hr/>
    <%
      ExamResult ER = (ExamResult)session.getAttribute("result");
      if (ER != null) {
    	  %>
    	   <table>
	      <tr>
	        <td style="font-weight:bold;">Seat Number:</td>        
	        <td><%=ER.getSeatNumber().getName()%></td>
	      </tr>
	      <tr>
	        <td style="font-weight:bold;">Full Name:</td>        
	        <td><%=ER.getStudentName()%></td>
	      </tr>
	      <tr>
	        <td style="font-weight:bold;">Mathematics:</td>        
	        <td><%=ER.getMarks_Math()%></td>
	      </tr>
	      <tr>
	        <td style="font-weight:bold;">Communication Skills:</td>        
	        <td><%=ER.getMarks_CommSkills()%></td>
	      </tr>
	      <tr>
	        <td style="font-weight:bold;">Programming Languages:</td>        
	        <td><%=ER.getMarks_Programming()%></td>
	      </tr>
	      <tr>
	        <td style="font-weight:bold;">Electronic Circuits:</td>        
	        <td><%=ER.getMarks_ElectronicCircuits()%></td>
	      </tr>
	      <tr>
	        <td style="font-weight:bold;">Grand Total:</td>        
	        <td><%=ER.getMarks_Total()%>/400</td>
	      </tr>
	      <tr>
	        <td style="font-weight:bold;">Percentage:</td>        
	        <td><%=ER.getMarks_Percentage()%>%</td>
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
