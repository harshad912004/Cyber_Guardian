package Servlet_Codes.DataClasses;

public class TutorialsData {
	
	private int id, instructor_id;
	private String title, file_path, file_type, description, instructor_Name, content;

	public TutorialsData() {}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInstructorId() {
		return instructor_id;
	}
	public void setInstructorId(int instructor_id) {
		this.instructor_id = instructor_id;
	}
	public String getInstructorName() {
		return instructor_Name;
	}
	public void setInstructorName(String instructor_Name) {
		this.instructor_Name = instructor_Name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return file_type;
	}
	public void setType(String type) {
		this.file_type = type;
	}
	public String getFilePath() {
		return file_path;
	}
	public void setFilePath(String url) {
		this.file_path = url;
	}
	public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}