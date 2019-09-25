package kr.co.itcen.mysite.vo;

public class GuestBookVo {


	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegisterdate() {
		return registerdate;
	}
	public void setRegisterdate(String registerdate) {
		this.registerdate = registerdate;
	}
	@Override
	public String toString() {
		return "GuestBookVo [no=" + no + ", text=" + text + ", writer=" + writer + ", password=" + password
				+ ", registerdate=" + registerdate + "]";
	}
	private Long no;
	private String text;
	private String writer;
	private String password;
	private String registerdate;
}
