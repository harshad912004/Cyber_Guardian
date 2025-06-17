<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CyberGuardian | Registration Page</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
	<!-- Navigation Bar -->
	<nav style="box-shadow: 4px 4px 4px 4px rgba(0, 0, 0, 0.1);">
		<a href="#" class="logo" style="color: #4e55e0;">CyberGuardian</a>
		<ul>
			<li><a href="index.html" class="cta-btn">HOME</a></li>
		</ul>
	</nav>
	
	<!--Register message for users-->
	<h1 class="login-form-heading">User Registration Form</h1>
	
	<!-- registration form -->
    <div class="registration-form">
    	<form action="UserRegistrationServlet" method="post">
    		
    		<%-- Retrieve and display error messages (if any) --%>
			<% List<String> errors = (List<String>) request.getAttribute("errors"); %>
			<%
				if (errors != null && !errors.isEmpty())
				{
			%>
			  		<ul>
			<%
					for (String error : errors)
					{
			%>
			      		<li style="color: red;"><%= error %></li>
			<%
					}
			%>
					</ul>
			<%
				}
			%>
			
			<%-- Retrieve and display Success messages (if any) --%>
			<% String success_msg = (String) request.getAttribute("successMessage"); %>
			<%
				if (success_msg != null && !success_msg.isEmpty())
				{
			%>
			  		<p style="color: green;"><%= success_msg %></p>
			<%
				}
			%>
			
			<label for="username">Your Name:</label>
            <input type="text" id="username" name="username" placeholder="Enter full name" pattern="[a-zA-Z ]+" required>

            <label for="email">Your Email:</label>
            <input type="email" id="email" name="email" placeholder="Enter valid email address" required>

            <label for="phone">Your Phone Number:</label>
            <input type="tel" id="phone" name="phone" placeholder="Enter 10 digit phone number" maxlength="10" pattern="[1-9]{1}[0-9]{9}" required>

            <label for="password">Your Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter password" required>

            <label for="gender">Select Your Gender:</label>
            <select id="gender" name="gender" required>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
            </select>
            
            <button type="submit">Register Now</button>
    	</form>
    </div>
</body>
</html>