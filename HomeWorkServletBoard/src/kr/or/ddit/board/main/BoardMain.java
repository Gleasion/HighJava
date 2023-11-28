package kr.or.ddit.board.main;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

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
	
	private Scanner scan;
	
	IBoardService boardService;
	
	public BoardMain() {
		scan = new Scanner(System.in);
		boardService = BoardServiceImpl.getInstance();
	}
	

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
					displayAll();
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
	private void displayAll() {

		List<BoardVO> boardList = boardService.getDisplayAll();
		
		if(boardList.size() == 0) {
			System.out.println("게시글이 존재하지 않습니다.");
		}else {
			
			System.out.println();
			System.out.println();
			System.out.println("----------------------------------------------------------------------");
			System.out.println("No.    작성자\t\t작성일\t\t   제목   \t\t\t내용");
			System.out.println("----------------------------------------------------------------------");		
			
			for(BoardVO bv : boardList) {
				
				System.out.println(" " + bv.getBoardNo() + ".   " + bv.getWriter() 
								+ "\t" + bv.getWriteDate() + "\t"
								+ bv.getTitle() + "\t\t" + bv.getContent());
			}
		}
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("게시글 전체 조회가 완료되었습니다.");
		
	}

	/** 게시글 검색 메소드
	 검색할 게시글번호, 작성자이름, 제목, 내용등을 입력하면 입력한 정보만 사용하여 검색하는 기능
	 주소는 입력한 값이 포함만 되어도 검색되도록 함
	(입력을 하지 않을 데이터는 엔터키로 다음 입력으로 넘김) 
	 */
	private void findNotice() {
		
		System.out.println();
		System.out.println("검색할 게시글 정보를 입력하세요.");
		System.out.print("게시글 번호 >> ");
		
		int boardNo = scan.nextInt();
		
		scan.nextLine();
		
		System.out.print("작성자 이름 >> ");
		String writer = scan.nextLine().trim();

		System.out.print("게시글 제목 >> ");
		String title = scan.nextLine().trim();
		
		System.out.print("게시글 내용 >> ");
		String content = scan.nextLine().trim();
		
		if(boardNo == 0 && writer.isEmpty() && title.isEmpty() && content.isEmpty()) {
			System.out.println("해당 게시글이 존재하지 않습니다.");
			System.out.println("다시 검색해주세요.");
			findNotice();
		}
		
		BoardVO bv = new BoardVO(boardNo, writer, title, content);
		System.out.println("bv : " + bv);
		
		List<BoardVO> boardList = boardService.searchNotice(bv);
		
		if(boardList.size() == 0) {
			System.out.println("해당 게시글이 존재하지 않습니다.");
			System.out.println("다시 검색해주세요.");			
		}else {
			System.out.println();
			System.out.println("----------------------------------------------------------------------");
			System.out.println("No.    작성자\t\t작성일\t\t   제목   \t\t\t내용");
			System.out.println("----------------------------------------------------------------------");		
			
			for(BoardVO bv2 : boardList) {
				
				System.out.println(" " + bv2.getBoardNo() + ".   " + bv2.getWriter() + "\t" 
									+ bv2.getWriteDate() + "\t"
									+ bv2.getTitle() + "\t\t" + bv2.getContent());

			}
		}
		
		System.out.println("----------------------------------------------------------------------");
		System.out.println("게시글 검색 작업이 완료되었습니다.");
		
	}

	// 게시글 삭제 메소드
	private void deleteNotice() {
		
		boolean isExist = false;
		
		int boardNo;
		
		do {
			System.out.println();
			System.out.println("삭제할 게시글 번호를 입력하세요.");
			System.out.print("게시판 번호 >> ");
			
			boardNo = scan.nextInt();

			scan.nextLine();
			
			isExist = boardService.checkBoardNo(boardNo);
			
			if(!isExist) {
				System.out.println("게시판 번호가 " + boardNo + "인 글이 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		}while(!isExist);
		
		int cnt = boardService.removeNotice(boardNo);
		
		if(cnt > 0){
			System.out.println(boardNo + "번 게시글 삭제에 성공하였습니다.");
		}else {
			System.out.println(boardNo + "번 게시글 삭제에 실패하였습니다.");
		}
		System.out.println("게시글 삭제가 완료되었습니다.");
		
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
			
			isExist = boardService.checkBoardNo(boardNo);
			
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
			
		BoardVO bv = new BoardVO(boardNo, writer, title, content);
		
		int cnt = boardService.modifyNotice(bv);
		
		if(cnt > 0) {
			System.out.println(boardNo + "번 게시글 수정 작업에 성공하였습니다.");
			System.out.println("게시글 수정이 완료되었습니다.");
		}else {
			System.out.println(boardNo + "번 게시글 수정 작업 실패하였습니다.");
			System.out.println("다시 시도해주세요.");
		}
		
		
	}

	// 새글 작성 메소드
	private void insertNotice() {
		
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
		
		// 다시 확인
		BoardVO bv = new BoardVO(writer, title, content);
		
		int cnt = boardService.registerNotice(bv);
		
		if(cnt > 0) {
			System.out.println(writer + " 님의 게시글 등록에 성공하였습니다.");
		}else {
			System.out.println(writer + " 님의 게시글 등록 실패하였습니다.");
		}
		System.out.println("게시글 등록이 완료되었습니다.");
		
	}
	

	public static void main(String[] args) {
		BoardMain board = new BoardMain();
		board.start();

	}

}
