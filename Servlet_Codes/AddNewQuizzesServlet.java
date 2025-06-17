package Servlet_Codes;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/AddNewQuizzesServlet")
public class AddNewQuizzesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AddNewQuizzesServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer instructorId = (Integer) session.getAttribute("instructor_id");

        if (instructorId == null) {
            response.sendRedirect("jsp/LoginPage.jsp");
            return;
        }

        String specializationIdStr = request.getParameter("specialization");
        if (specializationIdStr == null || specializationIdStr.isEmpty()) {
            response.sendRedirect("InstructorDashboard.jsp?error=SpecializationNotSelected");
            return;
        }
        int specializationId = Integer.parseInt(specializationIdStr);

        String question = request.getParameter("question");
        String optionA = request.getParameter("option_a");
        String optionB = request.getParameter("option_b");
        String optionC = request.getParameter("option_c");
        String optionD = request.getParameter("option_d");
        String correctOption = request.getParameter("correct_option");

        if (question == null || question.trim().isEmpty() || correctOption == null || correctOption.trim().isEmpty()) {
            response.sendRedirect("add_new_quiz.jsp?error=MissingQuestionOrAnswer");
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cyber_guardian", "root", "");
			
            String sql = "INSERT INTO questions (specialization_id, instructor_id, question, option_a, option_b, option_c, option_d, correct_option) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = c.prepareStatement(sql);

            pstmt.setInt(1, specializationId);
            pstmt.setInt(2, instructorId);
            pstmt.setString(3, question);
            pstmt.setString(4, optionA);
            pstmt.setString(5, optionB);
            pstmt.setString(6, optionC);
            pstmt.setString(7, optionD);
            pstmt.setString(8, correctOption);

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                response.sendRedirect("Instructor/AddNewQuizzes.jsp?success=QuestionAdded");
            } else {
                response.sendRedirect("Instructor/AddNewQuizzes.jsp?error=InsertFailed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("Instructor/InstructorDashboard.jsp?error=DatabaseError");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Instructor/InstructorDashboard.jsp?error=UnknownError");
        } finally {
            try {
                if (pstmt != null)
                	pstmt.close();
                if (conn != null)
                	conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}