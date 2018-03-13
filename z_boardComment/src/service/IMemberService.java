package service;

import model.Member;

public interface IMemberService {

	
	public int joinMember(Member member);
	public Member getMember(int memId);
	public int userCheck(Member member);
	public boolean confirmMemberId(String memberId);
}
