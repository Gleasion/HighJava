package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.Util.JDBCUtil;
import kr.or.ddit.board.vo.BoardVO;

public class BoardDaoImplForJDBC implements IBoardDao{
	
	private static IBoardDao boardDao;
	
	public static IBoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImplForJDBC();
		}
		
		return boardDao;
	}
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public int insertNotice(BoardVO bv) {
		
		int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " Insert into jdbc_board "
						+ "( board_no, board_title, board_writer, board_date, board_content) " 
						+ " values(board_seq.nextval,?,?,SYSDATE,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getTitle());
			pstmt.setString(2, bv.getWriter());
			pstmt.setString(3, bv.getContent());
			
			cnt = pstmt.executeUpdate();
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, stmt, rs);
		}
		
		return cnt;
		
	}
	
	@Override
	public int updateNotice(BoardVO bv) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = " update jdbc_board " 
						+ " set board_title = ? "
						+ " , board_writer = ? "
						+ " , board_date = SYSDATE "
						+ " , board_content = ? " 
						+ " where board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bv.getTitle());
			pstmt.setString(2, bv.getWriter());
			pstmt.setString(3, bv.getContent());
			pstmt.setInt(4, bv.getBoardNo());
			
			cnt = pstmt.executeUpdate();
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, stmt, rs);
		}
		
		return cnt;
	}
	
	@Override
	public boolean checkBoardNo(int boardNo) {

		boolean isExist = false;
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = " select count(*) as cnt from jdbc_board " 
					+ " where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				isExist = true;
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
			
		}finally {
			JDBCUtil.close(conn, pstmt, stmt, rs);
		}
		
		return isExist;
		
	}
	
	@Override
	public int deleteNotice(int boardNo) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = " delete from jdbc_board where board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, stmt, rs);
		}
		
		return cnt;
	}
	
	@Override
	public List<BoardVO> findNotice(String writer) {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = "select * from jdbc_board where board_writer = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			rs = pstmt.executeQuery();
	
			while(rs.next()) {
				
				int boardNo = rs.getInt("board_no");
				writer = rs.getString("board_writer");
				String writeDate = rs.getString("board_date");
				String title = rs.getString("board_title");
				String content = rs.getString("board_content");
				
				BoardVO bv = new BoardVO(boardNo, writer, writeDate, title, content);
				 
				boardList.add(bv);
				
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, stmt, rs);
		}
		
		return boardList;
		
	}
	
	@Override
	public boolean checkWriter(String writer) {
		
		boolean isExist = false;
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = " select count(*) as cnt from jdbc_board " 
						+ " where board_writer = ?";				
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			rs = pstmt.executeQuery();

			int cnt = 0;
			
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				isExist = true;
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
			
		}finally {
			JDBCUtil.close(conn, pstmt, stmt, rs);
		}
		
		return isExist;
		
	}
	
	@Override
	public List<BoardVO> displayAll() {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			 conn = JDBCUtil.getConnection();
			 
			 String sql = "select * from jdbc_board";
			 
			 stmt = conn.createStatement();
			 
			 rs = stmt.executeQuery(sql);
			 
			 while(rs.next()) {
				 int boardNo = rs.getInt("board_no");
				 String writer = rs.getString("board_writer");
				 String writeDate = rs.getString("board_date");
				 String title = rs.getString("board_title");
				 String content = rs.getString("board_content");
				 
				 BoardVO bv = new BoardVO(boardNo, writer, writeDate, title, content);
				 
				 boardList.add(bv);
				 
			 }
			 
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, stmt, rs);
		}
		
		return boardList;
	}
	
}
