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


import model.Comment;
import model.SearchParams;
import model.sobi;
import service.BoardService;
import service.CommentService;
import service.IBoardService;
import service.ICommentService;

@Controller
public class commentController {

	
	@Autowired
	private ICommentService commentService;

	

	@RequestMapping("writeComment.do")
	public String writeComment(Comment comment, int parentId) {
		System.out.println("dkd" + parentId);
		commentService.writeComment(comment, parentId);
		return "redirect:boardView.do?mode=0&bId=" + comment.getbId();

	}

	@RequestMapping("deleteComment.do")
	public String deleteComment(int cId, int bId) {
		commentService.deleteComment(cId);
		return "redirect:boardView.do?mode=0&bId=" + bId;

	}
	
	

	
	

	
	
	
}
