<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge" name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cyber Guardian | User Dashboard</title>
	<link rel="stylesheet" href="style.css">
	<script src="js/script.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>

<body>
	<nav style="box-shadow: 4px 4px 4px 4px rgba(0, 0, 0, 0.1);">
	
		<a class="logo" style="color: #4e55e0;">CyberGuardian</a>
		
		<div class="dropdown" style="position: relative; display: inline-block;">
			<% String username = (String) session.getAttribute("name"); %>
			<button class="dropdown-btn">
				<%= (username != null) ? username : "User Account" %>
			</button>
			<div class="dropdown-content">
				<a href="UserServlet?action=update_profile">Update Profile</a>
				<a href="UserServlet?action=change_password">Change Password</a>
				<a href="LogoutServlet">Logout</a>
			</div>
		</div>
	</nav>
	
	<div class="sidebar">
	    <ul class="sidebar-menu">
	    
	    	<!-- User Dashboard -->
	        <li><a href="UserServlet?action=dashboard"><i class="fas fa-home"></i> Dashboard</a></li>
	        
	        <!-- Tutorial Management -->
            <li><a href="UserServlet?action=my_tutorials"><i class="fas fa-book"></i> View Tutorials</a></li>

	        <!-- Certifications Management -->
	        <li><a href="UserServlet?action=my_certificates"><i class="fas fa-certificate"></i> My Certfications</i></a></li>
	        
	        <!-- Quizzes Management -->
	        <li><a href="UserServlet?action=my_attempted_quizzes"><i class="fas fa-question"></i> My Attempted Quizzes</i></a></li>
	        
	        <!-- Password Management -->
	        <li><a href="UserServlet?action=password_generator"><i class="fas fa-lock"></i> Password Generator</i></a></li>
	        <li><a href="UserServlet?action=password_checker"><i class="fas fa-lock"></i> Password Checker</i></a></li>
	        
	        <!-- Quiz/Exam Management 
	        <li><a href="UserServlet?action=quiz_list"><i class="fas fa-quiz">View Quizzes</i></a></li> -->
	
	        <!-- Logout -->
	        <li><a href="LogoutServlet"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
	    </ul>
	</div>
</body>
</html>