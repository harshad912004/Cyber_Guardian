<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="Servlet_Codes.DataClasses.TutorialsData"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="style.css">
    <script src="js/script.js"></script>
    <title>View Tutorial</title>
</head>
<body>
    <%@ include file="AdminHeader.jsp" %>
    <div class="main_container">
        <%
            TutorialsData tutorial = (TutorialsData) request.getAttribute("tutorial");
            if(tutorial != null) {
        %>
	            <h2><%= tutorial.getTitle() %></h2>
	            <p><strong>Type:</strong> <%= tutorial.getType() %></p>
	            <p><strong>Instructor:</strong> <%= tutorial.getInstructorName() %></p>
	            <div>
	                <h3>Content:</h3>
	                <p><%= tutorial.getContent() %></p>
	            </div>
        <%
            } else {
        %>
            <p>Tutorial not found.</p>
        <%
            }
        %>
    </div>
</body>
</html>