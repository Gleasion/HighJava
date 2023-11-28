package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDaoforJDBC;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;


@WebServlet(value = "/board/insert.do")
public class InsertBoardController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher("/views/board/insertForm.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 요청 파라미터 정보 가져오기
		String board_writer = req.getParameter("board_writer");
		String board_title = req.getParameter("board_title");
		String board_content = req.getParameter("board_content");
		
		// 2. 서비스 객체 생성하기
		IBoardService bdService = BoardServiceImpl.getInstance();
		
		// 3. 회원정보 등록
		BoardVO bv = new BoardVO(board_writer, board_title, board_content);
		Date now = new Date();
		bv.setRegDt(now);
		
		int cnt = bdService.registerBoard(bv);
		
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
//		req.getRequestDispatcher("/board/list.do").forward(req, resp);
		resp.sendRedirect(req.getContextPath() + "/board/list.do");
	}
}
