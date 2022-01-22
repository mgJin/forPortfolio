package com.myspring.afterdish.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myspring.afterdish.member.service.MemberService;
import com.myspring.afterdish.member.vo.MemberVO;
import org.springframework.web.bind.annotation.ModelAttribute;
@Controller("memberController")
public class MemberControllerImpl implements MemberController{
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberVO memberVO;
	
	@RequestMapping(value= {"/","/main.do"}, method=RequestMethod.GET)
	private ModelAndView main(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String viewName = (String)request.getAttribute("viewName"); //????
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/member/listMember.do", method=RequestMethod.GET)
	public ModelAndView listMember(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		List memberList = memberService.listMember();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("memberList",memberList);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/member/addMember.do", method=RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberVO vo, HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.setCharacterEncoding("utf-8");
		int result = 0;
		result = memberService.addMember(vo);
		ModelAndView mav = new ModelAndView("redirect:/main.do"); 
		return mav;
	}
	@Override
	@RequestMapping(value="/member/removeMember.do" ,method = RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, 
			           HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMember.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO vo, RedirectAttributes rAttr,HttpServletRequest request,HttpServletResponse response)throws Exception{
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(vo);
		if(memberVO!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("member",memberVO);
			session.setAttribute("isLogOn", true);
			mav.setViewName("redirect:/main.do"); //고쳐야할듯?
		}else {
			rAttr.addAttribute("result","loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/member/logout.do", method =  RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/main.do");
		
		return mav;
	}
	
	@RequestMapping(value = "/member/*Form.do", method =  RequestMethod.GET)
	private ModelAndView form(@RequestParam(value= "result", required=false) String result,
						       HttpServletRequest request, 
						       HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.addObject("result",result);
		mav.setViewName(viewName);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/member/checkDuplicationById",method= RequestMethod.POST)
	@ResponseBody
	public int checkDuplicationById(@ModelAttribute("id")String id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		int resultCheck = memberService.checkDuplicationById(id);
		return resultCheck;
	}
	
}
