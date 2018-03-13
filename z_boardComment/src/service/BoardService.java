package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.multipart.MultipartFile;

import dao.IBoardDao;
import dao.IBoardFileDao;
import dao.ICommentDao;
import model.Board;
import model.BoardFile;
import model.sobi;

@Service("boardService")
public class BoardService implements IBoardService {
	@Autowired
	private IBoardDao boardDao;
	@Autowired
	private IBoardFileDao boardFileDao;
	@Autowired
	private ICommentDao commentDao;	
	
	@Override
	public void writeBoard( Board board, int parentId) {

		// parentId 가 0 이면 원글
		// 0이 아니면 답글
		if (parentId == 0) {
			// ----원글쓰기
			// insert후에 얻어진 내 아디를 그룹아디로
			boardDao.insertBoard(board);
			int bId = board.getbId();
			board.setGroupId(bId);
			boardDao.updateBoard(board);
		} else {
			// ----답글쓰기
			Board parentBoard = boardDao.selectBoard(parentId);
			// 나의 그룹아이디는 부모의 아이디
			int parentGroupId = parentBoard.getGroupId();
			board.setGroupId(parentGroupId);
			// 내 자리를 확보하기 위해 부모시퀀스+1보다 시퀀스가 큰 같은 그룹애들의
			int mySeq = parentBoard.getGroupSeq() + 1;
			int myLev = parentBoard.getGroupLv() + 1;
			// 시퀀스를 ++
			HashMap<String, Object> params = new HashMap<>();
			params.put("group_id", parentGroupId);
			params.put("group_seq", mySeq);
			int result = boardDao.updateGroupSeq(params);
			System.out.println(result);
			// 나의 시퀀스는 부모의 시퀀스보다 + 1
			// 내 레벨(이름 잘못 지은 parent_id컬럼) 은 부모 의 레벨보다 +1
			board.setGroupSeq(mySeq);
			board.setGroupLv(myLev);
			boardDao.insertBoard(board);
			// 필요하다면 dao에 sql문을 추가하세요

		}

		// boardDao.insertBoard(board);
	}

	@Override
	public Board readBoard(int bId) {
		// TODO Auto-generated method stub
		Board board = boardDao.selectBoard(bId);
		board.setHit(board.getHit() + 1);
		boardDao.updateBoard(board);
		return board;
	}

	@Override
	public void modifyBoard(Board board) {
		 boardDao.updateBoard(board);
	}

	
	
	
	@Override
	public HashMap<String, Object> getBoardList(int page, int mode, String keyword) {
		// TODO Auto-generated method stub
		int start = (page - 1) / 10 * 10 + 1;
		int end = ((page - 1) / 10 + 1) * 10;
		int first = 1;
		int last = (boardDao.getBoardCount() - 1) / 10 + 1;
		int skip = (page - 1) * 10;
		int count = 10;

		System.out.println(skip + " " + count);

		HashMap<String, Object> params = new HashMap<>();
		params.put("skip", skip);
		params.put("count", count);

		if (mode == 1) {
			params.put("title", keyword);
			last = (boardDao.getSearchBoardCount(params) - 1) / 10 + 1;

		} else if (mode == 2) {
			params.put("content", keyword);
			last = (boardDao.getSearchBoardCount(params) - 1) / 10 + 1;

		} else if (mode == 3) {
			params.put("title", keyword);
			params.put("content", keyword);
			last = (boardDao.getSearchBoardCount(params) - 1) / 10 + 1;

		} else if(mode == 4) {
			params.put("writer", keyword);
			last = (boardDao.getSearchBoardCount(params) - 1) / 10 + 1;
		}
		
		else if(mode ==5) {//추천글
			last =(boardDao.getUpBestCount()- 1) / 10 + 1;
		}

		
		
		end = last < end ? last : end;

		
		List<Board> boardList = null;
		List<Integer> countCommentList = new ArrayList<>();
		
	  if(mode == 0 || mode== 1 || mode == 2 || mode ==3 || mode ==4) {
		 params.put("mode", mode);
		 boardList = boardDao.selectList(params);
		 
		 for(Board b : boardList) {
			 int bId = b.getbId();
			countCommentList.add(commentDao.countCommentBybId(bId));
		
		 }
		 
		 }
	  
	  else if(mode==5) {
		 params.put("mode", mode);
		boardList = boardDao.selectUpBestList(params);
		 for(Board b : boardList) {
			 int bId = b.getbId();
			countCommentList.add(commentDao.countCommentBybId(bId));
		 }
	  }
	  
	
	  
	

		HashMap<String, Object> results = new HashMap<>();
		results.put("start", start);
		results.put("end", end);
		results.put("first", first);
		results.put("last", last);
		results.put("current", page);
		results.put("boardList", boardList);
		results.put("countCommentList", countCommentList);
		return results;

		// 원하는 페이지가 17 이고 게시물이 총 276개 들어있다면
		// 첫페이지는 1
		// 시작페이지는 11
		// 끝페이지는 20
		// 마지막페이지는 28
		// skip은 160
		// count는 10

		// 해쉬맵에 게시물리스트, 시작페이지, 끝페이지, 현재페이지, 마지막 페이지
		// 의 정보를 담아서 리턴
		// 한 페이지당 게시물의 수 10개

		// 게시물의 리스트를 얻어오기 위해서는 내가 보고 싶은 페이지에 적합한
		// skip 과 count 값을 계산해서 찾아내야되고,
		// 그 값을 HashMap으로 만들어서 dao에 전달
	}

