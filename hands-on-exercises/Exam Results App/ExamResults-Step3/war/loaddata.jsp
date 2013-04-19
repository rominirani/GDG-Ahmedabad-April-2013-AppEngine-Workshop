<%@page import="com.mindstormsoftware.examresults.util.DataLoader"%>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Exam Results</title>
  </head>

  <body>
    <h1>Initial Data Loader</h1>
    <%
    DataLoader.loadData();
    %>
    <hr/>
    <a href="index.html">Back to Home</a>
  </body>
</html>
