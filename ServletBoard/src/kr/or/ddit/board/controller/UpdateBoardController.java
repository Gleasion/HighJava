package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

@WebServlet("/board/update.do")
public class UpdateBoardController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String board_no = req.getParameter("board_no");

		// 1. 서비스 생성
		IBoardService bdService = BoardServiceImpl.getInstance();

		// 2. 게시글 상세 조회
		BoardVO bv = bdService.getBoardone(board_no);

		// 3. 요청객체에 데이터 저장
		req.setAttribute("bv", bv);

		// 4. 수정화면으로 전달
		req.getRequestDispatcher("/views/board/updateForm.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 요청 파라미터 정보 가져오기
		String board_no = req.getParameter("board_no");
		String board_writer = req.getParameter("board_writer");
		String board_title = req.getParameter("board_title");
		String board_content = req.getParameter("board_content");

		// 2. 서비스 객체 생성하기
		IBoardService bdService = BoardServiceImpl.getInstance();

		// 3. 회원정보 수정
		BoardVO bv = new BoardVO(board_writer, board_title, board_content);
		bv.setBoard_no(board_no);
		
		int cnt = bdService.modifyBoard(bv);

		String msg = "";
		if(cnt > 0) {
			// 성공
			msg = "성공";
		}else {
			// 실패
			msg = "실패";
		}
		
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("msg", msg);
		
		// 4. 목록 조회화면으로 이동하기
//		req.getRequestDispatcher("/member/list.do").forward(req, resp);
		resp.sendRedirect(req.getContextPath() + "/board/list.do");

	}
}
