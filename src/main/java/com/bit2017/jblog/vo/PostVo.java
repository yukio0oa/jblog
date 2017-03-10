package com.bit2017.jblog.vo;

public class PostVo {
	private Long pno;
	private String ptitle;
	private String content;
	private String regDate;
	private Long cno;
	
	public Long getPno() {
		return pno;
	}
	public void setPno(Long pno) {
		this.pno = pno;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Long getCno() {
		return cno;
	}
	public void setCno(Long cno) {
		this.cno = cno;
	}
	
	@Override
	public String toString() {
		return "PostVo [pno=" + pno + ", ptitle=" + ptitle + ", content=" + content + ", regDate=" + regDate + ", cno="
				+ cno + "]";
	}
	
}
