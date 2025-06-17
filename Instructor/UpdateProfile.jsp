<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, Servlet_Codes.DataClasses.LoggedInUserData"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge" name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cyber Guardian | Update Profile Form</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
    <%@ include file="InstructorHeader.jsp" %>

    <div class="change-password-form">
        <div class="form-container">
            <form action="AdminServlet?action=save_profile" method="post" class="form-form">
                <%
                    LoggedInUserData userData = (LoggedInUserData) request.getAttribute("user_data");
                %>	            
                <h2 style="text-align: center; margin-bottom: 15px;">Update Profile</h2>
                <label for="username">Full Name<span style="color:red;">*</span></label>
                <input type="text" id="user_name" name="user_name" value="<%= userData.getName() %>" placeholder="Enter full name" pattern="[a-zA-Z ]+" style="text-transform: capitalize;" required>
    
                <label for="email">Email Address<span style="color:red;">*</span></label>
                <input type="email" id="user_email" name="user_email" value="<%= userData.getEmailID() %>" placeholder="Enter valid email address" required>
    
                <label for="phone">Phone Number<span style="color:red;">*</span></label>
                <input type="tel" id="user_phone" name="user_phone" value="<%= userData.getPhone_number() %>" placeholder="Enter 10 digit phone number" maxlength="10" pattern="[1-9]{1}[0-9]{9}" required>
                
                <input type="hidden" id="user_id" name="user_id" value="<%= userData.getId() %>">
                
                <button type="submit">Update</button>
            </form>
        </div>
    </div>
    
</body>
</html>