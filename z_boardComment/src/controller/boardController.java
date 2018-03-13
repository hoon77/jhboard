package controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import model.Board;
import model.BoardFile;
import model.Comment;
import model.SearchParams;
import model.sobi;
import service.BoardService;
import service.CommentService;
import service.IBoardService;
import service.ICommentService;

@Controller
public class boardController {

	@Autowired
	private IBoardService boardService;
	@Autowired
	private ICommentService commentService;

	@RequestMapping("boardList.do")
	public void getBoardList(Model model, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "0") int mode, SearchParams sp) {
       
		
	
		if (mode == 0) {// 전체목록
			model.addAllAttributes(boardService.getBoardList(page, 0, ""));
			model.addAttribute("mode",0);
		}

		else if(mode==1) {//베스트 추천글
			model.addAllAttributes(boardService.getBoardList(page, 5, ""));
			model.addAttribute("mode",1);
			
		}
		
		else if(mode==2) {// 검색목록
			model.addAllAttributes(boardService.getBoardList(page, sp.getSearchSel(), sp.getSearchInput()));
			model.addAttribute("mode",2);
			model.addAttribute("sp", sp);
		}
 
	} 

	
	@RequestMapping("download.do")
	public View download(int id){
		BoardFile boardFile = boardService.getBoardFile(id);
		File file = new File(boardFile.getUri());
		return new DownloadView(file, boardFile.getOriginFileName());
	}
	
	
	@RequestMapping("boardView.do")
	public void boardtView(Model model, int bId, int mode) {
		Board board = boardService.getBoard(bId);
		if (mode == 1) {
			board.setHit(board.getHit() + 1);
			boardService.modifyBoard(board);
			board = boardService.getBoard(bId);
		}
		BoardFile boardFile = boardService.getBoardFile(board.getfId());
		model.addAttribute("boardFile", boardFile);
		model.addAttribute("board", board);
		model.addAttribute("commentList", commentService.getCommentList(bId));

	}

	@RequestMapping("writeBoardForm.do")
	public String writeBoardForm(Model model, int parentId) {
		model.addAttribute("parentId", parentId);
		return "writeBoardForm";
	}

	
	@RequestMapping("updateBoardForm.do")
	public String updateBoardForm(Model model, int bId) {
		Board board = boardService.getBoard(bId);
		BoardFile boardFile = boardService.getBoardFile(board.getfId());
		model.addAttribute("board", board);
		model.addAttribute("boardFile", boardFile);
		return "updateBoardForm";
	}
	
	
	

	@RequestMapping("test.do")
	public String test() {
		return "test";
	}

	
	
	
	
	@RequestMapping("deleteBoard.do")
	public String deleteBoard(int bId) {
	   boardService.deleteBoard(bId);
	   return "redirect:boardList.do";
	    
	}
	
	
	@RequestMapping("modifyBoard.do")
	public String modifyBoard(Board board,@RequestParam("ufile") MultipartFile ufile) {
	    Board origin = boardService.getBoard(board.getbId());
	    origin.setTitle(board.getTitle());
	    origin.setContent(board.getContent());
	    boardService.modifyBoard(origin);
	    System.out.println("수정 :"+ ufile);
	    System.out.println("수정 :"+ ufile.getOriginalFilename());
	    System.out.println("수정 :"+ ufile.getSize());
	    
	    
	  if(ufile.getSize() != 0 ) {
	    Board UpdateBoard = boardService.getBoard(board.getbId());
	    boardService.updateFile(UpdateBoard, ufile);}
	  
	  
	    return "redirect:boardList.do";
	    
	}
	
	
	
	
	@RequestMapping("writeBoard.do")
	public String writeBoard(Board board, int parentId,@RequestParam("ufile") MultipartFile ufile) {
		boardService.writeBoard(board, parentId);
		 if(ufile.getSize() != 0 ) {
			    Board UpdateBoard = boardService.getBoard(board.getbId());
			    boardService.updateFile(UpdateBoard, ufile);}
		return "redirect:boardList.do";
	}


	
	
	@RequestMapping(value ="boardUpDown.do", produces = { "application/json" })
	public @ResponseBody int boardUp(int mode, int bId) {
		int result = boardService.boardUpDown(mode, bId);
		System.out.println(result);
		return result;
	}
	
	

	

	
	
}
