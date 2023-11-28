package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

@WebServlet("/board/delete.do")
public class DeleteBoardController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		
		IBoardService boardService = BoardServiceImpl.getInstance();
		
		int cnt = boardService.removeNotice(boardNo);
		
		String msg = "";
		if (cnt > 0) {
			// 성공
			msg = "삭제성공";
		} else {
			// 실패
			msg = "삭제실패";
		}

		// 한번만 생성되는 것
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("msg", msg);

		resp.sendRedirect(req.getContextPath() + "/board/list.do");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
			
	}
}
