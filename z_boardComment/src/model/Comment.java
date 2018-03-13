package model;

import java.util.Date;

public class Comment {
	private int cId;
	private String comment;
	private String writer;
	private int mId;
	private String pwd;
	private int bId;
	private Date regDate;
	private int groupId;
	private int groupSeq;
	private int groupLv;
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getGroupSeq() {
		return groupSeq;
	}
	public void setGroupSeq(int groupSeq) {
		this.groupSeq = groupSeq;
	}
	public int getGroupLv() {
		return groupLv;
	}
	public void setGroupLv(int groupLv) {
		this.groupLv = groupLv;
	}
	@Override
	public String toString() {
		return "Comment [cId=" + cId + ", comment=" + comment + ", writer=" + writer + ", mId=" + mId + ", pwd=" + pwd
				+ ", bId=" + bId + ", regDate=" + regDate + ", groupId=" + groupId + ", groupSeq=" + groupSeq
				+ ", groupLv=" + groupLv + "]";
	}
	
	
	
}
