<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge" name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cyber Guardian | User Dashboard</title>
	<link rel="stylesheet" href="style.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>

<body>
	<%@ include file="UserHeader.jsp" %>
    
	<div class="dashboard">
		<a href="UserServlet?action=total_tutorials" class="box-link">
			<div class="boxes" style="height: 250px; width: 300px;">
				<label><h2>Total Tutorials: <%= request.getAttribute("totalTutorials") %></h2></label><br>
				<img src="images/tutorials_icon.png" alt="Cyber Logo" style="width:50%;">
			</div>
		</a>
		
		<a href="UserServlet?action=my_tutorials" class="box-link">
			<div class="boxes" style="height: 250px; width: 300px;">
				<label><h2>My Tutorials: <%= request.getAttribute("totalMyTutorials") %></h2></label><br>
				<img src="images/tutorials_icon.png" alt="Cyber Logo" style="width:50%;">
			</div>
		</a>

		<a href="UserServlet?action=my_certificates" class="box-link">
			<div class="boxes" style="height: 250px; width: 300px;">
				<label><h2>My Certificates: <%= request.getAttribute("totalMyCertificates") %></h2></label><br>
				<img src="images/certificates_icon.png" alt="Cyber Logo" style="width: 50%;">
			</div>
		</a>

		<a href="UserServlet?action=my_attempted_quizzes" class="box-link">
			<div class="boxes" style="height: 250px; width: 300px;">
				<label><h2>Attempted Exams: <%= request.getAttribute("totalAttemptedQuizzes") %></h2></label><br>
				<img src="images/quizzes_icon.png" alt="Cyber Logo" style="width: 50%;">
			</div>
		</a>
	</div>
</body>
</html>