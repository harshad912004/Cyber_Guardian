package Servlet_Codes;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UserRegistrationServlet")
public class UserRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserRegistrationServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String susername = request.getParameter("username");
		String semail = request.getParameter("email");
		String sphone = request.getParameter("phone");
		String spassword = request.getParameter("password");
		String sgender = request.getParameter("gender");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cyber_guardian", "root", "");
			Statement s1 = con.createStatement();

			String query1 = "select id from account_type where type = 'User'";
			ResultSet rs1 = s1.executeQuery(query1);
			
			if(rs1.next()) {
				int account_type_id = rs1.getInt("id");
				
				String query2 = "INSERT INTO user (user_name, user_email, user_phone, user_password, gender, account_type) VALUES ('" + susername + "', '" + semail + "', '" + sphone + "', '" + spassword + "', '" + sgender + "', '" + account_type_id + "')";
				int affectedRows = s1.executeUpdate(query2, Statement.RETURN_GENERATED_KEYS);
				
				if (affectedRows > 0) {
					try (ResultSet rs2 = s1.getGeneratedKeys()) {
						if (rs2.next()) {
							request.setAttribute("successMessage", "Registered successfully!");
							RequestDispatcher disp1 = request.getRequestDispatcher("/LoginServlet?action=loginForm");
							disp1.forward(request, response);
						}
						else {
							System.out.println("Insertion failed, no rows affected.");
							List<String> errors = new ArrayList<>();
							errors.add("Unable to add new record");
							request.setAttribute("errors", errors);
							RequestDispatcher disp3 = request.getRequestDispatcher("/LoginServlet?action=user_register");
							disp3.forward(request, response);
						}
					}
				}
				else {
					List<String> errors = new ArrayList<>();
					errors.add("Unable to register");
					request.setAttribute("errors", errors);
					RequestDispatcher disp3 = request.getRequestDispatcher("/LoginServlet?action=user_register");
					disp3.forward(request, response);
				}
			}
			else {
				List<String> errors = new ArrayList<>();
				errors.add("Unable to register");
				request.setAttribute("errors", errors);
				RequestDispatcher disp4 = request.getRequestDispatcher("/LoginServlet?action=user_register");
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
			RequestDispatcher disp5 = request.getRequestDispatcher("/LoginServlet?action=user_register");
			disp5.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}