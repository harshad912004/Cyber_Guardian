<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, Servlet_Codes.DataClasses.TutorialsData"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<link rel="stylesheet" href="style.css">
	<script src="js/script.js"></script>
</head>
<body>
    <%@ include file="UserHeader.jsp" %>
	<p class="container_heading">Tutorials</p>
	<div class="main_container">		
		<div class="search_div">
			<input type="text" id="searchInput" placeholder="Search...">
		</div><br>			
		<table border='1' id="dataTable" class="data_tables">
			<tr style="font-size:18px;">
				<th>Sr No.</th>
				<th>Title</th>
				<th>Description</th>
				<th>Instructor Name</th>
				<th>Action</th>
			</tr>
			<%
				List<TutorialsData> dataList = (List<TutorialsData>) request.getAttribute("dataList");
				int tableRowNumber = 1;
				if(dataList != null) {
					for(TutorialsData data : dataList) {
			%>
						<tr>
							<td style="text-align: center;"><%= tableRowNumber++ %></td>
							<td style="text-transform: capitalize; text-align: center;"><%= data.getTitle() %></td>
							<td style="text-align: center;"><%= data.getDescription() %></td>
							<td style="text-transform: capitalize; text-align: center;"><%= data.getInstructorName() %></td>
							<td style="text-align: center;">
								<form action="ViewTutorialsServlet" method="post" target="_blank" style="display:inline;">
								    <input type="hidden" name="tutorialId" value="<%= data.getId() %>">
								    <input type="hidden" name="filePath" value="<%= data.getFilePath() %>">
								    <button type="submit" class="view-button">DOWNLOAD</button>
								</form>
							</td>
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