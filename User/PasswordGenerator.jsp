<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" import="java.security.SecureRandom, java.util.Random"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge" name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cyber Guardian | Password Generator</title>
	<link rel="stylesheet" href="../style.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>

<body>
	<%@ include file="UserHeader.jsp" %>
	
	<%-- Display Error Message if Length is Invalid --%>
	<%
	    String errorMessage = request.getParameter("error");
	    if (errorMessage != null) {
	%>
	    	<p class="error"><%= errorMessage %></p>
	<%
	    }
	%>
	
	<form action="PasswordGeneratorServlet" method="post" style="margin-top: 10rem; margin-left: 38rem; width: 500px; font-weight: 700">
	    <label for="passwordLength">Enter Password Length (Min: 8):</label>
	    <input type="number" id="passwordLength" name="passwordLength" min="8" max="20" required>
	    <button type="submit">Generate Password</button>
	</form>
	
	<%-- Display Generated Password --%>
	<%
	    String generatedPassword = (String) request.getAttribute("generatedPassword");
	    if (generatedPassword != null) {
	%>
		    <h3 style="margin-top:1rem; margin-left: 38rem; margin-bottom: 0.5rem;">Generated Password:</h3>
		    <p style="margin-left: 38rem; color: green; padding: 8px; border: 1px solid #4e55e0; border-radius: 5px; width: 500px;"><strong><%= generatedPassword %></strong></p>
	<%
	    }
	%>

</body>
</html>