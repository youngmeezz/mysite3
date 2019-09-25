package kr.co.itcen.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.itcen.mysite.service.GuestbookService;
import kr.co.itcen.mysite.vo.GuestBookVo;


@Controller
@RequestMapping("/guestbook1")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	//방명록 조회하기
	@RequestMapping({"", "/list"})
	public String list(Model model) {
		List<GuestBookVo> list = guestbookService.getList();
		model.addAttribute("list",list);   //??????
		return "guestbook/list";
	}
	//방명록 글 삽입하기
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute GuestBookVo vo) {
		guestbookService.insert(vo);
		return "redirect:/guestbook1" ;
	}
	
	
	//방명록 삭제한 폼 조회하기
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no",no);
		return "guestbook/deleteform";
	}
	
	//방명록 삭제한 후 페이지 돌아오기
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestBookVo vo) {
		guestbookService.delete(vo);
		return "redirect:/guestbook1/list" ;

	}

}
