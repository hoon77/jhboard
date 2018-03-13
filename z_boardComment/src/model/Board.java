package model;

import java.util.Date;

public class Board {
private int bId;
private String title;
private String content;
private String writer;
private int pwd;
private int mId;
private Date regDate;
private int hit;
private int up;
private int down;
private int groupId;
private int groupSeq;
private int groupLv;
private int fId;
private String fileuri;

public int getbId() {
	return bId;
}
public void setbId(int bId) {
	this.bId = bId;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
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

public int getPwd() {
	return pwd;
}
public void setPwd(int pwd) {
	this.pwd = pwd;
}
public Date getRegDate() {
	return regDate;
}
public void setRegDate(Date regDate) {
	this.regDate = regDate;
}
public int getHit() {
	return hit;
}
public void setHit(int hit) {
	this.hit = hit;
}
public int getUp() {
	return up;
}
public void setUp(int up) {
	this.up = up;
}
public int getDown() {
	return down;
}
public void setDown(int down) {
	this.down = down;
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
public int getfId() {
	return fId;
}
public void setfId(int fId) {
	this.fId = fId;
}


public int getmId() {
	return mId;
}
public void setmId(int mId) {
	this.mId = mId;
}
public String getFileuri() {
	return fileuri;
}
public void setFileuri(String fileuri) {
	this.fileuri = fileuri;
}
@Override
public String toString() {
	return "Board [bId=" + bId + ", title=" + title + ", content=" + content + ", writer=" + writer + ", pwd=" + pwd
			+ ", mId=" + mId + ", regDate=" + regDate + ", hit=" + hit + ", up=" + up + ", down=" + down + ", groupId="
			+ groupId + ", groupSeq=" + groupSeq + ", groupLv=" + groupLv + ", fId=" + fId + ", fileuri=" + fileuri
			+ "]";
}




}
