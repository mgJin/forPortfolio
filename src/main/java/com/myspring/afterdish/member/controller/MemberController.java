package com.myspring.afterdish.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.myspring.afterdish.member.vo.MemberVO;

public interface MemberController {
	public ModelAndView listMember(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ModelAndView addMember(@ModelAttribute("info") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ModelAndView removeMember(@ModelAttribute("id") String id,HttpServletRequest request, HttpServletResponse resonse)throws Exception;
	public ModelAndView login(@ModelAttribute("member") MemberVO member, RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public int checkDuplicationById(@ModelAttribute("id") String id,HttpServletRequest request, HttpServletResponse resonse)throws Exception;
}
