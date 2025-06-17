<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List, Servlet_Codes.DataClasses.InstructorListData"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<link rel="stylesheet" href="style.css">
	<script src="js/script.js"></script>
</head>
<body>
    <%@ include file="AdminHeader.jsp" %>    
	<p class="container_heading">Instructors</p>
	<div class="main_container">		
		<div class="search_div">
			<input type="text" id="searchInput" placeholder="Search...">
		</div><br>			
		<table border='1' id="dataTable" class="data_tables">
			<tr style="font-size:18px;">
				<th>Sr No.</th>
				<th>Name</th>
				<th>Email</th>
				<th>Phone Number</th>
				<th>Specialization</th>
				<th>Experience</th>
				<th>Address</th>
				<th>Birth Date</th>
				<th>Gender</th>
			</tr>
			<%
				List<InstructorListData> dataList = (List<InstructorListData>) request.getAttribute("dataList");
				int tableRowNumber = 1;
				if(dataList != null)
				{
					for(InstructorListData data : dataList)
					{
			%>
						<tr>
							<td style="text-align: center;"><%= tableRowNumber++ %></td>
							<td style="text-transform: capitalize; text-align: center;"><%= data.getName() %></td>
							<td style="text-align: center;"><%= data.getEmailID() %></td>
							<td style="text-align: center;"><%= data.getPhone_number() %></td>
							<td style="text-transform: capitalize; text-align: center;"><%= data.getField() %></td>
							<td style="text-align: center;"><%= data.getYear_Of_Experience() %></td>
							<td style="text-transform: capitalize; text-align: center;"><%= data.getAddress() %></td>
							<td style="text-align: center;"><%= data.getBirthDate() %></td>
							<td style="text-transform: capitalize; text-align: center;"><%= data.getGender() %></td>
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