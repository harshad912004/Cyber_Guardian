package Servlet_Codes.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Servlet_Codes.DataClasses.TutorialsData;

public class TutorialsDAO {

    private Connection c;

    public TutorialsDAO() {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cyber_guardian", "root", "");
    	}
    	catch(ClassNotFoundException | SQLException e) {
    		System.out.println("Error: " + e.getMessage());
    	}
    }

    public TutorialsData getTutorialById(int tutorialId) {
        TutorialsData tutorial = null;
        try {
            String sql = "SELECT * FROM tutorials WHERE id = ?";
            PreparedStatement stmt = c.prepareStatement(sql);
            stmt.setInt(1, tutorialId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tutorial = new TutorialsData();
                tutorial.setId(rs.getInt("id"));
                tutorial.setTitle(rs.getString("title"));
                tutorial.setType(rs.getString("type"));
                tutorial.setInstructorName(rs.getString("instructor_name"));
                tutorial.setFilePath(rs.getString("file_path"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return tutorial;
    }
}