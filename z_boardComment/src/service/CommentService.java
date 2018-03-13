package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import dao.ICommentDao;
import model.Board;
import model.Comment;

@Service
public class CommentService implements ICommentService {

	@Autowired
	private ICommentDao commentDao;

	@Override
	public void writeComment(Comment comment, int parentId) {

		try{
			// TODO Auto-generated method stub
			if (parentId == 0) {
				// 원 댓글 쓰기
				commentDao.insertComment(comment);
				int cId = comment.getcId();
		 		comment.setGroupId(cId);
				commentDao.updateComment(comment);
			} else {
				// 대댓글 쓰기
				// 부모글 정보를 조회해서
				Comment parentComment = commentDao.selectOne(parentId);
				// 부모글의 그룹아이디가 내가 쓸 그룹아이디
				int groupId = parentComment.getGroupId();
				// 내 그룹 시퀀스와 레벨은 부모글 +1
				int groupSeq = parentComment.getGroupSeq() + 1;
				int groupLv = parentComment.getGroupLv() + 1;
				// 내 자리 확보
				HashMap<String, Object> params = new HashMap<>();
				params.put("groupSeq", groupSeq);
				params.put("groupId", groupId);
				commentDao.updateGroupSeq(params);
				// 필요한 그룹정보 세팅해서 인설트
				comment.setGroupId(groupId);
				comment.setGroupSeq(groupSeq);
				comment.setGroupLv(groupLv);
				commentDao.insertComment(comment);
				System.out.println(comment.getcId() + "저장");
			}
		}
		catch(DataAccessException e){
			System.out.println("예외발생. 롤백합니다.");
		}
	}

	@Override
	public void modifyComment(Comment comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteComment(int cId) {
		commentDao.deleteComment(cId);

	}

	@Override
	public List<Comment> getCommentList(int bId) {
		// TODO Auto-generated method stub
		return commentDao.selectCommentByBId(bId);
	}

	@Override
	public HashMap<String, Object> getMyCommentList(int page ,int mId) {
		
		int start = (page - 1) / 5 * 5 + 1;
		int end = ((page - 1) / 5 + 1) * 5;
		int first = 1;
		int last = (commentDao.countCommentBymId(mId) - 1) / 5 + 1;
		System.out.println("last :" +last);
		System.out.println("mId :" +mId);
	
		int skip = (page - 1) * 5;
		int count = 5;


		HashMap<String, Object> params = new HashMap<>();
		params.put("skip", skip);
		params.put("count", count);
		params.put("mId", mId);
		
		end = last < end ? last : end;

		List<Comment> commentList = null;
		
		commentList = commentDao.selectMyCommentList(params);
		
	

		HashMap<String, Object> results = new HashMap<>();
		results.put("c_start", start);
		results.put("c_end", end);
		results.put("c_first", first);
		results.put("c_last", last);
		results.put("c_current", page);
		results.put("commentList", commentList);
		return results;

	}

}
