package com.myspring.afterdish.board.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
public interface BoardController {
	public ModelAndView listArticle(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ModelAndView addArticleForm(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest,HttpServletRequest request,HttpServletResponse response)throws Exception;
	public ModelAndView viewArticle(@RequestParam("articleNO") int articleNO,HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ResponseEntity updArticle(MultipartHttpServletRequest multipartRequest,HttpServletResponse response)throws Exception;
	public ResponseEntity delArticle(@ModelAttribute("articleNO")int articleNO,HttpServletRequest request,HttpServletResponse response)throws Exception;
}
