package Servlet_Codes.DataClasses;

public class QuizzesData {
	private int id, instructor_id, tutorial_id, question_id, user_id, score, duration, total_questions;
	private String question, options_a, options_b, options_c, options_d, correct_option, selected_option, attempted_date;
	private String instructor_name, title, description;
	
	public QuizzesData() {}

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
		return instructor_name;
	}
	public void setInstructorName(String instructor_name) {
		this.instructor_name = instructor_name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getTotalQuestions() {
		return total_questions;
	}
	public void setTotalQuestions(int total_questions) {
		this.total_questions = total_questions;
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
	public int getUserId() {
		return user_id;
	}
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	public int getTutorialId() {
		return tutorial_id;
	}
	public void setTutorialId(int tutorial_id) {
		this.tutorial_id = tutorial_id;
	}
	public int getQuestionId() {
		return question_id;
	}
	public void setQuestionId(int question_id) {
		this.question_id = question_id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOptionA() {
		return options_a;
	}
	public void setOptionA(String options_a) {
		this.options_a = options_a;
	}
	public String getOptionB() {
		return options_b;
	}
	public void setOptionB(String options_b) {
		this.options_b = options_b;
	}
	public String getOptionC() {
		return options_c;
	}
	public void setOptionC(String options_c) {
		this.options_c = options_c;
	}
	public String getOptionD() {
		return options_d;
	}
	public void setOptionD(String options_d) {
		this.options_d = options_d;
	}
	public String getCorrectOption() {
		return correct_option;
	}
	public void setCorrectOption(String correct_option) {
		this.correct_option = correct_option;
	}
	public String getSelectedOption() {
		return selected_option;
	}
	public void setSelectOption(String selected_option) {
		this.selected_option = selected_option;
	}
	public String getAttemptedDate() {
		return attempted_date;
	}
	public void setAttemptedDate(String attempted_date) {
		this.attempted_date = attempted_date;
	}
	public void getAllAvailableExams() {}
}