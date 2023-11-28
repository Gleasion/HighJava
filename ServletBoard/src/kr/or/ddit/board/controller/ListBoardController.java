package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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


@WebServlet(value = "/board/list.do")
public class ListBoardController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 서비스 객체 생성하기
		IBoardService bdService = BoardServiceImpl.getInstance();
		
		// 2. 회원정보 목록 조회하기
		List<BoardVO> bdList = bdService.getAllBoard();
		
		// 3. jsp가 사용할 수 있도록 요청객체에 저장하기
		req.setAttribute("bdList", bdList);
		
		// 4. jsp화면으로 포워딩 처리하기
		RequestDispatcher rd = req.getRequestDispatcher("/views/board/list.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
