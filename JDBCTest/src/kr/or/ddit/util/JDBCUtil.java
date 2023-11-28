package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// JDBC드라이버를 로딩하고 Connection 객체를 생성하는 메소드를 제공하는 유틸 클래스
public class JDBCUtil {
	
	static { // 드라이버가 로딩 될 때 한번만 사용하고 싶음
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 완료...");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		}

	}
	
	// 커넥션 객체 생성하기
	public static Connection getConnection() {
		
		try {
			return  DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"pc07",
					"java" );
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * 자원반납
	 * @param conn
	 * @param pstmt
	 * @param stmt
	 * @param rs
	 */
	public static void close(
			Connection conn, 
			PreparedStatement pstmt, 
			Statement stmt, 
			ResultSet rs ) {
		
		if(rs != null) try {rs.close();}catch(SQLException ex) {}
		if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
		if(stmt != null) try {stmt.close();}catch(SQLException ex) {}
		if(conn != null) try {conn.close();}catch(SQLException ex) {}
	
	}

}
