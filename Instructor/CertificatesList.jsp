<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, Servlet_Codes.DataClasses.CertificateData"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<link rel="stylesheet" href="style.css">
	<script src="js/script.js"></script>
</head>
<body>
    <%@ include file="InstructorHeader.jsp" %>    
	<p class="container_heading">Certificates</p>	
	<div class="main_container">		
		<div class="search_div">
			<input type="text" id="searchInput" placeholder="Search...">
		</div><br>			
		<table border='1' id="dataTable" class="data_tables">
			<tr style="font-size:18px;">
				<th>Sr No.</th>
				<th>Tutorial Name</th>
				<th>User Name</th>
				<th>Issue Date</th>
			</tr>
			<%
				List<CertificateData> dataList = (List<CertificateData>) request.getAttribute("dataList");
				int tableRowNumber = 1;
				if(dataList != null)
				{
					for(CertificateData data : dataList)
					{
			%>
						<tr>
							<td style="text-align: center;"><%= tableRowNumber++ %></td>
							<td style="text-transform: capitalize; text-align: center;"><%= data.getTutorialName() %></td>
							<td style="text-align: center;"><%= data.getUserName() %></td>
							<td style="text-align: center;"><%= data.getIssueDate() %></td>
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