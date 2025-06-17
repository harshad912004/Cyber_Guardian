package Servlet_Codes;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)

@WebServlet("/AddNewTutorialsServlet")
public class AddNewTutorialsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String UPLOAD_DIR = null;
	
    public AddNewTutorialsServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		Object instructorObj = session.getAttribute("instructor_id");
		if (instructorObj == null) {
		    out.println("<h3>Error: Instructor ID is missing. Please log in again.</h3>");
		    return;
		}
		int instructorId = (int) instructorObj;
		
		String title = request.getParameter("title");
        String description = request.getParameter("description");
        String fileType = request.getParameter("fileType");
        Part filePart = request.getPart("file");

        String fileName = extractFileName(filePart);
        String savePath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        
        File uploadDir = new File(savePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
        String filePath = savePath + File.separator + fileName;
        filePart.write(filePath);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cyber_guardian", "root", "");

            String sql = "INSERT INTO tutorials (instructor_id, title, description, file_type, file_path) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, instructorId);
            pstmt.setString(2, title);
            pstmt.setString(3, description);
            pstmt.setString(4, fileType);
            pstmt.setString(5, fileName);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                out.println("Tutorial uploaded successfully!");
            }
            RequestDispatcher disp1 = request.getRequestDispatcher("/InstructorServlet?action=tutorials");
			disp1.forward(request, response);
            c.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace();
        }
	}
	
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String content : contentDisp.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return "unknown";
    }
}