<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" http-equiv="X-UA-Compatible" content="IE=edge" name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cyber Guardian | Password Checker</title>
	<link rel="stylesheet" href="../style.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <script>
        function checkStrength() {
            var password = document.getElementById("password").value;
            var strengthDisplay = document.getElementById("strength");

            if (password.length === 0) {
                strengthDisplay.innerHTML = "";
                strengthDisplay.style.color = "";
                return;
            }

            var strength = 0;
            if (password.length >= 8)
            	strength++;
            if (/[A-Z]/.test(password))
            	strength++;
            if (/[a-z]/.test(password))
            	strength++;
            if (/\d/.test(password))
            	strength++;
            if (/[!@#$%^&*()_+{}\[\]:;<>,.?~\\/ -]/.test(password))
            	strength++;

            if (strength <= 2) {
                strengthDisplay.innerHTML = "Weak";
                strengthDisplay.style.color = "red";
            } else if (strength === 3 || strength === 4) {
                strengthDisplay.innerHTML = "Medium";
                strengthDisplay.style.color = "orange";
            } else {
                strengthDisplay.innerHTML = "Strong";
                strengthDisplay.style.color = "green";
            }
        }
    </script>
</head>
<body>

	<%@ include file="UserHeader.jsp" %>
	
	<h2 style="margin-top: 10rem; margin-left: 38rem; margin-bottom: 0.5rem; width: 500px; font-weight: 700">Password Strength Checker</h2>
	
	<form action="UserServlet?action=dashboard" style="margin-left: 38rem; width: 500px;">
	    <input type="text" id="password" placeholder="Enter password" onkeyup="checkStrength()" required>
	    <p id="strength" class="strength" style="color:red;"></p>
	</form>

</body>
</html>