package kr.or.ddit.memeber.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImplForJDBC implements IMemberDao {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public int insertMemeber(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = " Insert into mymember " 
						+ " (mem_id, mem_name, mem_tel, mem_addr) " 
						+ " values(?,?,?,?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemId());
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());
			
			cnt = pstmt.executeUpdate(); // insert, update, delete
			// pstmt.executeQuery() // select를 위함 resultQuery

		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, pstmt, stmt, rs);
		}
		
		return cnt;
		
	}

	@Override
	public boolean checkMember(String memId) {
		
		boolean isExist = false;
		
		try {
			conn =JDBCUtil3.getConnection();
			
			String sql =  " select count(*) as cnt from mymember " 
					+ " where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			while(rs.next()) {
				cnt = rs.getInt("CNT");
			}
			
			if(cnt > 0) {
				isExist = true;
			}
								
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, pstmt, stmt, rs);
		}
				
		return isExist;
		
	}

	@Override
	public List<MemberVO> getAllMember() {
		
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from mymember";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			while(rs.next()) {
				
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				Date regDt = rs.getTimestamp("reg_dt");
				// ToChar 사용할 거면 rs.getString으로 날짜 가져오기
				
				MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
				mv.setRegDt(regDt);
				
				memList.add(mv);
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, pstmt, stmt, rs);
		}
		
		return memList;
		
	}

	@Override
	public int updateMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = " UPDATE mymember "
						+ " SET mem_name = ? "
						+ "    , mem_tel = ? "
						+ "    , mem_addr = ? "
						+ " WHERE mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());
			
			cnt = pstmt.executeUpdate();
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, pstmt, stmt, rs);
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		
		try {
			
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from mymember where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			cnt = pstmt.executeUpdate();
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, pstmt, stmt, rs);
		}
		
		return cnt;
	}

}
