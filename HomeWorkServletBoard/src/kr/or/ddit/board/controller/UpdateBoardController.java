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
import kr.or.ddit.board.vo.BoardVO;

@WebServlet("/board/update.do")
public class UpdateBoardController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		
		IBoardService boardService = BoardServiceImpl.getInstance();
		
		BoardVO bv = boardService.getBoard(boardNo);
		
		req.setAttribute("bv", bv);
		
		req.getRequestDispatcher("/views/board/updateForm.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int boardNo = Integer.parseInt(req.getParameter("boardNo")); // 되는지 봐야함
		String writer = req.getParameter("writer");
		String writeDate = req.getParameter("writeDate");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		IBoardService boardService = BoardServiceImpl.getInstance();
		
		BoardVO bv = new BoardVO(boardNo, writer, writeDate, title, content);
		
		int cnt = boardService.modifyNotice(bv);
		
		String msg = "";
		if (cnt > 0) {
			msg = "수정성공";
		}else {
			msg = "수정실패";
		}
		
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("msg", msg);
		
		resp.sendRedirect(req.getContextPath() + "/board/list.do");
	
	
	}
}
