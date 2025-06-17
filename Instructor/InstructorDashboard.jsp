<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge" name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cyber Guardian | Instructor Dashboard</title>
	<link rel="stylesheet" href="style.css">
	<script src="js/script.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>

<body>
	<%@ include file="InstructorHeader.jsp" %>
    
	<div class="dashboard">
		<a href="InstructorServlet?action=users_list" class="box-link">
			<div class="boxes" style="height: 250px;">
				<label><h2>My Users: <%= request.getAttribute("totalUsers") %></h2></label><br>
				<img src="images/user_icon.png" alt="Cyber Logo" style="width: 50%;">
			</div>
		</a>

		<a href="InstructorServlet?action=tutorials" class="box-link">
			<div class="boxes" style="height: 250px;">
				<label><h2>My Tutorials: <%= request.getAttribute("totalTutorials") %></h2></label>
				<img src="images/tutorials_icon.png" alt="Cyber Logo" style="width: 50%;">
			</div>
		</a>

		<a href="InstructorServlet?action=certificates" class="box-link">
			<div class="boxes" style="height: 250px;">
				<label><h2>Total Certificates: <%= request.getAttribute("totalCertificates") %></h2></label><br>
				<img src="images/certificates_icon.png" alt="Cyber Logo" style="width: 50%;">
			</div>
		</a>
		
		<!-- <a href="InstructorServlet?action=questions_list" class="box-link">
			<div class="boxes">
				<label>Total Questions: <%--<%= request.getAttribute("totalQuestions") %> --%></label><br>
				<img src="images/question_icon.png" alt="Cyber Logo" style="width:50%;">
			</div>
		</a> -->
	</div>
</body>
</html>