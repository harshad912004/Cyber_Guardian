package Servlet_Codes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Servlet_Codes.DataClasses.CertificateData;
import Servlet_Codes.DataClasses.InstructorListData;
import Servlet_Codes.DataClasses.LoggedInUserData;
import Servlet_Codes.DataClasses.TutorialsData;
import Servlet_Codes.DataClasses.UserListData;
import Servlet_Codes.DataClasses.QuizzesData;
import Servlet_Codes.DataClasses.SpecializationsData;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/InstructorServlet")
public class InstructorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InstructorServlet() {
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
						int totalQuestions = 0, totalUsers = 0, totalTutorials = 0, totalCertificates = 0;

						String query1 = "SELECT COUNT(*) AS total_records FROM questions";
						PreparedStatement s1 = c.prepareStatement(query1);
						ResultSet rs1 = s1.executeQuery();
						if (rs1.next()) {
							totalQuestions = rs1.getInt("total_records");
						}

						HttpSession se1 = request.getSession();
						Integer instructorId1 = (Integer) se1.getAttribute("instructor_id");

						if (instructorId1 != null) {
						    String query3 = "SELECT COUNT(DISTINCT user_id) AS total_records FROM certificates WHERE instructor_id = ?";
						    PreparedStatement s3 = c.prepareStatement(query3);
						    s3.setInt(1, instructorId1);
						    ResultSet rs3 = s3.executeQuery();
						    
						    if (rs3.next()) {
						        totalUsers = rs3.getInt("total_records");
						    }
						}
						
						HttpSession se2 = request.getSession();
						Integer instructorId2 = (Integer) se2.getAttribute("instructor_id");

						if (instructorId2 != null) {
						    String query3 = "SELECT COUNT(*) AS total_records FROM tutorials WHERE instructor_id = ?";
						    PreparedStatement s3 = c.prepareStatement(query3);
						    s3.setInt(1, instructorId2);
						    ResultSet rs3 = s3.executeQuery();
						    
						    if (rs3.next()) {
						        totalTutorials = rs3.getInt("total_records");
						    }
						}


						HttpSession se3 = request.getSession();
						Integer instructorId3 = (Integer) se3.getAttribute("instructor_id");

						if (instructorId3 != null) {
						    String query4 = "SELECT COUNT(*) AS total_records FROM certificates WHERE instructor_id = ?";
						    PreparedStatement s4 = c.prepareStatement(query4);
						    s4.setInt(1, instructorId3);
						    ResultSet rs4 = s4.executeQuery();
						    
						    if (rs4.next()) {
						        totalCertificates = rs4.getInt("total_records");
						    }
						}


						request.setAttribute("totalQuestions", totalQuestions);
						request.setAttribute("totalUsers", totalUsers);
						request.setAttribute("totalTutorials", totalTutorials);
						request.setAttribute("totalCertificates", totalCertificates);

						RequestDispatcher disp = request.getRequestDispatcher("Instructor/InstructorDashboard.jsp");
						disp.forward(request, response);
					}
					else if ("tutorials".equals(action)) {
					    List<TutorialsData> dataList = new ArrayList<>();

					    HttpSession se = request.getSession();
					    Integer instructorId = (Integer) se.getAttribute("instructor_id");

					    if (instructorId != null) {
					        String query8 = "SELECT * FROM tutorials WHERE instructor_id = ?";
					        PreparedStatement s8 = c.prepareStatement(query8);
					        s8.setInt(1, instructorId);
					        ResultSet rs8 = s8.executeQuery();

					        while (rs8.next()) {
					            TutorialsData data = new TutorialsData();
					            data.setId(rs8.getInt("id"));
					            data.setTitle(rs8.getString("title"));
					            data.setDescription(rs8.getString("description"));
					            data.setType(rs8.getString("file_type"));
					            data.setFilePath(rs8.getString("file_path"));
					            data.setInstructorId(rs8.getInt("instructor_id"));
					            dataList.add(data);
					        }
					    }

					    request.setAttribute("dataList", dataList);
					    request.getRequestDispatcher("Instructor/TutorialsList.jsp").forward(request, response);
					}
					else if ("add_new_tutorials".equals(action)) {
						List<TutorialsData> dataList = new ArrayList<>();

						Statement st1 = c.createStatement();
						ResultSet rs9 = st1.executeQuery("SELECT * from tutorials");

						while (rs9.next()) {
							TutorialsData data = new TutorialsData();
							data.setId(rs9.getInt("id"));
							data.setTitle(rs9.getString("title"));
							data.setDescription(rs9.getString("description"));
							data.setType(rs9.getString("file_type"));
							data.setFilePath(rs9.getString("file_path"));
							dataList.add(data);
						}

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Instructor/AddNewTutorials.jsp").forward(request, response);
					}
					else if ("add_new_quizzes".equals(action)) {
						List<SpecializationsData> dataList = new ArrayList<>();

						Statement st1 = c.createStatement();
						ResultSet rs9 = st1.executeQuery("SELECT id, field FROM specialization");

						while (rs9.next()) {
							SpecializationsData data = new SpecializationsData();
							data.setId(rs9.getInt("id"));
							data.setField(rs9.getString("field"));
							dataList.add(data);
						}

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Instructor/AddNewQuizzes.jsp").forward(request, response);
					}
					else if ("certificates".equals(action))
					{
						List<CertificateData> dataList = new ArrayList<>();

						String query5 = "SELECT * from certificates";
						PreparedStatement s5 = c.prepareStatement(query5);
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
						request.getRequestDispatcher("Instructor/CertificatesList.jsp").forward(request, response);
					}
					else if ("users_list".equals(action)) {
					    List<UserListData> dataList = new ArrayList<>();

					    HttpSession se = request.getSession();
					    Integer instructorId = (Integer) se.getAttribute("instructor_id");

					    if (instructorId != null) {
					        String query7 = "SELECT * FROM user WHERE account_type = 3 AND id = ?";
					        PreparedStatement s7 = c.prepareStatement(query7);
					        s7.setInt(1, instructorId);
					        ResultSet rs7 = s7.executeQuery();

					        while (rs7.next()) {
					            UserListData data = new UserListData();
					            data.setId(rs7.getInt("id"));
					            data.setName(rs7.getString("user_name"));
					            data.setEmailID(rs7.getString("user_email"));
					            data.setPhone_number(rs7.getString("user_phone"));
					            data.setGender(rs7.getString("gender"));
					            dataList.add(data);
					        }
					    }

					    request.setAttribute("dataList", dataList);
					    request.getRequestDispatcher("Instructor/UserList.jsp").forward(request, response);
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
						request.getRequestDispatcher("Instructor/UpdateProfile.jsp").forward(request, response);
					}
					else if ("change_password".equals(action)) {
						RequestDispatcher disp2 = request.getRequestDispatcher("./Instructor/ChangePassword.jsp");
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
							RequestDispatcher disp3 = request.getRequestDispatcher("/InstructorServlet?action=change_password");
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
							RequestDispatcher disp4 = request.getRequestDispatcher("/InstructorServlet?action=dashboard");
							disp4.forward(request, response);
						}
						else {
							errors.add("Unable to change password");
							request.setAttribute("errors", errors);
							RequestDispatcher disp5 = request.getRequestDispatcher("/InstructorServlet?action=change_password");
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
							RequestDispatcher disp6 = request.getRequestDispatcher("/InstructorServlet?action=dashboard");
							disp6.forward(request, response);
						}
						else {
							errors.add("Unable to update profile");
							request.setAttribute("errors", errors);
							RequestDispatcher disp7 = request.getRequestDispatcher("/InstructorServlet?action=update_profile");
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