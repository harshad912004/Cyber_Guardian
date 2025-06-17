package Servlet_Codes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getParameter("action");
		if("loginForm".equals(action)) {
			RequestDispatcher disp1 = request.getRequestDispatcher("jsp/LoginPage.jsp");
			disp1.forward(request, response);
		}
		else if("login".equals(action)) {
			String semail = request.getParameter("email");
			String spassword = request.getParameter("password");
//			System.out.println(semail + "\n" + spassword);
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
//				System.out.println("Driver Loaded");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cyber_guardian", "root", "");
//				System.out.println("Connection Established");
				Statement st = con.createStatement();
				
				String query = "select * from user where user_email = '" + semail + "' and user_password = '" + spassword + "' ";
				ResultSet rs = st.executeQuery(query);
				
				if(rs.next()) {
					HttpSession se = request.getSession();
					se.setAttribute("id", rs.getInt("id"));
					se.setAttribute("name", rs.getString("user_name"));
					se.setAttribute("email", rs.getString("user_email"));
					se.setAttribute("phone", rs.getString("user_phone"));
					se.setAttribute("password", rs.getString("user_password"));
					se.setAttribute("gender", rs.getString("gender"));

					if (rs.getInt("account_type") == 1) {
						request.setAttribute("successMessage", "Logged in successfully!");
						RequestDispatcher disp2 = request.getRequestDispatcher("/AdminServlet?action=dashboard");
						disp2.forward(request, response);
					}
					else if (rs.getInt("account_type") == 2) {
						request.setAttribute("successMessage", "Logged in successfully!");
						HttpSession se1 = request.getSession();
					    se1.setAttribute("instructor_id", rs.getInt("id"));
					    RequestDispatcher disp3 = request.getRequestDispatcher("/InstructorServlet?action=dashboard");
					    disp3.forward(request, response);
					}
					else if (rs.getInt("account_type") == 3) {
						request.setAttribute("successMessage", "Logged in successfully!");
						HttpSession se2 = request.getSession();
					    se2.setAttribute("user_id", rs.getInt("id"));
						RequestDispatcher disp4 = request.getRequestDispatcher("/UserServlet?action=dashboard");
						disp4.forward(request, response);
					}
				}
				else {
					//error message
					List<String> errors = new ArrayList<>();
					errors.add("Wrong Credentials");
					request.setAttribute("errors", errors);
					RequestDispatcher disp5 = request.getRequestDispatcher("/LoginServlet?action=loginForm");
					disp5.forward(request, response);
				}
				con.close();
			}
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				System.out.println("Errors: " + e.getMessage());
				
				List<String> errors = new ArrayList<>();
				errors.add("Something went wrong...Try again or Try after some time");
				request.setAttribute("errors", errors);
				RequestDispatcher disp6 = request.getRequestDispatcher("/LoginServlet?action=loginForm");
				disp6.forward(request, response);
			}
		}
		else if ("user_register".equals(action)) {
			RequestDispatcher disp7 = request.getRequestDispatcher("jsp/RegistrationPage.jsp");
			disp7.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}