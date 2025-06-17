package Servlet_Codes;

import java.io.IOException;
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

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UserServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		if (session != null) {
			String user_name = (String) session.getAttribute("name");
			int user_id = (int) session.getAttribute("id");
			String user_password = ((String) session.getAttribute("password")).trim();
	        
			if (user_name != null && user_id != 0 && user_password != null) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cyber_guardian", "root", "");

					if ("dashboard".equals(action)) {
						int totalTutorials = 0, totalMyTutorials = 0, totalMyCertificates = 0, totalAttemptedQuizzes = 0;
						
						String query1 = "SELECT COUNT(*) AS total_records FROM tutorials";
						PreparedStatement s1 = c.prepareStatement(query1);
						ResultSet rs1 = s1.executeQuery();
						if (rs1.next()) {
							totalTutorials = rs1.getInt("total_records");
						}
						
						String query2 = "SELECT COUNT(DISTINCT tutorial_id) AS total_records FROM tutorials_progress where user_id = ?";
						PreparedStatement s2 = c.prepareStatement(query2);
						s2.setInt(1, user_id);
						ResultSet rs2 = s2.executeQuery();
						if (rs2.next()) {
							totalMyTutorials = rs2.getInt("total_records");
						}
						
						String query3 = "SELECT COUNT(*) AS total_records FROM certificates WHERE user_id = ?";
						PreparedStatement s3 = c.prepareStatement(query3);
						s3.setInt(1, user_id);
						ResultSet rs3 = s3.executeQuery();
						if (rs3.next()) {
							totalMyCertificates = rs3.getInt("total_records");
						}
						
						String query4 = "SELECT COUNT(DISTINCT question_id) AS total_records FROM quiz_result WHERE user_id = ?";
						PreparedStatement s4 = c.prepareStatement(query4);
						s4.setInt(1, user_id);
						ResultSet rs4 = s4.executeQuery();
						if (rs4.next()) {
							totalAttemptedQuizzes = rs4.getInt("total_records");
						}
						
						request.setAttribute("totalTutorials", totalTutorials);
						request.setAttribute("totalMyTutorials", totalMyTutorials);
						request.setAttribute("totalMyCertificates", totalMyCertificates);
						request.setAttribute("totalAttemptedQuizzes", totalAttemptedQuizzes);

						RequestDispatcher disp = request.getRequestDispatcher("User/UserDashboard.jsp");
						disp.forward(request, response);
					}
					else if ("total_tutorials".equals(action)) {
					    List<TutorialsData> dataList = new ArrayList<>();

					    String query8 = "SELECT tutorials.*, user.user_name AS instructor_name FROM tutorials INNER JOIN user ON tutorials.instructor_id = user.id"; 
					    
					    PreparedStatement s8 = c.prepareStatement(query8);
					    ResultSet rs8 = s8.executeQuery();

					    while (rs8.next()) {
					        TutorialsData data = new TutorialsData();
					        data.setId(rs8.getInt("id"));
					        data.setTitle(rs8.getString("title"));
					        data.setDescription(rs8.getString("description"));
					        data.setType(rs8.getString("file_type"));
					        data.setFilePath(rs8.getString("file_path"));
					        data.setInstructorName(rs8.getString("instructor_name"));
					        dataList.add(data);
					    }
					    
					    rs8.close();
					    s8.close();

					    request.setAttribute("dataList", dataList);
					    request.getRequestDispatcher("User/TutorialsList.jsp").forward(request, response);
					}
					else if ("my_tutorials".equals(action)) {
					    List<TutorialsData> myTutorials = new ArrayList<>();

					    String query = "SELECT t.id, t.title, t.description, t.file_path, user_name AS instructor_name FROM tutorials t "
					    		+ "JOIN tutorials_progress p ON t.id = p.tutorial_id "
					    		+ "JOIN user u ON t.instructor_id = u.id WHERE p.user_id = ?";
					    		
					    PreparedStatement ps = c.prepareStatement(query);
					    ps.setInt(1, user_id);
					    ResultSet rs = ps.executeQuery();

					    while (rs.next()) {
					        TutorialsData data = new TutorialsData();
					        data.setId(rs.getInt("id"));
					        data.setTitle(rs.getString("title"));
					        data.setDescription(rs.getString("description"));
					        data.setFilePath(rs.getString("file_path"));
					        data.setInstructorName(rs.getString("instructor_name"));
					        myTutorials.add(data);
					    }
					    rs.close();
					    ps.close();

					    request.setAttribute("dataList", myTutorials);
					    request.getRequestDispatcher("User/MyTutorialsList.jsp").forward(request, response);
					}
					else if ("my_certificates".equals(action)) {
						List<CertificateData> dataList = new ArrayList<>();

						String query5 = "SELECT id, tutorial_id, user_id, issue_date from certificates WHERE user_id = ?";
						PreparedStatement s5 = c.prepareStatement(query5);
					    s5.setInt(1, user_id);
					    ResultSet rs5 = s5.executeQuery();

						while (rs5.next()) {
							CertificateData data = new CertificateData();
							data.setId(rs5.getInt("id"));
							data.setTutorialId(rs5.getInt("tutorial_id"));
							data.setUserId(rs5.getInt("user_id"));
							data.setIssueDate(rs5.getString("issue_date"));
							dataList.add(data);
						}
						rs5.close();
						s5.close();

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("User/CertificatesList.jsp").forward(request, response);
					}
					else if ("attempt_quiz".equals(action)) {
					    String tutorialIdStr = request.getParameter("tutorialId");
					    int tutorialId = Integer.parseInt(tutorialIdStr);

					    List<QuizzesData> quizList = new ArrayList<>();

					    String query = "SELECT id, question, option_a, option_b, option_c, option_d, correct_option FROM questions WHERE apecialization_id = ?";
					    PreparedStatement ps = c.prepareStatement(query);
					    ps.setInt(1, tutorialId);
					    ResultSet rs = ps.executeQuery();

					    while (rs.next()) {
					    	QuizzesData q = new QuizzesData();
					        q.setId(rs.getInt("id"));
					        q.setQuestion(rs.getString("question"));
					        q.setOptionA(rs.getString("option_a"));
					        q.setOptionB(rs.getString("option_b"));
					        q.setOptionC(rs.getString("option_c"));
					        q.setOptionD(rs.getString("option_d"));
					        q.setCorrectOption(rs.getString("correct_option"));
					        quizList.add(q);
					    }

					    rs.close();
					    ps.close();

					    request.setAttribute("quizList", quizList);
					    request.setAttribute("tutorialId", tutorialId);

					    request.getRequestDispatcher("User/AttemptQuiz.jsp").forward(request, response);
					}
					else if ("my_attempted_quizzes".equals(action)) {
						List<QuizzesData> quizList = new ArrayList<>();

					    String query = "SELECT * FROM quiz_result WHERE user_id = ?";
					    PreparedStatement ps = c.prepareStatement(query);
					    ps.setInt(1, user_id);
					    ResultSet rs = ps.executeQuery();

					    while (rs.next()) {
					        QuizzesData data = new QuizzesData();
					        data.setId(rs.getInt("id"));
					        data.setUserId(rs.getInt("user_id"));
					        data.setScore(rs.getInt("score"));
					        data.setAttemptedDate(rs.getString("attempted_date"));
					        quizList.add(data);
					    }
					    rs.close();
					    ps.close();

					    request.setAttribute("dataList", quizList);
					    request.getRequestDispatcher("User/MyQuizzesList.jsp").forward(request, response);
					}
					else if ("password_generator".equals(action)) {
						request.getRequestDispatcher("User/PasswordGenerator.jsp").forward(request, response);
					}
					else if ("password_checker".equals(action)) {
						request.getRequestDispatcher("User/PasswordChecker.jsp").forward(request, response);
					}
					else if ("update_profile".equals(action)) {
						Statement st2 = c.createStatement();
						ResultSet rs10 = st2.executeQuery("SELECT * FROM user where id=" + user_id);

						LoggedInUserData data = new LoggedInUserData();
						while (rs10.next()) {
							data.setId(rs10.getInt("id"));
							data.setName(rs10.getString("user_name"));
							data.setEmailID(rs10.getString("user_email"));
							data.setPhone_number(rs10.getString("user_phone"));
						}

						request.setAttribute("user_data", data);
						request.getRequestDispatcher("User/UpdateProfile.jsp").forward(request, response);
					}
					else if ("change_password".equals(action)) {
						RequestDispatcher disp2 = request.getRequestDispatcher("./User/ChangePassword.jsp");
						disp2.forward(request, response);
					}
					else if ("save_new_password".equals(action)) {
						System.out.println("password " + user_password);
						String old_password = request.getParameter("old_password").trim();
						System.out.println("old_password " + old_password);
						String new_password = request.getParameter("new_password").trim();
						String cnew_password = request.getParameter("cnew_password").trim();

						List<String> errors = new ArrayList<>();
						if (!old_password.equals(user_password)) {
							errors.add("Old password is wrong");
						}
						if (!errors.isEmpty()) {
							request.setAttribute("errors", errors);
							RequestDispatcher disp3 = request.getRequestDispatcher("/UserServlet?action=change_password");
							disp3.forward(request, response);
							return;
						}
						String query9 = "UPDATE user SET user_password = ? WHERE id = " + user_id;
						PreparedStatement s9 = c.prepareStatement(query9);
						s9.setString(1, request.getParameter("new_password"));
						int rowsAffected = s9.executeUpdate();

						if (rowsAffected > 0) {
							session.setAttribute("user_password", new_password);
							System.out.println("new updated password " + (String) session.getAttribute("user_password"));
							System.out.println("Update successful! " + rowsAffected + " rows affected.");
							request.setAttribute("successMessage", "Password changed successfully!");
							RequestDispatcher disp4 = request.getRequestDispatcher("/UserServlet?action=dashboard");
							disp4.forward(request, response);
						}
						else {
							errors.add("Unable to change password");
							request.setAttribute("errors", errors);
							RequestDispatcher disp5 = request.getRequestDispatcher("/UserServlet?action=change_password");
							disp5.forward(request, response);
						}
					}
					else if ("save_profile".equals(action)) {
						List<String> errors = new ArrayList<>();

						String query10 = "UPDATE user SET name = ?, email_id = ?, phone_number = ? WHERE id = " + user_id;
						PreparedStatement s10 = c.prepareStatement(query10);
						s10.setString(1, request.getParameter("user_name"));
						s10.setString(2, request.getParameter("user_email"));
						s10.setString(3, request.getParameter("user_phone"));
						int rowsAffected = s10.executeUpdate();

						if (rowsAffected > 0) {
							request.setAttribute("successMessage", "Profile updated successfully!");
							RequestDispatcher disp6 = request.getRequestDispatcher("/UserServlet?action=dashboard");
							disp6.forward(request, response);
						}
						else {
							errors.add("Unable to update profile");
							request.setAttribute("errors", errors);
							RequestDispatcher disp7 = request.getRequestDispatcher("/UserServlet?action=update_profile");
							disp7.forward(request, response);
						}
					}
				}
				catch (ClassNotFoundException | SQLException e) {
					// e.printStackTrace();
					System.out.println("Error: " + e.getMessage());

					request.getSession().invalidate();
					List<String> errors = new ArrayList<>();
					errors.add(e.getMessage());
					request.setAttribute("errors", errors);
					RequestDispatcher disp8 = request.getRequestDispatcher("/InstructorServlet?action=dashboard");
					disp8.forward(request, response);
				}
			}
			else {
				request.getSession().invalidate();
				List<String> errors = new ArrayList<>();
				errors.add("You have logged out!!!");
				request.setAttribute("errors", errors);
				RequestDispatcher disp9 = request.getRequestDispatcher("/LoginServlet?action=loginForm");
				disp9.forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}