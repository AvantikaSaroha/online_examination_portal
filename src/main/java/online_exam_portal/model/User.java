package online_exam_portal.model;

public class User{
	private int userid;
	private String username;
	private String u_email;
	
	
	public User(int userid, String username, String u_email) {
		super();
		this.userid = userid;
		this.username = username;
		this.u_email = u_email;
	}
	
	
	public User(String username, String u_email) {
		super();
		this.username = username;
		this.u_email = u_email;
	}


	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	
	

}