	@Override
	public Board getBoard(int bId) {
		// TODO Auto-generated method stub
		return boardDao.selectBoard(bId);
	}

	@Override
	public BoardFile getBoardFile(int fId) {
		
		return boardFileDao.selectOne(fId);
	}
	
	@Override
	public int boardUpDown(int mode, int bId) {
		int result = 0 ;
		//추천
		if (mode == 0) { 
         Board board = boardDao.selectBoard(bId);
         board.setUp(board.getUp()+1);
         boardDao.updateBoard(board);
         result = boardDao.selectBoard(bId).getUp();
		}
		
		//비추천
		else {
			
			 Board board = boardDao.selectBoard(bId);
	         board.setDown(board.getDown()+1);
	         boardDao.updateBoard(board);
	         result = boardDao.selectBoard(bId).getDown();
		}
		
		return result;
	}

	@Override
	public void deleteBoard(int bId) {
		boardDao.deleteBoard(bId);
		commentDao.deleteCommentBybId(bId);
		
	}

	@Override
	public void updateFile(Board board, MultipartFile file) {

		String path = "C:\\fileUpload\\";
		String fileuri = path + file.getOriginalFilename();
		System.out.println(fileuri);
    
		
		//원본 파일명
		String fileName = file.getOriginalFilename();
		//파일의 사이즈
		int size = (int)file.getSize();
		//파일을 저장할 경로를 얻어서
		//BoardFile객체에 담으세요
		BoardFile boardFile = new BoardFile();
		boardFile.setOriginFileName(fileName);
		boardFile.setSize(size);
		boardFile.setUri(fileuri);
		
		File localFile = new File(fileuri);
		try {
			file.transferTo(localFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//boardFile테이블에 방금 준비한 boardFile 데이터를 삽입하고
		boardFileDao.insertBoardFile(boardFile);
		//삽입된 레코드의 파일 아이디를 
		int fileid = boardFile.getId();
		//board의 fileid컬럼에 넣어서 board 데이터 삽입
		board.setfId(fileid);
		board.setFileuri(fileuri);
		boardDao.updateBoard(board);
		
	}

	@Override
	public HashMap<String, Object> getMyBoardList(int page, int mId) {
		int start = (page - 1) / 5 * 5 + 1;
		int end = ((page - 1) / 5 + 1) * 5;
		int first = 1;
		int last = (boardDao.getMyBoardCount(mId) - 1) / 5 + 1;
		System.out.println("last :" +last);
		System.out.println("mId :" +mId);
	
		int skip = (page - 1) * 5;
		int count = 5;


		HashMap<String, Object> params = new HashMap<>();
		params.put("skip", skip);
		params.put("count", count);

		
		end = last < end ? last : end;

		
		List<Board> boardList = null;
		List<Integer> countCommentList = new ArrayList<>();
		
	  
	
		 params.put("mId", mId);
		boardList = boardDao.selectMyList(params);
		 for(Board b : boardList) {
			 int bId = b.getbId();
			countCommentList.add(commentDao.countCommentBybId(bId));
		 }
	
	  
	
	  
	

		HashMap<String, Object> results = new HashMap<>();
		results.put("start", start);
		results.put("end", end);
		results.put("first", first);
		results.put("last", last);
		results.put("current", page);
		results.put("boardList", boardList);
		results.put("countCommentList", countCommentList);
		return results;

		// 원하는 페이지가 17 이고 게시물이 총 276개 들어있다면
		// 첫페이지는 1
		// 시작페이지는 11
		// 끝페이지는 20
		// 마지막페이지는 28
		// skip은 160
		// count는 10

		// 해쉬맵에 게시물리스트, 시작페이지, 끝페이지, 현재페이지, 마지막 페이지
		// 의 정보를 담아서 리턴
		// 한 페이지당 게시물의 수 10개

		// 게시물의 리스트를 얻어오기 위해서는 내가 보고 싶은 페이지에 적합한
		// skip 과 count 값을 계산해서 찾아내야되고,
		// 그 값을 HashMap으로 만들어서 dao에 전달
	}

	@Override
	public List<sobi> getCalendarList(int year, int month) {
		HashMap<String, Object> params = new HashMap<>();
		params.put("year", year);
		params.put("month",month+1);
	   List<sobi> sobiSumList = boardDao.selectSobiList(params);
	   
	   for(sobi a : sobiSumList) {
		   System.out.println(a);
	   }
		return sobiSumList;
	}
	
}
