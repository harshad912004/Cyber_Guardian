package Servlet_Codes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddNewInstructorServlet")
public class AddNewInstructorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int account_type_id = 0;
	private int new_record_id = 0;
    
    public AddNewInstructorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String susername = request.getParameter("instructor_name");
		String semail = request.getParameter("email");
		String sphone = request.getParameter("phone");
		String spassword = request.getParameter("password");
		String sgender = request.getParameter("gender");
		int sspecialization = Integer.parseInt(request.getParameter("specialization"));
		String saddress = request.getParameter("address");
		String sdob = request.getParameter("dob");
		String syear_of_experience = request.getParameter("year_of_experience");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cyber_guardian", "root", "");
			Statement st1 = con.createStatement();

			String query1 = "select id from account_type where type = 'Instructor'";
			ResultSet rs1 = st1.executeQuery(query1);
			
			if (rs1.next()) {
				account_type_id = rs1.getInt("id");
				String query2 = "INSERT INTO user (user_name, user_email, user_phone, user_password, gender, account_type) VALUES ('" + susername + "', '" + semail + "', '" + sphone + "', '" + spassword + "', '" + sgender + "', '" + account_type_id + "')";
				int affectedRows = st1.executeUpdate(query2, Statement.RETURN_GENERATED_KEYS);
				
				if (affectedRows > 0) {
					try (ResultSet rs2 = st1.getGeneratedKeys()) {
						if (rs2.next()) {
							new_record_id = rs2.getInt(1);
							String query3 = "INSERT INTO instructors (user_id, specialization_id, year_of_experience, address, birth_date) VALUES ('" + new_record_id + "', '" + sspecialization + "', '" + syear_of_experience + "', '" + saddress + "', '" + sdob + "')";
							int affectedRows2 = st1.executeUpdate(query3, Statement.RETURN_GENERATED_KEYS);

							if (affectedRows2 > 0) {
								try (ResultSet rs3 = st1.getGeneratedKeys()) {
									if (rs3.next()) {
										request.setAttribute("successMessage", "New record added successfully!");
										RequestDispatcher disp1 = request.getRequestDispatcher("/AdminServlet?action=instructors_list");
										disp1.forward(request, response);
									}
								}
							}
							else {
								String query4 = "DELETE From user where id=" + new_record_id;
								st1.executeUpdate(query4);

								List<String> errors = new ArrayList<>();
								errors.add("Unable to add new record");
								request.setAttribute("errors", errors);
								RequestDispatcher disp2 = request.getRequestDispatcher("/AdminServlet?action=add_new_instructor");
								disp2.forward(request, response);
							}
						}
					}
				}
				else {
					System.out.println("Insertion failed, no rows affected.");
					List<String> errors = new ArrayList<>();
					errors.add("Unable to add new record");
					request.setAttribute("errors", errors);
					RequestDispatcher disp4 = request.getRequestDispatcher("/AdminServlet?action=add_new_instructor");
					disp4.forward(request, response);
				}
			}
			else {
				System.out.println("Insertion failed, no rows affected.");
				List<String> errors = new ArrayList<>();
				errors.add("Unable to add new record");
				request.setAttribute("errors", errors);
				RequestDispatcher disp4 = request.getRequestDispatcher("/AdminServlet?action=add_new_instructor");
				disp4.forward(request, response);
			}
			con.close();
		}
		catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Error: " + e.getMessage());

			request.getSession().invalidate();
			List<String> errors = new ArrayList<>();
			errors.add(e.getMessage());
			request.setAttribute("errors", errors);
			RequestDispatcher disp5 = request.getRequestDispatcher("/AdminServlet?action=add_new_instructor");
			disp5.forward(request, response);
		}
	}
}