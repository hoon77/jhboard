package model;

public class Member {

 private int memId;
 private String id;
 private String pwd;
 private String name;
public int getMemId() {
	return memId;
}
public void setMemId(int memId) {
	this.memId = memId;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Override
public String toString() {
	return "Member [memId=" + memId + ", id=" + id + ", pwd=" + pwd + ", name=" + name + "]";
}
	
 
 
}