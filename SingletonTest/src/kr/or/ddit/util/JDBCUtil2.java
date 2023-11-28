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

/*
 *  db.properties 파일의 내용으로 DB정보를 설정하는 방법
 *  방법1) Properties객체 이용하기
 */

public class JDBCUtil2 {
	
	static Properties prop;
		
	static { 
		
		prop = new Properties(); //객체 생성
		
		try {
			//파일읽기를 수행할 FileInputStream 객체 생성하기
			FileInputStream fis = new FileInputStream("./res/db.properties");
		
			// 파일 내용 읽기
			prop.load(fis);
		
		} catch (IOException ex){
			ex.printStackTrace();
		}
		
		try {
			Class.forName(prop.getProperty("driver"));
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
					prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("password") );
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
