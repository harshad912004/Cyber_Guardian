<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge" name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cyber Guardian | Admin Dashboard</title>
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
				<%= (username != null) ? username : "Admin Account" %>
			</button>
			<div class="dropdown-content">
				<a href="AdminServlet?action=update_profile">Update Profile</a>
				<a href="AdminServlet?action=change_password">Change Password</a>
				<a href="LogoutServlet">Logout</a>
			</div>
		</div>
	</nav>
	
	<div class="sidebar">
	    <ul class="sidebar-menu">
	    
	    	<!-- Admin Dashboard -->
	        <li><a href="AdminServlet?action=dashboard"><i class="fas fa-home"></i> Dashboard</a></li>
	        
	        <!-- User Management -->
            <li><a href="AdminServlet?action=users_list"><i class="fas fa-user-circle"></i> View Users</a></li>
            <li><a href="AdminServlet?action=instructors_list"><i class="fas fa-user-circle"></i> View Instructors</a></li>
            <li><a href="AdminServlet?action=add_new_instructor"><i class="fas fa-user-circle"></i> Add New Instructor</a></li>
	        
	        <!-- Specialization Management -->
	        <li><a href="AdminServlet?action=specializations"><i class="fas fa-book"></i> View Specializations</i></a></li>
	        <li><a href="AdminServlet?action=add_new_specialization"><i class="fas fa-book"></i> Add New Specialization</a></li>

	        <!-- Tutorial Management -->
	        <li><a href="AdminServlet?action=tutorials"><i class="fas fa-school"></i> View Tutorials</i></a></li>
	        
	        <!-- Certifications Management -->
	        <li><a href="AdminServlet?action=certificates"><i class="fas fa-certificate"></i> View Certifications</i></a></li>
	        
	        <!-- Quiz/Exam Management 
	        <li class="dropdown">
	            <a href="#"><i class="fas fa-question-circle"></i> Quiz/Exam Management <i class="fas fa-chevron-down"></i></a>
	            <ul class="submenu">
	                <li><a href="AdminServlet?action=quiz_list">View Quizzes</a></li>
	                <li><a href="AdminServlet?action=create_quiz">Create Quiz</a></li>
	                <li><a href="AdminServlet?action=view_responses">View Responses</a></li>
	            </ul>
	        </li> -->
	
	        <!-- Chatbot Management 
	        <li class="dropdown">
	            <a href="#"><i class="fas fa-comments"></i> Chatbot Management <i class="fas fa-chevron-down"></i></a>
	            <ul class="submenu">
	                <li><a href="AdminServlet?action=manage_faq">Manage FAQs</a></li>
	                <li><a href="AdminServlet?action=train_chatbot">Train Chatbot</a></li>
	                <li><a href="AdminServlet?action=support_queries">User Support Queries</a></li>
	            </ul>
	        </li> -->
	
	        <!-- Incident Tracking Management
	        <li><a href="#"><i class="fas fa-lock"></i> Incident Tracking</i></a></li>
            <li><a href="AdminServlet?action=log_incident"><i class="fas fa-lock"></i> Log New Incident</a></li>
            <li><a href="AdminServlet?action=incident_reports"><i class="fas fa-lock"></i> View Incident Reports</a></li>
            <li><a href="AdminServlet?action=assign_incident"><i class="fas fa-lock"></i> Assign Resolutions</a></li> -->
	        
	
	        <!-- User Activity Monitoring 
	        <li><a href="AdminServlet?action=user_activity"><i class="fas fa-chart-bar"></i> User Activity Monitoring</a></li> -->
	
	        <!-- Logout Management -->
	        <li><a href="LogoutServlet"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
	    </ul>
	</div>
</body>
</html>