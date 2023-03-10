package model;

public class UserAnswer {
	private String ans1;
	private String ans2;
	private String ans3;
	
	public UserAnswer() {
		
	}
	
	public UserAnswer(String ans1, String ans2, String ans3) {
		this.ans1 = ans1;
		this.ans2 = ans2;
		this.ans3 = ans3;
	}
	
	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}
	
	public String getAns1() {
		return ans1;
	}
	
	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}
	
	public String getAns2() {
		return ans2;
	}
	
	public void setAns3(String ans3) {
		this.ans3 = ans3;
	}
	
	public String getAns3() {
		return ans3;
	}
}
