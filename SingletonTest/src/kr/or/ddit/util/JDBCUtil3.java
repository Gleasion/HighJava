package kr.or.ddit.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import sun.util.BuddhistCalendar;

/*
 *  db.properties 파일의 내용으로 DB정보를 설정하는 방법
 *  방법2) ResourceBundle 객체 이용하기
 */

public class JDBCUtil3 {
	
	static ResourceBundle bundle;
		
	static { 
		
		bundle = ResourceBundle.getBundle("db");
		
		try {
			Class.forName(bundle.getString("driver"));
			System.out.println("드라이버 로딩 완료...");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 커넥션 객체 생성하기
	 * @return
	 */
	public static Connection getConnection() {
		
		try {
			return  DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("password") );
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
