package service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import model.Board;
import model.BoardFile;
import model.sobi;

public interface IBoardService {
//글쓰기, 글읽기, 글수정, 글목록
	
	//그냥 글쓰기
	public void writeBoard(Board board, int parentId);
	//글 상세조회, 조회수++
	public Board readBoard(int bId);
	//글 수정 
	public void modifyBoard(Board board);
	//게시물 내용 얻어오기
	public void updateFile(Board board,MultipartFile file);
	public void deleteBoard(int bId);
	public Board getBoard(int bId);
	public BoardFile getBoardFile(int fId);
	//게시물 목록페이지에 필요한 데이터들 줏어오기
	public HashMap<String, Object> getBoardList(int page, int mode, String keyword);
	public HashMap<String, Object> getMyBoardList(int page, int mId);
	
    public int boardUpDown(int mode, int bId);
	
    
    
    public List<sobi> getCalendarList(int year, int month);
	//서비스 단에서 getBoardList 호출시 
	//mode가 0이면 전체검색, 1이면 제목 ,2 내용 ,3 제목+내용 
	//서비스 기능규약에 제목검색어를 매개변수로 추가 
	//지금은 타이틀로 검색밖에 안됨 
	//검색안하고 싶어도 해야됨 
	//동적쿼리를 이용해서 like에 title 이 안들어오면 전체검색 
	//아니면 제목검색으로 해보라긔 
	
}







