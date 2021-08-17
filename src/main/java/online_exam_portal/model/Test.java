package online_exam_portal.model;

public class Test {
	private int topicid;
	private String topic;
	
	
	public Test(int topicid, String topic) {
		super();
		this.topicid= topicid;
		this.topic = topic;
	}
	public Test(String topic) {
		super();
		this.topic = topic;
	}

	public int gettopicid() {
		return topicid;
	}
	public void settopicid(int topicid) {
		this.topicid = topicid;
	}

	public String gettopic() {
		return topic;
	}
	public void settopic(String topic) {
		this.topic = topic;
	}
}
