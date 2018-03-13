package service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IMemberDao;
import model.Member;

@Service("memberService")
public class MemberService implements IMemberService {
	@Autowired
	private IMemberDao memberDao;

	@Override
	public int joinMember(Member member) {
		int result = memberDao.insertMember(member);
		return result;
	}

	@Override
	public Member getMember(int memId) {

		Member member = memberDao.getMember(memId);
		return member;
	}

	@Override
	public int userCheck(Member member) {
		int result = -1;
		HashMap<String, Object> params = new HashMap<>();
		params.put("id", member.getId());
		Member user = memberDao.userCheck(params);

		if (user == null) {
			result = -1;
		}

		else if (user.getPwd() != null && user.getPwd().equals(member.getPwd())) {
			result = user.getMemId();
		}

		else {
			result = 0; // 비밀번호 틀림
		}

		return result;
	}

	@Override
	public boolean confirmMemberId(String memberId) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("memberId", memberId);
		int result = memberDao.confirmMemberId(params);
		if (result == 0) {
			return true;
		}

		//존재하면
		return false;
	}

}
