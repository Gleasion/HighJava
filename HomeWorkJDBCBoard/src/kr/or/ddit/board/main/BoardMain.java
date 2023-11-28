package kr.or.ddit.board.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import kr.or.ddit.Util.JDBCUtil;

/*위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
 
------------------------------------------------------------

게시판 테이블 구조 및 시퀀스

create table jdbc_board(
    board_no number not null,  -- 번호(자동증가)
    board_title varchar2(100) not null, -- 제목
    board_writer varchar2(50) not null, -- 작성자
    board_date date not null,   -- 작성날짜
    board_content clob,     -- 내용
    constraint pk_jdbc_board primary key (board_no)
);
create sequence board_seq
    start with 1   -- 시작번호
    increment by 1; -- 증가값
		
----------------------------------------------------------

// 시퀀스의 다음 값 구하기
//  시퀀스이름.nextVal
*/
		
public class BoardMain {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in);
	
	// 메뉴를 출력하는 메소드
	public void displayMenu() {
		System.out.println();
		System.out.println("--------------------------------");
		System.out.println("============작 업 선 택=============");
		System.out.println("\t 1. 전체 목록 출력");
		System.out.println("\t 2. 새글 작성");
		System.out.println("\t 3. 수 정");
		System.out.println("\t 4. 삭 제");
		System.out.println("\t 5. 검 색");
		System.out.println("\t 6. 프로그램 종료");
		System.out.println("--------------------------------");
		System.out.print("원하는 작업 번호 선택 >> ");
		
	}
	
	// 프로그램 시작 메소드
	public void start() {
		
		int choice = 0;
		
		do {
			displayMenu(); // 메뉴 출력
			
			
			try {
				choice = scan.nextInt(); // 메뉴번호 입력 받기
				scan.nextLine();
			}catch(InputMismatchException e) {
				System.out.println("올바른 값이 아닙니다. 다시 입력하세요.");
				scan.nextLine();
				continue;
			}
			
			switch(choice) {
				case 1: // 전체 목록 출력
					DisplayAll();
					break;
				case 2: // 새글 작성
					insertNotice();
					break;
				case 3: // 수정
					updateNotice();
					break;
				case 4: // 삭제
					deleteNotice();
					break;
				case 5: // 검색
					findNotice();
					break;
				case 6: // 프로그램 종료
					System.out.println("프로그램을 종료합니다.");
					break;
				default :
					System.out.println("잘못된 입력 값입니다. 다시 입력하세요.");
				}
		}while(choice != 6);
	}
	
	// 전체 게시글을 조회하는 메소드
	private void DisplayAll() {
		
		System.out.println();
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("No.    작성자\t\t작성일\t\t   제목   \t\t내용");
		System.out.println("-------------------------------------------------------------------------");	
		
		try {
			 conn = JDBCUtil.getConnection();
			 
			 String sql = "select * from jdbc_board";
			 
			 stmt = conn.createStatement();
			 
			 rs = stmt.executeQuery(sql);
			 
			 
			 
			 while(rs.next()) {
				 String boardNo = rs.getString("board_no");
				 String writer = rs.getString("board_writer");
				 String writeDate = rs.getString("board_date");
				 String title = rs.getString("board_title");
				 String content = rs.getString("board_content");
				 
				 System.out.println(" " + boardNo + ".   " + writer + "\t" + writeDate + "\t"
							+ title + "\t\t" + content);
			 }
			 System.out.println("--------------------------------------------------------------------");
			 System.out.println("게시글 전체 조회가 완료되었습니다.");
		
			 
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, stmt, rs);
		}
	}

	// 게시글 검색 메소드
	private void findNotice() {
		
		boolean isExist = false;
		
		String writer;
		
		do {
			System.out.println();
			System.out.println("검색할 작성자 이름을 입력하세요.");
			System.out.print("작성자 이름 >> ");
			
			writer = scan.next();
			
			isExist = checkWriter(writer);
			
			if(!isExist) {
				System.out.println("작성자가 " + writer + "인 글이 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		}while(!isExist);
		
		System.out.println();
		System.out.println("----------------------------------------------------------");
		System.out.println("No.    작성자\t\t작성일\t\t   제목   \t\t내용");
		System.out.println("----------------------------------------------------------");		
				
				
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = "select * from jdbc_board where board_writer = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			rs = pstmt.executeQuery();
			
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				String boardNo = rs.getString("board_no");
				writer = rs.getString("board_writer");
				String writeDate = rs.getString("board_date");
				String title = rs.getString("board_title");
				String content = rs.getString("board_content");
				// ToChar 사용할 거면 rs.getString으로 날짜 가져오기
				
				System.out.println(" " + boardNo + ".   " + writer + "\t" + writeDate + "\t"
						+ title + "\t\t" + content);
			}
			System.out.println("----------------------------------------------------------");
			System.out.println("게시글 검색이 완료되었습니다.");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, stmt, rs);
		}
		
		
	}

	// 게시글 삭제 메소드
	private void deleteNotice() {
		
		boolean isExist = false;
		
		int boardNo;
		
		do {
			System.out.println();
			System.out.println("삭제할 게시글 번호를 입력하세요.");
			System.out.println("게시판 번호 >> ");
			
			boardNo = scan.nextInt();

			scan.nextLine();
			
			isExist = checkBoardNo(boardNo);
			
			if(!isExist) {
				System.out.println("게시판 번호가 " + boardNo + "인 글이 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		}while(!isExist);
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = " delete from jdbc_board where board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0){
				System.out.println(boardNo + "번 게시글 삭제에 성공하였습니다.");
			}else {
				System.out.println(boardNo + "번 게시글 삭제에 실패하였습니다.");
			}
			System.out.println("게시글 삭제가 완료되었습니다.");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, stmt, rs);
		}
		
	}

	// 게시글 수정 메소드
	private void updateNotice() {
		
		boolean isExist = false;
		
		int boardNo;
		
		do {
			System.out.println();
			System.out.println("수정할 게시판 번호를 입력하세요.");
			System.out.print("게시판 번호 >> ");
			
			boardNo = scan.nextInt();

			scan.nextLine();
			
			isExist = checkBoardNo(boardNo);
			
			if(!isExist) {
				System.out.println("게시판 번호가 " + boardNo + "인 글이 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		}while(!isExist);
		
		System.out.print("작성자 >> ");
		String writer = scan.next();
		
		scan.nextLine();
		
		String title;
		do {
		    System.out.print("제목 >> ");
		    title = scan.nextLine();
		} while (title.isEmpty());
		
		String content = "";
		while(content.isEmpty()) {
			System.out.print("내용 >> ");			
			content = scan.nextLine();
		}
			
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = " update jdbc_board " 
						+ " set board_title = ? "
						+ " , board_writer = ? "
						+ " , board_date = SYSDATE "
						+ " , board_content = ? " 
						+ " where board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			pstmt.setInt(4, boardNo);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(boardNo + "번 게시글 수정 작업에 성공하였습니다.");
			}else {
				System.out.println(boardNo + "번 게시글 수정 작업 실패하였습니다.");
			}
			System.out.println("게시글 수정이 완료되었습니다.");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, stmt, rs);
		}
		
	}

	// 새글 작성 메소드
	private void insertNotice() {
		
//		boolean isExist = false;
//		
//		String writer = "";
//		
//		do {
//		System.out.println();
//			System.out.println("작성자를 입력하세요.");
//			System.out.println("작성자 이름 >> ");
//		
//			writer = scan.next();
//			
//			isExist = checkWriter(writer);		
//			if(isExit) {
//				System.out.println(writer + "는 이미 존재합니다. 작성자를 다시 입력하세요.");
//			}
//		
//		}while(isExist);
		
		System.out.print("작성자 >> ");
		String writer = scan.next();
		
		scan.nextLine();
		
		String title;
		do {
		    System.out.print("제목 >> ");
		    title = scan.nextLine();
		} while (title.isEmpty());
		
		String content = "";
		while(content.isEmpty()) {
			System.out.print("내용 >> ");			
			content = scan.nextLine();
		}
				
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " Insert into jdbc_board "
					+ "( board_no, board_title, board_writer, board_date, board_content) " 
					+ " values(board_seq.nextval,?,?,SYSDATE,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(writer + " 님의 게시글 등록에 성공하였습니다.");
			}else {
				System.out.println(writer + " 님의 게시글 등록 실패하였습니다.");
			}
			System.out.println("게시글 등록이 완료되었습니다.");
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, pstmt, stmt, rs);
		}
		
	}
	
	/**
	 * 게시판 번호의 존재 유무를 검사하는 메소드
	 * @param boardNo 게시판 번호 확인
	 * @return 게시판 번호가 존재하면 true, 존재하지 않으면 false 리턴
	 */
	private boolean checkBoardNo(int boardNo) {
		
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
	
	/**
	 * 작성자의 글 존재 여부 체크
	 * @param writer 확인할 작성자 이름
	 * @return 작성자가 존재하면 true, 존재하지 않으면 false 리턴
	 */
	private boolean checkWriter(String writer) {
		
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

	public static void main(String[] args) {
		BoardMain board = new BoardMain();
		board.start();

	}

}
