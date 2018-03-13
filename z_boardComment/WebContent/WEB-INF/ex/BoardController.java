package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Board;
import model.BoardFile;
import service.IBoardService;

@Controller
public class BoardController {
	@Autowired
	private IBoardService boardService;
	
	@RequestMapping("list.do")
	//함수만드셈
	public ModelAndView list(@RequestParam(defaultValue="1") int page){
		ModelAndView mav = new ModelAndView();
		mav.addAllObjects(boardService.getBoardList(page));
		mav.setViewName("list");
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="view.do")
	public ModelAndView view(int num){
		ModelAndView mav = new ModelAndView();
//		mav.addObject("board",boardService.readBoard(num));
		mav.addAllObjects(boardService.readBoard(num));
		mav.setViewName("view");
		return mav;
	}
	@RequestMapping("writeForm.do")
	public void writeForm(){}
	
	@RequestMapping(method=RequestMethod.POST,value="write.do")
	public String write(Board board,@RequestParam("ufile") MultipartFile ufile)
	{
		boardService.writeBoard(board,  ufile);
		return "redirect:view.do?num=" + board.getNum();
	}
	@RequestMapping("delete.do")
	public String delete(int num, String pass)
	{
		boolean isDeleted = boardService.deleteBoard(num, pass);
		if(isDeleted)
			return "redirect:list.do";
		else
			return "redirect:view.do?num="+num;
	}
	
//	@RequestMapping("chkPwd.do")
//	public String chkPwd(Model model, int num){
//		model.addAttribute("num", num);
//		return "chkPwd";
//	}
	@RequestMapping("modifyForm.do")
	public ModelAndView modifyForm(int num)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("modifyForm");
//		mav.addObject("board", boardService.getBoard(num));
		mav.addAllObjects(boardService.getBoard(num));
		return mav;
	}
	@RequestMapping("modify.do")
	public String modify(Board board) {
		boardService.updateBoard(board);
		return "redirect:view.do?num=" + board.getNum();
	}
	
	@RequestMapping("main.do")
	public void main(){}
	
	
	
	@RequestMapping("download.do")
	public View download(int id){
		BoardFile boardFile = boardService.getBoardFile(id);
		File file = new File(boardFile.getUri());
		return new DownloadView(file, boardFile.getOriginFileName());
	}
	
	
	@RequestMapping("getBoardJson.do")
	public
	@ResponseBody Board getBoardJson(HttpServletResponse resp, int num)
	{
		return (Board) boardService.getBoard(num).get("board");
	}
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));

	}
}














