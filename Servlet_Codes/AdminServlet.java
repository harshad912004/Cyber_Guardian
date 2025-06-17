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
import Servlet_Codes.DataClasses.TutorialsData;
import Servlet_Codes.DataClasses.InstructorListData;
import Servlet_Codes.DataClasses.LoggedInUserData;
import Servlet_Codes.DataClasses.SpecializationsData;
import Servlet_Codes.DataClasses.UserListData;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public AdminServlet() {
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
						int totalInstructors = 0, totalUsers = 0, totalTutorials = 0, totalCertificates = 0, totalSpecializations=0;

						String query1 = "SELECT COUNT(*) AS total_records FROM user where account_type = 2";
						PreparedStatement s1 = c.prepareStatement(query1);
						ResultSet rs1 = s1.executeQuery();
						if (rs1.next()) {
							totalInstructors = rs1.getInt("total_records");
						}

						String query2 = "SELECT COUNT(*) AS total_records FROM user where account_type = 3";
						PreparedStatement s2 = c.prepareStatement(query2);
						ResultSet rs2 = s2.executeQuery();
						if (rs2.next()) {
							totalUsers = rs2.getInt("total_records");
						}

						String query3 = "SELECT COUNT(*) AS total_records FROM tutorials";
						PreparedStatement s3 = c.prepareStatement(query3);
						ResultSet rs3 = s3.executeQuery();
						if (rs3.next()) {
							totalTutorials = rs3.getInt("total_records");
						}

						String query4 = "SELECT COUNT(*) AS total_records FROM certificates";
						PreparedStatement s4 = c.prepareStatement(query4);
						ResultSet rs4 = s4.executeQuery();
						if (rs4.next()) {
							totalCertificates = rs4.getInt("total_records");
						}
						
						String query5 = "SELECT COUNT(*) AS total_records FROM specialization";
						PreparedStatement s5 = c.prepareStatement(query5);
						ResultSet rs5 = s5.executeQuery();
						if (rs5.next()) {
							totalSpecializations = rs5.getInt("total_records");
						}

						request.setAttribute("totalInstructors", totalInstructors);
						request.setAttribute("totalUsers", totalUsers);
						request.setAttribute("totalTutorials", totalTutorials);
						request.setAttribute("totalCertificates", totalCertificates);
						request.setAttribute("totalSpecializations", totalSpecializations);

						RequestDispatcher disp = request.getRequestDispatcher("Admin/AdminDashboard.jsp");
						disp.forward(request, response);
					}
					else if ("instructors_list".equals(action)) {
						List<InstructorListData> dataList = new ArrayList<>();

						String query6 = "SELECT user.id, user.user_name, user.user_email, user.user_phone, specialization.field, instructors.year_of_experience, instructors.address, instructors.birth_date, user.gender FROM instructors "
								+ "JOIN user ON instructors.user_id = user.id "
								+ "JOIN specialization ON instructors.specialization_id = specialization.id";
						PreparedStatement s6 = c.prepareStatement(query6);
						ResultSet rs6 = s6.executeQuery();

						while (rs6.next()) {
							InstructorListData data = new InstructorListData();
							data.setId(rs6.getInt("id"));
							data.setName(rs6.getString("user_name"));
							data.setEmailID(rs6.getString("user_email"));
							data.setPhone_number(rs6.getString("user_phone"));
							data.setField(rs6.getString("field"));
							data.setYear_Of_Experience(rs6.getInt("year_of_experience"));
							data.setBirthDate(rs6.getString("birth_date"));
							data.setAddress(rs6.getString("address"));
							data.setGender(rs6.getString("gender"));
							dataList.add(data);
						}

						request.setAttribute("dataList", dataList);
						RequestDispatcher disp1 = request.getRequestDispatcher("Admin/InstructorList.jsp");
						disp1.forward(request, response);
					}
					else if ("add_new_instructor".equals(action)) {
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
						request.getRequestDispatcher("Admin/AddNewInstructor.jsp").forward(request, response);
					}
