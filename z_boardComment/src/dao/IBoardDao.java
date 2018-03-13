package dao;

import java.util.HashMap;
import java.util.List;

import model.Board;
import model.sobi;

public interface IBoardDao {
	
	public int insertBoard(Board board);
	public int updateBoard(Board board);
	public int deleteBoard(int bId);
	public Board selectBoard(int bId);
	public List<Board> selectList(HashMap<String, Object> params);
	public List<Board> selectUpBestList(HashMap<String, Object> params);
	public List<Board> selectMyList(HashMap<String, Object> params);
	//매개변수가 hashmap이기때문에인터페이스 수정없이 원하는 파라미터를
	//조정해서 사용할 수있따 
	public int getBoardCount();
	public int getUpBestCount();
	public int getSearchBoardCount(HashMap<String, Object> params);
	public int getMyBoardCount(int mId);
	public int updateGroupSeq(HashMap<String, Object> params);
	
	public List<sobi> selectSobiList(HashMap<String, Object> params);
	
	
}

