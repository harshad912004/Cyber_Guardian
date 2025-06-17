package Servlet_Codes;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Servlet_Codes.DataClasses.CertificateData;
import Servlet_Codes.DataClasses.QuizzesData;
import Servlet_Codes.DataClasses.LoggedInUserData;
import Servlet_Codes.DataClasses.TutorialsData;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SubmitQuizServlet")
public class SubmitQuizServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/cyber_guardian", "root", "");

            int score = 0, totalQuestions = 0;

            int tutorialId = Integer.parseInt(request.getParameter("tutorialId"));
            
            String query = "SELECT id, correct_option FROM questions WHERE specialization_id = ?";
            ps = c1.prepareStatement(query);
            ps.setInt(1, tutorialId);
            rs = ps.executeQuery();
            
            Map<Integer, String> correctOptions = new HashMap<>();
            
            while (rs.next()) {
                correctOptions.put(rs.getInt("id"), rs.getString("correct_option"));
            }
            
            rs.close();
            ps.close();
            
            totalQuestions = correctOptions.size();
            
            for (Map.Entry<Integer, String> entry : correctOptions.entrySet()) {
                int questionId = entry.getKey();
                String correctAnswer = entry.getValue();
                
                String userAnswer = request.getParameter("question_" + questionId);
                
                if (userAnswer != null && userAnswer.equals(correctAnswer)) {
                    score++;
                }
            }
            
            HttpSession session = request.getSession();
            int userId = (Integer) session.getAttribute("id");

            String insertResult = "INSERT INTO quiz_result (user_id, tutorial_id, score, attempted_date) VALUES (?, ?, ?, ?, NOW())";
            ps = c1.prepareStatement(insertResult);
            ps.setInt(1, userId);
            ps.setInt(2, tutorialId);
            ps.setInt(3, score);
            ps.executeUpdate();
            
            request.setAttribute("score", score);
            request.setAttribute("totalQuestions", totalQuestions);
            request.getRequestDispatcher("User/QuizResult.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ex) {}
            try { if (ps != null) ps.close(); } catch (Exception ex) {}
            try { if (c != null) c.close(); } catch (Exception ex) {}
        }
    }
}
