<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge" name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cyber Guardian | Password Change Form</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="InstructorHeader.jsp" %>

	<div class="change-password-form">
		<div id="form_container">
	    	<form action="InstructorServlet?action=save_new_password" method="post" id="passwordForm">
	            <label for="old_password">Old Password<span style="color:red;">*</span></label>
	            <input type="password" id="old_password" name="old_password" required><br>
	            
	            <label for="new_password">New Password<span style="color:red;">*</span></label>
	            <input type="password" id="new_password" name="new_password" required><br>
	            
	            <label for="cnew_password">Confirm New Password<span style="color:red;">*</span></label>
	            <input type="password" id="cnew_password" name="cnew_password" required onkeyup="checkPasswords()">
  				<p id="errorMessage" style="color: red;"></p><br>
	            
	            <button type="submit">Save</button>
	        </form>
	    </div>
	</div>
	    
    <script>
	    const passwordForm = document.getElementById("passwordForm");
	    const newPassword = document.getElementById("new_password");
	    const confirmPassword = document.getElementById("cnew_password");
	    const errorMessage = document.getElementById("errorMessage");
	    const submitButton = document.querySelector("button[type='submit']");
	
	    function checkPasswords() {
			if (newPassword.value !== confirmPassword.value) {
		        errorMessage.innerText = "Password do not match to New Password";
		        submitButton.disabled = true;
			}
			else {
				errorMessage.innerText = "";
				submitButton.disabled = false;
			}
	    }
	    confirmPassword.addEventListener("keyup", checkPasswords);
	</script>
  
</body>
</html>