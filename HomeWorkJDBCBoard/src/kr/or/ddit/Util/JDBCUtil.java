package kr.or.ddit.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

// DB 연결을 위한 JDBCUtil
// ResourceBundle 객체 이용
public class JDBCUtil {
	
	static ResourceBundle bundle;
	
	static {
		
		bundle = ResourceBundle.getBundle("db");
		
		try {
			Class.forName(bundle.getString("driver"));
			System.out.println("드라이버 로딩 완료...");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 커넥션 객체 생성
	 * @return
	 */
	public static Connection getConnection() {
		
		try {
			
			return DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("password") );
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 자원 반납
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
		
		if(rs != null) try {rs.close();}catch (SQLException e) {}
		if(pstmt != null) try {pstmt.close();}catch (SQLException e) {}
		if(stmt != null) try {stmt.close();}catch (SQLException e) {}
		if(conn != null) try {conn.close();}catch (SQLException e) {}
		
	}
		
	
}
