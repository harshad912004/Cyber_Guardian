<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, Servlet_Codes.DataClasses.QuizzesData"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<link rel="stylesheet" href="style.css">
</head>
<body>
    <%@ include file="UserHeader.jsp" %>
    
    <h2>Quiz for Tutorial ID: <%= request.getAttribute("tutorialId") %></h2>

	<form action="SubmitQuizServlet" method="post">
	    <%
	        List<QuizzesData> quizList = (List<QuizzesData>) request.getAttribute("quizList");
	        int questionNumber = 1;
	        for (QuizzesData q : quizList) {
	    %>
	        <div class="quiz-question">
	            <h4>Q<%= questionNumber++ %>: <%= q.getQuestion() %></h4>
	            <input type="radio" name="question_<%= q.getId() %>" value="option_a"> <%= q.getOptionA() %><br>
	            <input type="radio" name="question_<%= q.getId() %>" value="option_b"> <%= q.getOptionB() %><br>
	            <input type="radio" name="question_<%= q.getId() %>" value="option_c"> <%= q.getOptionC() %><br>
	            <input type="radio" name="question_<%= q.getId() %>" value="option_d"> <%= q.getOptionD() %><br>
	        </div>
	        <hr>
	    <% } %>
	
	    <input type="hidden" name="tutorialId" value="<%= request.getAttribute("tutorialId") %>">
	    <button type="submit" class="submit-quiz-btn">Submit Quiz</button>
	</form>

</body>
</html>