package Servlet_Codes.DataClasses;

public class LoggedInUserData {
	private int id, info_record_id;
	private String user_name, user_email, user_phone, gender;

	public LoggedInUserData() {}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return user_name;
	}
	public void setName(String name) {
		this.user_name = name;
	}
	public String getEmailID() {
		return user_email;
	}
	public void setEmailID(String email_id) {
		this.user_email = email_id;
	}
	public String getPhone_number() {
		return user_phone;
	}
	public void setPhone_number(String phone_number) {
		this.user_phone = phone_number;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getInfo_record_id() {
		return info_record_id;
	}
	public void setInfo_record_id(int info_record_id) {
		this.info_record_id = info_record_id;
	}
}