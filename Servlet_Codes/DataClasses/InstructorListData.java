package Servlet_Codes.DataClasses;

public class InstructorListData {
	
	private int id, specialization_id, year_of_experience;
	private String user_name, user_email, user_phone, field, gender, address, birth_date;

	public InstructorListData() {}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSpecializationId() {
		return specialization_id;
	}
	public void setSpecializationId(int specialization_id) {
		this.specialization_id = specialization_id;
	}
	public int getYear_Of_Experience() {
		return year_of_experience;
	}
	public void setYear_Of_Experience(int year_of_experience) {
		this.year_of_experience = year_of_experience;
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
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirthDate() {
		return birth_date;
	}
	public void setBirthDate(String birth_date) {
		this.birth_date = birth_date;
	}

	public void setTotal_instructors(int i) {
		
	}
}