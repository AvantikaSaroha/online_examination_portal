package online_exam_portal.model;

public class Result {

	private String username;
	private int totalmarks;
	private int marksobt;
	private String topicname;

	public Result(String username,int totalmarks, int marksobt) {
		super();
		this.username=username;
		this.totalmarks= totalmarks;
		this.marksobt = marksobt;
	}
	public Result(int totalmarks, int marksobt,String topicname) {
		super();
		this.totalmarks= totalmarks;
		this.marksobt = marksobt;
		this.topicname = topicname;
	}

	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	public String gettopicname() {
		return topicname;
	}
	public void settopicname(String topicname) {
		this.topicname = topicname;
	}
	

	public int gettotalmarks() {
		return totalmarks;
	}
	public void settotalmarks(int totalmarks) {
		this.totalmarks = totalmarks;
	}
	
	public int getmarksobt() {
		return marksobt;
	}
	public void setmarksobt(int marksobt) {
		this.marksobt = marksobt;
	}

	
}
