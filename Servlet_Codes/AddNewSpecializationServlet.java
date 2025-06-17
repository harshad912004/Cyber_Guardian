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

@WebServlet("/AddNewSpecializationServlet")
public class AddNewSpecializationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int new_record_id = 0;
	
    public AddNewSpecializationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		String sfield = request.getParameter("field_name");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cyber_guardian", "root", "");
			Statement st1 = con.createStatement();

			String query2 = "INSERT INTO specialization (field) VALUES ('" + sfield + "')";
			int affectedRows = st1.executeUpdate(query2, Statement.RETURN_GENERATED_KEYS);
				
			if (affectedRows > 0) {
				try (ResultSet rs2 = st1.getGeneratedKeys()) {
					if (rs2.next()) {
						request.setAttribute("successMessage", "New record added successfully!");
						RequestDispatcher disp1 = request.getRequestDispatcher("/AdminServlet?action=specializations");
						disp1.forward(request, response);
					}
					else {
						String query4 = "DELETE From specialization where id=" + new_record_id;
						st1.executeUpdate(query4);

						List<String> errors = new ArrayList<>();
						errors.add("Unable to add new record");
						request.setAttribute("errors", errors);
						RequestDispatcher disp2 = request.getRequestDispatcher("/AdminServlet?action=add_new_specialization");
						disp2.forward(request, response);
					}
				}
			}
			else {
				System.out.println("Insertion failed, no rows affected.");
				List<String> errors = new ArrayList<>();
				errors.add("Unable to add new record");
				request.setAttribute("errors", errors);
				RequestDispatcher disp4 = request.getRequestDispatcher("/AdminServlet?action=add_new_specialization");
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
			RequestDispatcher disp5 = request.getRequestDispatcher("/AdminServlet?action=add_new_specialization");
			disp5.forward(request, response);
		}
	}
}