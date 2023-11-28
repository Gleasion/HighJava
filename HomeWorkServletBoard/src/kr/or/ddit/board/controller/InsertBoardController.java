package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

@WebServlet("/board/insert.do")
public class InsertBoardController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher("/views/board/insertForm.jsp");
		rd.forward(req, resp);
		
	}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 요청 파라미터 정보 가져오기
//		int boardNo = Integer.parseInt(req.getParameter("boardNo")); // 되는지 봐야함
		String writer = req.getParameter("writer");
//		String writeDate = req.getParameter("writeDate");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		// 2. 서비스 객체 생성하기
		IBoardService boardService = BoardServiceImpl.getInstance();
		
		// 3. 회원정보 등록
//		BoardVO bv = new BoardVO(boardNo, writer, writeDate, title, content);
		BoardVO bv = new BoardVO(writer, title, content);
		
		int cnt = boardService.registerNotice(bv);
		
		String msg = "";
		if (cnt > 0) {
			msg = "등록성공";
		}else {
			msg = "등록실패";
		}
		
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("msg", msg);
		
		// 4. 목록조회 화면으로 이동
		resp.sendRedirect(req.getContextPath() + "/board/list.do");
	
	}
	
	
}
