package kr.or.ddit.board.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.Util.BoardUtil;

public class BoardDaoImpl implements IBoardDao{
	
	private static IBoardDao boardDao;
	
	private BoardDaoImpl() {
		
	}
	
	public static IBoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		
		return boardDao;
	}
	
	@Override
	public int insertNotice(BoardVO bv) {
		
		int cnt = 0;
		
		SqlSession session = BoardUtil.getInstance();
		
		try {
			cnt = session.insert("board.insertNotice", bv);
			session.commit();
		}catch(PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return cnt;
	}
	
	@Override
	public int updateNotice(BoardVO bv) {
		
		int cnt = 0;
		
		SqlSession session = BoardUtil.getInstance();
		
		try {
			cnt = session.update("board.updateNotice", bv);
			session.commit();
		}catch(PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		}finally {
			
		}
		
		return cnt;
	}
	
	@Override
	public boolean checkBoardNo(int boardNo) {

		boolean isExist = false;
		
		SqlSession session = BoardUtil.getInstance(true);
		
		try {
			
			int cnt = session.selectOne("board.checkBoardNo", boardNo);
			
			if(cnt > 0) {
				isExist = true;
			}
		}catch(PersistenceException ex){
			session.rollback();
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return isExist;
		
	}
	
	@Override
	public int deleteNotice(int boardNo) {
		
		int cnt = 0;
		
		SqlSession session = BoardUtil.getInstance();
		
		try {
			
			cnt = session.delete("board.deleteNotice", boardNo);
			
			session.commit();
			
		}catch(PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return cnt;
	}
	
	@Override
	public List<BoardVO> findNotice(BoardVO bv) {
		
		List<BoardVO> boardList = Collections.EMPTY_LIST;
		
		SqlSession session = null;
		
		try {
			
			session = BoardUtil.getInstance(true);
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("boardNo", bv.getBoardNo());
			paramMap.put("writer", bv.getWriter());
			paramMap.put("date", bv.getWriteDate());
			paramMap.put("title", bv.getTitle());
			paramMap.put("content", bv.getContent());
				
			boardList = session.selectList("board.findNotice", paramMap);
				
		}catch(PersistenceException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return boardList;
		
	}
	
	@Override
	public boolean checkWriter(String writer) {
		
		boolean isExist = false;
		
		SqlSession session = BoardUtil.getInstance(true);
		
		try {
			
			int cnt = session.selectOne("board.checkWriter", writer);
			
			if(cnt > 0) {
				isExist = true;
			}
		}catch(PersistenceException ex){
			session.rollback();
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return isExist;
	}
	
	@Override
	public List<BoardVO> displayAll() {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		SqlSession session = BoardUtil.getInstance();
		
		try {
			
			boardList = session.selectList("board.displayAll");
			 
		}catch(PersistenceException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return boardList;
	}
	
}
