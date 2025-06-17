package Servlet_Codes.DataClasses;

public class CertificateData {
	
	private int id, tutorial_id, instructor_id, user_id;
	private String tutorial_name, instructor_name, user_name, issue_date;

	public CertificateData () {}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public int getTutorialId() {
		return tutorial_id;
	}
	public void setTutorialId(int tutorial_id) {
		this.tutorial_id = tutorial_id;
	}
	public String getTutorialName() {
		return tutorial_name;
	}
	public void setTutorialName(String tutorial_name) {
		this.tutorial_name = tutorial_name;
	}
	public int getInstructorId() {
		return instructor_id;
	}
	public void setInstructorId(int instructor_id) {
		this.instructor_id = instructor_id;
	}
	public String getInstructorName() {
		return instructor_name;
	}
	public void setInstructorName(String instructor_name) {
		this.instructor_name = instructor_name;
	}
	public String getUserName() {
		return user_name;
	}
	public void setUserName(String user_name) {
		this.user_name = user_name;
	}
	public int getUserId() {
		return user_id;
	}
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	public String getIssueDate() {
		return issue_date;
	}
	public void setIssueDate(String issue_date) {
		this.issue_date = issue_date;
	}
}