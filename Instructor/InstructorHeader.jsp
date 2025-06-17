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
	<nav style="box-shadow: 4px 4px 4px 4px rgba(0, 0, 0, 0.1);">
	
		<a class="logo" style="color: #4e55e0;">CyberGuardian</a>
		
		<div class="dropdown" style="position: relative; display: inline-block;">
			<% String username = (String) session.getAttribute("name"); %>
			<button class="dropdown-btn">
				<%= (username != null) ? username : "Instructor Account" %>
			</button>
			<div class="dropdown-content">
				<a href="InstructorServlet?action=update_profile">Update Profile</a>
				<a href="InstructorServlet?action=change_password">Change Password</a>
				<a href="LogoutServlet">Logout</a>
			</div>
		</div>
	</nav>
	
	<div class="sidebar">
	    <ul class="sidebar-menu">
	    
	    	<!-- Instructor Dashboard -->
	        <li><a href="InstructorServlet?action=dashboard"><i class="fas fa-home"></i> Dashboard</a></li>
	        
	        <!-- User Management Dropdown -->
            <li><a href="InstructorServlet?action=users_list"><i class="fas fa-user-circle"></i> My Users</a></li>

	        <!-- Tutorial Management Dropdown -->
	        <li><a href="InstructorServlet?action=tutorials"><i class="fas fa-book"></i> My Tutorials</a></li>
	        <li><a href="InstructorServlet?action=add_new_tutorials"><i class="fas fa-book"></i> Add New Tutorial</a></li>
	        
	        <!-- Quiz Management Dropdown -->
	        <li><a href="InstructorServlet?action=add_new_quizzes"><i class="fas fa-question"></i> Add New Quiz</a></li>
	        
	        <!-- Certifications Management -->
	        <li><a href="InstructorServlet?action=certificates"><i class="fas fa-certificate"></i> View Certifications</a></li>
	        
	        <!-- Quiz/Exam Management 
	        <li class="dropdown">
	            <a href="#"><i class="fas fa-question-circle"></i> Quiz/Exam Management <i class="fas fa-chevron-down"></i></a>
	            <ul class="submenu">
	                <li><a href="InstructorServlet?action=quiz_list">View Quizzes</a></li>
	                <li><a href="InstructorServlet?action=create_quiz">Create Quiz</a></li>
	                <li><a href="InstructorServlet?action=view_responses">View Responses</a></li>
	            </ul>
	        </li> -->
	
	        <!-- Chatbot Management 
	        <li class="dropdown">
	            <a href="#"><i class="fas fa-comments"></i> Chatbot Management <i class="fas fa-chevron-down"></i></a>
	            <ul class="submenu">
	                <li><a href="InstructorServlet?action=manage_faq">Manage FAQs</a></li>
	                <li><a href="InstructorServlet?action=train_chatbot">Train Chatbot</a></li>
	                <li><a href="InstructorServlet?action=support_queries">User Support Queries</a></li>
	            </ul>
	        </li> -->
	
	        <!-- User Activity Monitoring 
	        <li><a href="InstructorServlet?action=user_activity"><i class="fas fa-chart-bar"></i> User Activity Monitoring</a></li> -->
	
	        <!-- Logout -->
	        <li><a href="LogoutServlet"><i class="fas fa-sign-out-alt"></i>Logout</a></li>
	    </ul>
	</div>
</body>
</html>