package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.MyBatisUtil;

public class BoardDaoImpl implements IBoardDaoforJDBC {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static IBoardDaoforJDBC bdDao;

	public static IBoardDaoforJDBC getInstance() {
		if (bdDao == null)
			bdDao = new BoardDaoImpl();
		return bdDao;
	}

	@Override
	public int insertBoard(BoardVO bv) {

		int cnt = 0;

		SqlSession session = MyBatisUtil.getInstance(true);

		try {

			cnt = session.insert("board.insertBoard", bv);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return cnt;
	}

	@Override
	public boolean checkBoard(String bdNo) {
		boolean isExist = false;

		SqlSession session = MyBatisUtil.getInstance(true);

		try {

			int cnt = session.selectOne("Board.checkBoard", bdNo);

			if (cnt > 0) {
				isExist = true;
			}

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return isExist;

	}

	@Override
	public List<BoardVO> getAllBoard() {

		List<BoardVO> bdList = new ArrayList<BoardVO>();

		SqlSession session = MyBatisUtil.getInstance(true);

		try {

			bdList = session.selectList("board.getAllBoard");

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return bdList;

	}

	@Override
	public int updateBoard(BoardVO bv) {

		int cnt = 0;

		SqlSession session = MyBatisUtil.getInstance(true);
		try {

			cnt = session.update("board.updateBoard", bv);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(String bdNo) {

		int cnt = 0;

		SqlSession session = MyBatisUtil.getInstance(true);

		try {
			cnt = session.delete("board.deleteBoard", bdNo);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> getBoard(BoardVO bv) {

		List<BoardVO> bdList = Collections.emptyList();
		
		SqlSession session = MyBatisUtil.getInstance(true);

		try {
			
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("board_no", bv.getBoard_no());
			paramMap.put("board_writer", bv.getBoard_writer());
			paramMap.put("board_title", bv.getBoard_title());
			paramMap.put("board_content", bv.getBoard_content());
			
			bdList = session.selectList("board.getBoard", paramMap);

			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return bdList;
	
	}

	@Override
	public BoardVO getBoardone(String board_no) {
		
		SqlSession session = MyBatisUtil.getInstance(true);

		BoardVO bd = null;
		
		try {
			
			bd = session.selectOne("board.getBoardone", board_no);

		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return bd;
	
	}
	
}
