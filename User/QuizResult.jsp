<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, Servlet_Codes.DataClasses.QuizzesData"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<link rel="stylesheet" href="style.css">
</head>
<body>
    <%@ include file="UserHeader.jsp" %>
    <h2>Quiz Result</h2>
	
	<p>Your Score: <%= request.getAttribute("score") %> / <%= request.getAttribute("totalQuestions") %></p>
	
	<a href="UserDashboard.jsp">Back to Dashboard</a>

</body>
</html>