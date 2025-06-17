<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CyberGuardian | Login Page</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
	<!-- Navigation Bar -->
	<nav style="box-shadow: 4px 4px 4px 4px rgba(0, 0, 0, 0.1);">
		<a href="#" class="logo" style="color: #4e55e0;">CyberGuardian</a>
		<ul>
			<li><a href="index.html" class="cta-btn">HOME</a></li>
			<li><a href="LoginServlet?action=user_register" class="cta-btn">REGISTER</a></li>
		</ul>
	</nav>
	
	<!--login message for users-->
    <h1 class="login-form-heading">Welcome, Please login to proceed!</h1>
    
    <div class="login-form">
    	<form action="LoginServlet?action=login" method="post">

			<%-- Retrieve and display error messages (if any) --%>
    		<% 	List<String> errors = (List<String>) request.getAttribute("errors");	%>
    		<%	if(errors != null && !errors.isEmpty())
    			{
    		%>
    				<ul>
    		<%		
    				for(String error : errors)
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
    		<% String success_msg = (String) request.getAttribute("successMessage");	%>
			<%
				if (success_msg != null && !success_msg.isEmpty())
				{
			%>
			  		<li style="color: green;"><%= success_msg %></li>
			<%
				}
			%>
			<br>
			
			<label for="email">Your Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Your Password:</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">Login</button>
            
            <label for="text">Don't have an account? Click <a href="LoginServlet?action=user_register">here</a> to register.</label>
    	</form>
    </div>
</body>
</html>