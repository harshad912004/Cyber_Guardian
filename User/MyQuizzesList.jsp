<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, Servlet_Codes.DataClasses.QuizzesData"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<link rel="stylesheet" href="style.css">
</head>
<body>
    <%@ include file="UserHeader.jsp" %>    
	<p class="container_heading">Attempted Quizzes</p>	
	<div class="main_container">		
		<div class="search_div">
			<input type="text" id="searchInput" placeholder="Search...">
		</div><br>			
		<table border='1' id="dataTable" class="data_tables">
			<tr style="font-size:18px;">
				<th>Sr No.</th>
				<th>Tutorial Name</th>
				<th>Instructor Name</th>
				<th>Score</th>
			</tr>
			<%
				List<QuizzesData> dataList = (List<QuizzesData>) request.getAttribute("dataList");
				int tableRowNumber = 1;
				if(dataList != null)
				{
					for(QuizzesData data : dataList)
					{
			%>
						<tr>
							<td style="text-align: center;"><%= tableRowNumber++ %></td>
							<td style="text-transform: capitalize; text-align: center;"><%= data.getTitle() %></td>
							<td style="text-align: center;"><%= data.getInstructorName() %></td>
							<td style="text-align: center;"><%= data.getScore() %></td>
						</tr>
			<%
					}
				}
			%>
		</table>
	</div>    
    <script>
	    searchTable("dataTable", "searchInput");
	</script>
</body>
</html>