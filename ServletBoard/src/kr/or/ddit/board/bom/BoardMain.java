package kr.or.ddit.board.bom;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;

public class BoardMain {

	private Scanner scan;

	IBoardService bdService;

	public BoardMain() {
		scan = new Scanner(System.in);
		bdService = BoardServiceImpl.getInstance();
	}

	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 게시글 작성");
		System.out.println("  2. 게시글 삭제");
		System.out.println("  3. 게시글 수정");
		System.out.println("  4. 전체 게시글 검색");
		System.out.println("  5. 게시글 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	/**
	 * 프로그램 시작메서드
	 */
	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch (choice) {
			case 1: // 자료 입력
				insertBoard();
				break;
			case 2: // 자료 삭제
				deleteBoard();
				break;
			case 3: // 자료 수정
				updateBoard();
				break;
			case 4: // 전체 자료 출력
				displayAllBoard();
				break;
			case 5: // 게시글 검색
				searchBoard();
				break;
			case 6: // 작업 끝
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 6);
	}

	private void searchBoard() {
		scan.nextLine();

		System.out.print("게시글 번호 >> ");
		String board_no = scan.nextLine();

		System.out.print("작성자 이름 >> ");
		String board_writer = scan.nextLine();

		System.out.print("게시글 이름 >> ");
		String board_title = scan.nextLine();

		System.out.print("게시글 내용 >> ");
		String board_content = scan.nextLine();

		System.out.println();
		System.out.println("────────────────────────────────────────────────────────────────────────────");
		System.out.printf("%3s \t          %s    \t %8s \t %15s \t %20s \n", "번 호", "시 간", "작 성 자", "제 목", "내 용");
		System.out.println("────────────────────────────────────────────────────────────────────────────");

		BoardVO bv = new BoardVO(board_writer, board_title, board_content);
		bv.setBoard_no(board_no);
		
		List<BoardVO> bdList = bdService.getBoard(bv);

		if (bdList.size() == 0) {
			System.out.println("게시글 정보가 존재하지 않습니다.");
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			for (BoardVO bv1 : bdList) {
				System.out.printf("%3s \t %s \t %8s \t %15s \t %20s \n", bv1.getBoard_no(), sdf.format(bv1.getRegDt()),
						bv1.getBoard_writer(), bv1.getBoard_title(), bv1.getBoard_content());
			}

			System.out.println("────────────────────────────────────────────────────────────────────────────");
			System.out.println("출력 작업 끝...");
		}

	}

	/**
	 * 회원정보를 삭제하기 위한 메서드
	 */
	private void deleteBoard() {

		System.out.println();
		System.out.println("삭제할 게시글 번호를 입력하세요.");
		System.out.print("게시글 번호 >> ");

		String board_no = scan.next();

		int cnt = bdService.removeBoard(board_no);

		if (cnt > 0) {
			System.out.println(board_no + " 게시글 삭제 작업 성공!");
		} else {
			System.out.println(board_no + " 게시글 삭제 작업 실패!!!");
		}

		System.out.println("게시글 삭제작업 완료...");
	}

	/**
	 * 회원정보를 수정하기 위한 메서드
	 */
	private void updateBoard() {

		boolean isExist = false;

		String board_no = "";

		do {
			System.out.println();
			System.out.println("수정할 게시글 작성자를 입력하세요.");
			System.out.print("게시글 번호 >> ");

			board_no = scan.next();

			isExist = bdService.checkBoard(board_no);

			if (!isExist) {
				System.out.println(board_no + "번 게시글은 존재하지 않습니다.");
				System.out.println("다시 입력해 주세요.");
			}

		} while (!isExist);

		scan.nextLine();

		System.out.print("작성자 이름 >> ");
		String board_writer = scan.nextLine();

		System.out.print("게시글 이름 >> ");
		String board_title = scan.nextLine();

		System.out.print("게시글 내용 >> ");
		String board_content = scan.nextLine();

		BoardVO bv = new BoardVO(board_writer, board_title, board_content);
		bv.setBoard_no(board_no);

		int cnt = bdService.modifyBoard(bv);

		if (cnt > 0) {
			System.out.println(board_no + " 게시글 수정 작업 성공!");
		} else {
			System.out.println(board_no + " 게시글 수정 작업 실패!!!");
		}

		System.out.println("게시글 수정작업 완료...");
	}

	/**
	 * 모든 회원정보를 출력하기 위한 메서드
	 */
	private void displayAllBoard() {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println(" 번호\t     시간\t\t작성자\t제 목\t내 용");
		System.out.println("----------------------------------------------");

		List<BoardVO> bdList = bdService.getAllBoard();

		if (bdList.size() == 0) {
			System.out.println("게시글 정보가 존재하지 않습니다.");
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			for (BoardVO bv : bdList) {
				System.out.println(bv.getBoard_no() + "\t" + sdf.format(bv.getRegDt()) + "\t" + bv.getBoard_writer()
						+ "\t" + bv.getBoard_title() + "\t" + bv.getBoard_content());
			}
			
			System.out.println("----------------------------------------------");
			System.out.println("출력 작업 끝...");
		}

	}

	/**
	 * 회원정보를 등록하기 위한 메서드
	 */
	private void insertBoard() {

		boolean isExist = false;

		System.out.println();
		System.out.println("게시글 작성 페이지입니다.");

		scan.nextLine();
		
		System.out.print("작성자 이름 >> ");
		String board_writer = scan.nextLine();

		System.out.print("게시글 이름 >> ");
		String board_title = scan.nextLine();

		System.out.print("게시글 내용 >> ");
		String board_content = scan.nextLine();

		Date now = new Date();

		BoardVO bv = new BoardVO(board_writer, board_title, board_content);
		bv.setRegDt(now);

		int cnt = bdService.registerBoard(bv);

		if (cnt > 0) {
			System.out.println(board_writer + "게시글 추가 작업 성공!");
		} else {
			System.out.println(board_writer + "게시글 추가 작업 실패!!!");
		}

		System.out.println("게시글 추가 작업 완료...");

	}

	public static void main(String[] args) {
		BoardMain bdObj = new BoardMain();
		bdObj.start();
	}

}
