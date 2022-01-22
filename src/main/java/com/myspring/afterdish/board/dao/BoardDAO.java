package com.myspring.afterdish.board.dao;

import java.util.List;
import com.myspring.afterdish.board.vo.BoardVO;
import org.springframework.dao.DataAccessException;
import java.util.Map;
public interface BoardDAO {
	public List<BoardVO> selectAllArticleList()throws DataAccessException;
	public int insertNewArticle(Map articleMap)throws DataAccessException;
	public BoardVO viewArticle(int articleNo)throws DataAccessException;
	public int deleteBoard(int articleNO) throws DataAccessException;
	public void updateBoard(Map boardMap)throws DataAccessException;
	
}
