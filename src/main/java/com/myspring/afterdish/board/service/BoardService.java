package com.myspring.afterdish.board.service;

import com.myspring.afterdish.board.vo.BoardVO;

import java.util.List;
import java.util.Map;
public interface BoardService {
	public List listArticle()throws Exception;
	public int addNewArticle(Map articleMap)throws Exception;
	public BoardVO viewArticle(int articleNO)throws Exception;
	public void updBoard(Map boardMap)throws Exception;
	public void delBoard(int articleNO)throws Exception;
	
}
