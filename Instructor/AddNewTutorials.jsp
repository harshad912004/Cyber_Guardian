<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="style.css">
</head>
<body>
	<%@ include file="InstructorHeader.jsp" %><br>
	
    <div class="change-password-form" style="margin-top: 8.5rem; margin-left: 40rem;">
    	<div class="form-container">
		    <form action="AddNewTutorialsServlet" method="post" enctype="multipart/form-data">
		    
		    	<h2>Upload Cybersecurity Tutorial</h2>
		    	
		        <label style="margin-top: 1rem;">Title:</label>
		        <input type="text" name="title" style="margin-bottom: -1rem;" required><br><br>
		
		        <label>Description:</label>
		        <textarea name="description" style="margin-bottom: -1rem;" required></textarea><br><br>
		
		        <label>File Type:</label>
		        <select name="fileType" style="margin-bottom: -1rem;" required>
		            <option value="PDF">PDF</option>
		            <option value="DOCX">DOCX</option>
		            <option value="PPT">PPT</option>
		            <option value="VIDEO">VIDEO</option>
		            <option value="AUDIO">AUDIO</option>
		            <option value="TEXT">TEXT</option>
		        </select><br><br>
		
		        <label>Upload File:</label>
		        <input type="file" name="file" style="margin-bottom: -1rem;" required><br><br>
		
		        <button type="submit">Upload</button>
		    </form>
	    </div>
    </div>
</body>
</html>