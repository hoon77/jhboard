package dao;

import java.util.HashMap;
import java.util.List;

import model.Comment;

public interface ICommentDao {
	public int insertComment(Comment comment);
	public int updateComment(Comment comment);
	public int deleteComment(int cId);
	public int deleteCommentBybId(int bId);
	public Comment selectOne(int cId);
	public int updateGroupSeq(HashMap<String, Object> params);
	public List<Comment> selectCommentByBId(int bId);
	public int countCommentBybId(int bId);
	public int countCommentBymId(int mId);
	public List<Comment> selectMyCommentList(HashMap<String, Object> params);
	
}
