package controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSplitPaneUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Member;
import service.BoardService;
import service.IBoardService;
import service.ICommentService;
import service.IMemberService;

@Controller
public class memberController {
   @Autowired
	private IMemberService memberService;
   @Autowired
   private IBoardService boardService;
   @Autowired
   private ICommentService commentService;
   
   @RequestMapping("main.do")
	public String main(HttpSession session, Model model, @RequestParam(defaultValue = "1") int boardPage,
			@RequestParam(defaultValue = "1") int commentPage) {
        Member member = (Member) session.getAttribute("loginUser");
        System.out.println("loginUser:"+member.getMemId());
		model.addAllAttributes(boardService.getMyBoardList(boardPage,member.getMemId() ));
		model.addAllAttributes(commentService.getMyCommentList(commentPage, member.getMemId()));
		return "main";
	}
   
   
   
  
  
   @RequestMapping(value ="confirmMemberId.do", produces = { "application/json" })
	public @ResponseBody boolean confirmMemberId(String memberId) {    
	   boolean result = memberService.confirmMemberId(memberId);
		return result;
	}
   
   
	@RequestMapping("loginForm.do")
	public String loginForm(Model model, @RequestParam(defaultValue="0") int check) {
		
		
		String message = null ;
		if(check==1) {
			message = "회원가입 되었습니다 로그인을 해주세요!";
		}
		
		else if(check==2) {
			message = "존재하지 않는 아이디입니다!";
		}
		
		else if(check==3) {
			message = "비밀번호가 맞지 않습니다!";
		}
		
		model.addAttribute("message",message);
		
		return "loginForm";
	}
	
	
	
	@RequestMapping("joinForm.do")
	public String joinForm() {
		return "joinForm";
	}
	
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
	    session.invalidate();
		return "redirect:loginForm.do";
	}
	
	
	@RequestMapping("join.do")
	public String join(Member member) {
		memberService.joinMember(member);
		return "redirect:loginForm.do?check=1";
	}
	
	@RequestMapping("login.do")
	public  String login(HttpSession session, Member member) {
		int result = memberService.userCheck(member);
	
		if(result== -1) {
			
			return "redirect:loginForm.do?check=2";
		}
		
		else if (result == 0) {
			return "redirect:loginForm.do?check=3";
		}
		
	
		else {
			Member loginUser = memberService.getMember(result);
			session.setAttribute("loginUser", loginUser);
			return "redirect:main.do";
		}

	
	}
	
}
