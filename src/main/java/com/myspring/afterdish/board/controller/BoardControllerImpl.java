package com.myspring.afterdish.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Enumeration;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.myspring.afterdish.board.service.BoardService;
import com.myspring.afterdish.board.vo.BoardVO;
import com.myspring.afterdish.member.vo.MemberVO;
@Controller("boardController")
public class BoardControllerImpl implements BoardController{
	private static final String BOARD_IMAGE_REPO = "C:\\afterdish\\board\\image";
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardVO boardVO;
	
	@Override
	@RequestMapping(value="/board/listArticle.do",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView listArticle(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		System.out.println("system");
		List articleList = boardService.listArticle();
		System.out.println("리스트의 크기는 : " + articleList.size());
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("articleList",articleList);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/board/addArticleForm.do",method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView addArticleForm(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		return mav;
	}
	
	
	
	@Override
	@RequestMapping(value="/board/addNewArticle.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest,HttpServletRequest request,HttpServletResponse response)throws Exception{
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String,Object> articleMap = new HashMap<String,Object>();
		Enumeration en = multipartRequest.getParameterNames();
		while(en.hasMoreElements()) {
			String name=(String)en.nextElement();
			String value=multipartRequest.getParameter(name);
			articleMap.put(name, value);
		}
		
		String imageFileName = upload(multipartRequest);
		HttpSession session  =multipartRequest.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		String id = memberVO.getId();
		
		articleMap.put("parentNO", 0);
		articleMap.put("id", id);
		articleMap.put("imageFileName", imageFileName);
		
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			int articleNO = boardService.addNewArticle(articleMap);
			if(imageFileName!= null && imageFileName.length()!=0) {
				File srcFile = new File(BOARD_IMAGE_REPO+"\\" + "temp" + "\\" +imageFileName);
				File destDir = new File(BOARD_IMAGE_REPO+"\\" + articleNO);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
				
			}
			message = "<script>";
			message += " alert('새글을 추가했습니다.');";
			message += " location.href='"+multipartRequest.getContextPath()+"/board/listArticle.do'; ";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			
		}catch(Exception e) {
			File srcFile = new File(BOARD_IMAGE_REPO + "\\" + "temp" + "\\" +imageFileName);
			srcFile.delete();
			
			message = " <script>";
			message +=" alert('오류가 발생했습니다. 다시 시도해 주세요');');";
			message +=" location.href='"+multipartRequest.getContextPath()+"/board/addarticleForm.do'; ";
			message +=" </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
		
	}
	
	@Override
	@RequestMapping(value="/board/viewArticle.do",method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView viewArticle(@RequestParam("articleNO")int articleNO, HttpServletRequest request, HttpServletResponse response)throws Exception{
		String viewName = (String)request.getAttribute("viewName");
		
		boardVO = boardService.viewArticle(articleNO);
		
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("article", boardVO);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/board/updArticle.do", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity updArticle(MultipartHttpServletRequest multipartRequest,HttpServletResponse response)throws Exception{
		
		    multipartRequest.setCharacterEncoding("utf-8");
		    System.out.println("updarticle들어옴");
			Map<String,Object> articleMap = new HashMap<String, Object>();
			Enumeration enu=multipartRequest.getParameterNames();
			while(enu.hasMoreElements()){
				String name=(String)enu.nextElement();
				String value=multipartRequest.getParameter(name);
				articleMap.put(name,value);
				System.out.println("name: " + name + " value=" + value);
			}
			
			String imageFileName= upload(multipartRequest);
			articleMap.put("imageFileName", imageFileName);
			
			String articleNO=(String)articleMap.get("articleNO");
			String message;
			ResponseEntity resEnt=null;
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		    try {
		    	System.out.println("서비스전");
		       boardService.updBoard(articleMap);
		       System.out.println("서비스후");
		       if(imageFileName!=null && imageFileName.length()!=0) {
		         File srcFile = new File(BOARD_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
		         File destDir = new File(BOARD_IMAGE_REPO+"\\"+articleNO);
		         FileUtils.moveFileToDirectory(srcFile, destDir, true);
		         
		         String originalFileName = (String)articleMap.get("originalFileName");
		         File oldFile = new File(BOARD_IMAGE_REPO+"\\"+articleNO+"\\"+originalFileName);
		         oldFile.delete();
		       }	
		       message = "<script>";
			   message += " alert('글을 수정했습니다.');";
			   message += " location.href='"+multipartRequest.getContextPath()+"/board/viewArticle.do?articleNO="+articleNO+"';";
			   message +=" </script>";
			   System.out.println("계란빵인가");
		       resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    }catch(Exception e) {
		      File srcFile = new File(BOARD_IMAGE_REPO+"\\"+"temp"+"\\"+imageFileName);
		      srcFile.delete();
		      message = "<script>";
			  message += " alert('오류가 발생했습니다.다시 수정해주세요');";
			  message += " location.href='"+multipartRequest.getContextPath()+"/board/viewArticle.do?articleNO="+articleNO+"';";
			  message +=" </script>";
		      resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    }
		    return resEnt;
		  }
		  
	
	@Override
	@RequestMapping(value="/board/delArticle.do", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity delArticle(@ModelAttribute("aritlce") int articleNO, HttpServletRequest request, HttpServletResponse response)throws Exception{
		response.setContentType("text/html; charset=UTF-8"); //왜 따로 또 하는걸까?
		String message;
		ResponseEntity resEnt =null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html;charset=utf-8");
		try {
			boardService.delBoard(articleNO);
			File destDir = new File(BOARD_IMAGE_REPO + "\\" + articleNO);
			FileUtils.deleteDirectory(destDir);
			
			message = "<script>";
			message += " alert('글을 삭제했습니다.');";
			message += " location.href='"+request.getContextPath()+"/board/listArticle.do';";
			message +=" </script>";
			resEnt = new ResponseEntity(message,responseHeaders, HttpStatus.CREATED);
		}catch(Exception e) {
			message = "<script>";
			message += " alert('작업중 오류가 발생했습니다.다시 시도해 주세요.');";
			message += " location.href='"+request.getContextPath()+"/board/listArticle.do';";
			message +=" </script>";
		    resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		    e.printStackTrace();
		}
		return resEnt;
		
	}
	
	private String upload(MultipartHttpServletRequest multipart)throws Exception{
		String imageFileName = null;
		Iterator<String> fileNames = multipart.getFileNames();
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mfile = multipart.getFile(fileName);
			imageFileName = mfile.getOriginalFilename();
			File file = new File(BOARD_IMAGE_REPO + "\\" + "temp" + "\\" + fileName);
			if(mfile.getSize()!=0) {
				if(!file.exists()) {
					file.getParentFile().mkdirs();
					mfile.transferTo(new File(BOARD_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName));
					
				}
			}
		}
		return imageFileName;
	}
}
