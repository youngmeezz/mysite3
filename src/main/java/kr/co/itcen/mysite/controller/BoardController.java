package kr.co.itcen.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.itcen.mysite.service.BoardService;
import kr.co.itcen.mysite.vo.BoardVo;
import kr.co.itcen.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	//게시판 조회하기
	@RequestMapping({"", "/list"})
	public String list(Model model) {
		List<BoardVo> list = boardService.getList();
		model.addAttribute("list",list);   //??????
		return "board/list";
	}
	
	//게시판 글 삽입할 글쓰기 페이지 가져오기
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String insertForm() {
		return "board/write" ;
	}
	
	//게시판 글  등록
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String insert(@ModelAttribute BoardVo vo,HttpSession session) {
	
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if (authUser != null) {
			vo.setUserNo(authUser.getNo());
			boardService.insert(vo);
		}
		
		
		return "redirect:/board" ;
	}
	
	
	//게시판 글 쓴 view 페이지 가져오기
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String viewForm(@PathVariable("no") Long no,@PathVariable("userNo") Long userNo,HttpSession session) {
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if (authUser != null) {
			 authUser.getNo();
			boardService.get(no,userNo);
		}
		return "redirect:/board/view" ;

	}
	
		
	//게시판 삭제한 폼 조회하기
//	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
//	public String delete(@PathVariable("no") Long no, Model model) {
//		model.addAttribute("no",no);
//		return "board/deleteform";
//	}
	
	//게시판 삭제한 후 페이지 돌아오기
//	@RequestMapping(value="/delete",method=RequestMethod.POST)
//	public String delete(@PathVariable("no") Long no, @PathVariable("userNo") Long userNo) {
//		boardService.delete(no, userNo);
//		return "redirect:/board/list" ;
//	}
//
//	
//	//게시판 수정한 폼 조회하기
//	@RequestMapping(value = "/update/{no}", method = RequestMethod.GET)
//	public String update(@PathVariable("no") Long no, Model model) {
//		model.addAttribute("no",no);
//		return "board/modify";
//	}
//	
//	//게시판 수정한 후 페이지 돌아오기
//	@RequestMapping(value="/update",method=RequestMethod.GET)
//	public String updateForm(@PathVariable("no") Long no,@PathVariable("userNo") Long userNo) {
//		boardService.update(no, userNo);
//		return "redirect:/board/list" ;
//	}

	
}
