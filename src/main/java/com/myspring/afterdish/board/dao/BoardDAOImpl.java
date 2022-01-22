package com.myspring.afterdish.board.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;

import com.myspring.afterdish.board.vo.BoardVO;
import java.util.Map;
@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO{
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private BoardVO boardVO;
	
	@Override
	public List<BoardVO> selectAllArticleList()throws DataAccessException{
		List<BoardVO> articleList = sqlSession.selectList("mapper.board.selectAllArticleList");
		return articleList;
	}
	
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException{
		
		int articleNO = selectNewArticleNO();
		
		articleMap.put("articleNO",articleNO);
		sqlSession.insert("mapper.board.insertNewArticle",articleMap);
		
		return articleNO;
	}
	
	@Override
	public BoardVO viewArticle(int articleNO)throws DataAccessException{
		boardVO = sqlSession.selectOne("mapper.board.selectArticle",articleNO);
		return boardVO;
	}
	
	@Override
	public int deleteBoard(int articleNO)throws DataAccessException{
		return sqlSession.delete("mapper.board.deleteBoard",articleNO);
	}
	
	@Override
	public void updateBoard(Map boardMap)throws DataAccessException{
		System.out.println("다오 들어가고");
		System.out.println("map내용"+boardMap.get("articleNO") + boardMap.get("content"));
		sqlSession.update("mapper.board.updateBoard",boardMap);
		System.out.println("다오 나가고");
	}
	
	private int selectNewArticleNO() throws DataAccessException{
		
		System.out.println("지금의 총개수: " + sqlSession.selectOne("mapper.board.getNewArticleNO"));
		return sqlSession.selectOne("mapper.board.getNewArticleNO");
	}
}
