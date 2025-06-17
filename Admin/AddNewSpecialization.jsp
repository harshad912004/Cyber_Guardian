<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, Servlet_Codes.DataClasses.SpecializationsData"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <%@ include file="AdminHeader.jsp" %><br>

	<div class="change-password-form">
		<div class="form-container">
			<form action="AddNewSpecializationServlet" method="post" class="form-form">
				<h2 style="margin-bottom:20px; text-align:center;">Add New Specialization</h2>
				
				<label for="username">Field Name<span style="color:red;">*</span></label>
				<input type="text" id="field_name" name="field_name" placeholder="Enter field name" pattern="[a-zA-Z ]+" style="text-transform: capitalize;" required>
				
				<button type="submit">Add</button>
			</form>
		</div>
	</div>
</body>
</html>