package com.myspring.afterdish.board.service;

import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myspring.afterdish.board.dao.BoardDAO;
import com.myspring.afterdish.board.vo.BoardVO;
import java.util.Map;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDAO boardDAO;
	@Autowired
	BoardVO boardVO;
	
	@Override
	public List listArticle()throws Exception{
		List<BoardVO> articleList = boardDAO.selectAllArticleList();
		return articleList;
	}
	
	@Override
	public int addNewArticle(Map articleMap)throws Exception{
		return boardDAO.insertNewArticle(articleMap);
	}
	
	@Override
	public BoardVO viewArticle(int articleNO)throws Exception{
		return boardDAO.viewArticle(articleNO);
	}
	
	@Override
	public void updBoard(Map boardMap)throws Exception{
		boardDAO.updateBoard(boardMap);
	}
	
	@Override
	public void delBoard(int articleNO)throws Exception{
		boardDAO.deleteBoard(articleNO);
	}
}
