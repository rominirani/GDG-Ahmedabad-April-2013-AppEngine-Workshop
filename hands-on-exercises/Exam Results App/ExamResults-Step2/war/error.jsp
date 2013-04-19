<%@ page isErrorPage="true"%>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Exam Results</title>
  </head>

  <body>
    <h1>Welcome to Exam Results Portal</h1>
    <hr/>
    <h3>We could not provide you with the results.</h3>
    <h3><b>Reason:</b><%=exception.getMessage()%></h3>
    <hr/>
    <a href="index.html">Back to Home</a>
  </body>
</html>
