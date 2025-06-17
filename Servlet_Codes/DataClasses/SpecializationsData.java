package Servlet_Codes.DataClasses;

public class SpecializationsData {
	
	private int id;
	private String field;

	public SpecializationsData() {}
	
	public SpecializationsData(int id, String field) {
		this.id = id;
		this.field = field;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
}