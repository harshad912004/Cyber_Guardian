<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, Servlet_Codes.DataClasses.SpecializationsData" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Upload Cybersecurity Quiz</title>
    <link rel="stylesheet" href="style.css">
    <style>
        .form-container {
            width: 400px;
            margin: 100px auto;
            padding: 30px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
        }
        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-container label {
            display: block;
            margin-top: 15px;
            margin-bottom: 5px;
        }
        .form-container input, .form-container select {
            width: 100%;
            padding: 8px 12px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-container button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            margin-top: 15px;
            cursor: pointer;
        }
        .form-container button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <%@ include file="InstructorHeader.jsp" %>

    <div class="form-container">
        <form action="AddNewQuizzesServlet" method="post">

            <h2>Upload Cybersecurity Quiz</h2>

            <label for="specialization">Specialization<span style="color:red;">*</span></label>
            <select id="specialization" name="specialization" required>
                <%
                    List<SpecializationsData> dataList = (List<SpecializationsData>) request.getAttribute("dataList");
                    if (dataList != null && !dataList.isEmpty()) {
                        for (SpecializationsData data : dataList) {
                %>
                            <option value=<%= data.getId() %>><%= data.getField() %></option>
                <%
                        }
                    } else {
                %>
                        <option value="">No Specializations Available</option>
                <%
                    }
                %>
            </select>

            <label for="question">Question<span style="color:red;">*</span></label>
			<input type="text" id="question" name="question" required>
			
			<label for="option_a">Option A<span style="color:red;">*</span></label>
			<input type="text" id="option_a" name="option_a" required>
			
			<label for="option_b">Option B<span style="color:red;">*</span></label>
			<input type="text" id="option_b" name="option_b" required>
			
			<label for="option_c">Option C<span style="color:red;">*</span></label>
			<input type="text" id="option_c" name="option_c" required>
			
			<label for="option_d">Option D<span style="color:red;">*</span></label>
			<input type="text" id="option_d" name="option_d" required>
			
			<label for="correct_option">Correct Option<span style="color:red;">*</span></label>
			<select id="correct_option" name="correct_option" required>
			    <option value="">-- Select Correct Option --</option>
			    <option value="A">Option A</option>
			    <option value="B">Option B</option>
			    <option value="C">Option C</option>
			    <option value="D">Option D</option>
			</select>
			
			<button type="submit">Add Quiz</button>

        </form>
    </div>

</body>
</html>