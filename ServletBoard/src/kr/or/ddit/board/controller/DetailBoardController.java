package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDaoforJDBC;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

@WebServlet(value = "/board/detail.do") // value는 생략이 가능하다.
public class DetailBoardController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String board_no = req.getParameter("board_no");
		
		// 1. 서비스 생성
		IBoardService bdService = BoardServiceImpl.getInstance();
		
		// 2. 회원정보 상세 조회
		BoardVO bv = bdService.getBoardone(board_no);

		// 3. 요청객체에 데이터 저장
		req.setAttribute("bv", bv);
		
		// 4. 상세 페이지로 전달
		req.getRequestDispatcher("/views/board/detail.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
