package Servlet_Codes;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Servlet_Codes.DataClasses.QuizzesData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ViewTutorialsServlet")
public class ViewTutorialsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewTutorialsServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        String filePath = request.getParameter("filePath");
        String tutorialIdStr = request.getParameter("tutorialId");

        if (filePath == null || filePath.isEmpty() || tutorialIdStr == null || tutorialIdStr.isEmpty()) {
            response.getWriter().println("Missing file path or tutorial ID!");
            return;
        }

        int user_id = (int) session.getAttribute("id");
        int tutorialID = Integer.parseInt(tutorialIdStr);

        filePath = filePath.replace("\\", "/");
        String fullPath = getServletContext().getRealPath("/Specialization Notes/" + filePath);

        File file = new File(fullPath);

        if (!file.exists() || file.isDirectory()) {
            response.getWriter().println("File not found at path: " + fullPath);
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cyber_guardian", "root", "");
            
            String checkQuery = "SELECT id FROM tutorials_progress WHERE user_id = ? AND tutorial_id = ?";
            try (PreparedStatement psCheck = c.prepareStatement(checkQuery)) {
                psCheck.setInt(1, user_id);
                psCheck.setInt(2, tutorialID);

                try (ResultSet rs = psCheck.executeQuery()) {
                    if (!rs.next()) {
                        String insertQuery = "INSERT INTO tutorials_progress (user_id, tutorial_id, completed_date) VALUES (?, ?, CURRENT_DATE)";
                        try (PreparedStatement psInsert = c.prepareStatement(insertQuery)) {
                            psInsert.setInt(1, user_id);
                            psInsert.setInt(2, tutorialID);
                            psInsert.executeUpdate();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Database error occurred!");
            return;
        }

        String mimeType = getServletContext().getMimeType(fullPath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        try (FileInputStream inStream = new FileInputStream(file);
             OutputStream outStream = response.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}