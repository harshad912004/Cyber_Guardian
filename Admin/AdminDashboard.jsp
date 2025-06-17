<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge" name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cyber Guardian | Admin Dashboard</title>
	<link rel="stylesheet" href="style.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>

<body>
	<%@ include file="AdminHeader.jsp" %>
    
	<div class="dashboard">
	<!-- <div> -->
		<a href="AdminServlet?action=instructors_list" class="box-link">
			<div class="boxes" style="height: 250px;">
				<label><h2>Total Instructors: <%= request.getAttribute("totalInstructors") %></h2></label><br>
				<img src="images/instructor_icon.png" alt="Cyber Logo" style="width:50%;">
			</div>
		</a>

		<a href="AdminServlet?action=users_list" class="box-link">
			<div class="boxes" style="height: 250px;">
				<label><h2>Total Users: <%= request.getAttribute("totalUsers") %></h2></label><br><br>
				<img src="images/user_icon.png" alt="Cyber Logo" style="width: 50%;">
			</div>
		</a>

		<a href="AdminServlet?action=tutorials" class="box-link">
			<div class="boxes" style="height: 250px;">
				<label><h2>Total Tutorials: <%= request.getAttribute("totalTutorials") %></h2></label><br>
				<img src="images/tutorials_icon.png" alt="Cyber Logo" style="width: 50%;">
			</div>
		</a>

		<a href="AdminServlet?action=specializations" class="box-link">
			<div class="boxes" style="height: 250px;">
				<label><h2>Total Specializations: <%= request.getAttribute("totalSpecializations") %></h2></label><br>
				<img src="images/specialization_icon.png" alt="Cyber Logo" style="width: 50%;">
			</div>
		</a>
	
		<!-- Chart 1: User Status 
	    <div class="chart-container">
	        <canvas id="userStatusChart"></canvas>
	    </div> 
	
	    <!-- Chart 2: Users by Role 
	    <div class="chart-container">
	        <canvas id="totalInstructors"></canvas>
	    </div>
	
	    <!-- Chart 3: New Users Per Month 
	    <div class="chart-container">
	        <canvas id="newUsersChart"></canvas>
	    </div>
	
	    <script>        
	        // 2️⃣ Users by Role Chart
	        const ctx2 = document.getElementById('totalInstructors').getContext('2d');
	        new Chart(ctx2, {
	            type: 'pie',
	            data: {
	                labels: ['Admin', 'Instructor', 'User'],
	                datasets: [{
	                    label: 'Number of Users',
	                    data: [2, 12, 30],
	                    backgroundColor: ['#2196F3', '#FFC107', '#8BC34A']
	                }]
	            },
	            options: {
	                responsive: true,
	                scales: {
	                    y: { beginAtZero: true }
	                }
	            }
	        });
	
	        // 3️⃣ New Users Registered Per Month
	        const ctx3 = document.getElementById('newUsersChart').getContext('2d');
	        new Chart(ctx3, {
	            type: 'bar',
	            data: {
	                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'July', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
	                datasets: [{
	                    label: 'New Users Registered',
	                    data: [120, 140, 110, 160, 180, 200, 110, 160, 180, 110, 150, 190],
	                    backgroundColor: '#673AB7'
	                }]
	            },
	            options: {
	                responsive: true,
	                scales: {
	                    y: { beginAtZero: true }
	                }
	            }
	        });
	    </script>
    </div>-->
    </div>
</body>
</html>