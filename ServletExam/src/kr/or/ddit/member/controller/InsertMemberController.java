package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.comm.vo.AtchFileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

/**
 * 회원을 등록하는 메소드
 */
@MultipartConfig
@WebServlet(value = "/member/insert.do")
public class InsertMemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher("/views/member/insertForm.jsp");
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// req.setCharacterEncoding("UTF-8"); // Filter 실행하면 굳이 필요 없음

		// 1. 요청 파라미터 정보 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");

		// 2. 서비스 객체 생성하기
		IMemberService memService = MemberServiceImpl.getInstance();
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();

		AtchFileVO atchFileVO = new AtchFileVO();
		
		// 파일 아이디 담겨 있음
		atchFileVO = fileService.saveAtchFileList(req);
		
		// 3. 회원정보 등록
		MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);
		
		if(atchFileVO != null) { // 널체크, 첨부파일 추가 안하는 경우도 고려
			mv.setAtchFileId(atchFileVO.getAtchFileId()); // 널이 아닐 때 아이디 담아옴
		}
		
		int cnt = memService.registerMember(mv);

		String msg = "";
		if (cnt > 0) {
			// 성공
			msg = "성공";
		} else {
			// 실패
			msg = "실패";
		}

		// 한번만 생성되는 것
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("msg", msg);

		// 4. 목록 조회화면으로 이동
//		 req.getRequestDispatcher("/member/list.do").forward(req, resp);

		// redirect: insert.do에서 제출하기를 눌러 list.do로 이동하면 url도 바뀌도록 설정
		resp.sendRedirect(req.getContextPath() + "/member/list.do");

	}

}
