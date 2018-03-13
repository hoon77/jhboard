package service;

import java.util.HashMap;
import java.util.List;

import model.Comment;

public interface ICommentService {

	//새로운 댓글을 추가하는 기능
	public void writeComment(Comment comment, int parentId);
	//댓글의 내용을 수정
	public void modifyComment(Comment comment);
	//댓글을 삭제
	public void deleteComment(int cId);
	//게시물별로 댓글리스트를 조회
	public List<Comment> getCommentList(int bId);
	
	public HashMap<String, Object> getMyCommentList(int page, int mId);
}
