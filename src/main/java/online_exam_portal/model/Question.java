package online_exam_portal.model;

public class Question {
	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String coption;
	
	public Question(String question, String option1,String option2,String option3,String option4,String coption) {
		super();
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.coption = coption;
	}

	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}

	public String getoption1() {
		return option1;
	}
	public void setoption1(String option) {
		this.option1 = option;
	}
	public String getoption2() {
		return option2;
	}
	public void setoption2(String option) {
		this.option2 = option;
	}
	public String getoption3() {
		return option3;
	}
	public void setoption3(String option) {
		this.option3 = option;
	}
	public String getoption4() {
		return option4;
	}
	public void setoption4(String option) {
		this.option4 = option;
	}
	public String getcoption() {
		return coption;
	}
	public void setcoption(String option) {
		this.coption = option;
	}
}
