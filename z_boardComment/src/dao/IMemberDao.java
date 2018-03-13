package dao;

import java.util.HashMap;

import model.Member;

public interface IMemberDao {

	public int insertMember(Member member);
	public Member getMember(int memId);
	public Member userCheck(HashMap<String, Object> params);
	public int confirmMemberId(HashMap<String, Object> params);
	
}