//					else if ("delete_instructor".equals(action)) {
//						List<InstructorListData> dataList = new ArrayList<>();
//
//						Statement st1 = c.createStatement();
//						ResultSet rs9 = st1.executeQuery("SELECT id, user_name FROM user");
//
//						while (rs9.next()) {
//							InstructorListData data = new InstructorListData();
//							data.setId(rs9.getInt("id"));
//							data.setName(rs9.getString("user_name"));
//							data.setTotal_instructors(0);
//							dataList.add(data);
//						}
//
//						request.setAttribute("dataList", dataList);
//						request.getRequestDispatcher("Admin/DeleteNewInstructor.jsp").forward(request, response);
//					}
					else if ("users_list".equals(action)) {
						List<UserListData> dataList = new ArrayList<>();

						String query7 = "SELECT * FROM user WHERE user.account_type = 3";
						PreparedStatement s7 = c.prepareStatement(query7);
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

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Admin/UserList.jsp").forward(request, response);
					}
					else if ("tutorials".equals(action)) {
						List<TutorialsData> dataList = new ArrayList<>();

					    String query8 = "SELECT tutorials.*, user.user_name AS instructor_name FROM tutorials JOIN user ON tutorials.instructor_id = user.id"; 

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

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Admin/TutorialsList.jsp").forward(request, response);
					}
					else if ("view_tutorials".equals(action)) {
						List<TutorialsData> dataList = new ArrayList<>();

						String query8 = "SELECT tutorials.*, user.user_name AS instructor_name FROM tutorials JOIN user ON tutorials.instructor_id = user.id"; 

					    PreparedStatement s8 = c.prepareStatement(query8);
					    ResultSet rs8 = s8.executeQuery();

					    while (rs8.next()) {
					        TutorialsData data = new TutorialsData();
					        data.setId(rs8.getInt("id"));
					        data.setTitle(rs8.getString("title"));
					        data.setType(rs8.getString("file_type"));
					        data.setInstructorName(rs8.getString("instructor_name"));
					        dataList.add(data);
					    }

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Admin/ViewTutorials.jsp").forward(request, response);
					}
					else if ("certificates".equals(action))
					{
						List<CertificateData> dataList = new ArrayList<>();

						String query5 = "SELECT certificates.*, tutorials.title as title, user.user_name as instructor_name FROM certificates "
								+ "JOIN user ON certificates.instructor_id = user.id "
								+ "JOIN tutorials on certificates.tutorial_id = tutorials.id";
						PreparedStatement s5 = c.prepareStatement(query5);
						ResultSet rs5 = s5.executeQuery();

						while (rs5.next()) {
							CertificateData data = new CertificateData();
							data.setId(rs5.getInt("id"));
							data.setTutorialName(rs5.getString("title"));
							data.setInstructorName(rs5.getString("instructor_name"));
							data.setUserName(rs5.getString("user_name"));
							data.setIssueDate(rs5.getString("issue_date"));
							dataList.add(data);
						}
						rs5.close();
						s5.close();

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Admin/CertificatesList.jsp").forward(request, response);
					}
					else if ("specializations".equals(action)) {
						List<SpecializationsData> dataList = new ArrayList<>();

						String query9 = "SELECT * from specialization";
						PreparedStatement s8 = c.prepareStatement(query9);
						ResultSet rs9 = s8.executeQuery();

						while (rs9.next()) {
							SpecializationsData data = new SpecializationsData();
							data.setId(rs9.getInt("id"));
							data.setField(rs9.getString("field"));
							dataList.add(data);
						}

						request.setAttribute("dataList", dataList);
						request.getRequestDispatcher("Admin/SpecializationList.jsp").forward(request, response);
					}
					else if ("add_new_specialization".equals(action)) {
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
						request.getRequestDispatcher("Admin/AddNewSpecialization.jsp").forward(request, response);
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
						request.getRequestDispatcher("Admin/UpdateProfile.jsp").forward(request, response);
					}
					else if ("change_password".equals(action)) {
						RequestDispatcher disp2 = request.getRequestDispatcher("./Admin/ChangePassword.jsp");
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
							RequestDispatcher disp3 = request.getRequestDispatcher("/AdminServlet?action=change_password");
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
							RequestDispatcher disp4 = request.getRequestDispatcher("/AdminServlet?action=dashboard");
							disp4.forward(request, response);
						}
						else {
							errors.add("Unable to change password");
							request.setAttribute("errors", errors);
							RequestDispatcher disp5 = request.getRequestDispatcher("/AdminServlet?action=change_password");
							disp5.forward(request, response);
						}
					}
					else if ("save_profile".equals(action)) {
						List<String> errors = new ArrayList<>();

						String query10 = "UPDATE user SET user_name = ?, user_email = ?, user_phone = ? WHERE id = " + user_id;
						PreparedStatement s10 = c.prepareStatement(query10);
						s10.setString(1, request.getParameter("user_name"));
						s10.setString(2, request.getParameter("user_email"));
						s10.setString(3, request.getParameter("user_phone"));
						int rowsAffected = s10.executeUpdate();

						if (rowsAffected > 0) {
							request.setAttribute("successMessage", "Profile updated successfully!");
							RequestDispatcher disp6 = request.getRequestDispatcher("/AdminServlet?action=dashboard");
							disp6.forward(request, response);
						}
						else {
							errors.add("Unable to update profile");
							request.setAttribute("errors", errors);
							RequestDispatcher disp7 = request.getRequestDispatcher("/AdminServlet?action=update_profile");
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
					RequestDispatcher disp8 = request.getRequestDispatcher("/AdminServlet?action=dashboard");
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