package com.bit2017.jblog.vo;

public class CategoryVo {
	private Long cno;
	private String cname;
	private String description;
	private String regDate;
	private String blogId;
	
	public Long getCno() {
		return cno;
	}
	public void setCno(Long cno) {
		this.cno = cno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	
	@Override
	public String toString() {
		return "Category [cno=" + cno + ", cname=" + cname + ", description=" + description + ", regDate=" + regDate
				+ ", blogId=" + blogId + "]";
	}
	
}
