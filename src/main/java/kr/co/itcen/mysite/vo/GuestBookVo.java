package kr.co.itcen.mysite.vo;

public class GuestBookVo {
	private Long no;
	private String content;
	private String writer;
	private String password;
	private String registerdate;

	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
		return "GuestBookVo [no=" + no + ", content=" + content + ", writer=" + writer + ", password=" + password
				+ ", registerdate=" + registerdate + "]";
	}
	
}
