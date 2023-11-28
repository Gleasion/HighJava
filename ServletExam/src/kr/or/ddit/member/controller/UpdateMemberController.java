package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

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

@MultipartConfig
@WebServlet("/member/update.do")
public class UpdateMemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String memId = req.getParameter("memId");

		// 1. 서비스 생성
		IMemberService memService = MemberServiceImpl.getInstance();
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		// 2. 회원정보 상세 조회
		MemberVO mv = memService.getMember(memId);

		if(mv.getAtchFileId() != -1) {
	    	  
	    	  AtchFileVO atchFileVO = new AtchFileVO();
	    	  atchFileVO.setAtchFileId(mv.getAtchFileId());
	      
	    	  List<AtchFileVO> atchFileList = fileService.getAtchFileList(atchFileVO);
	      
	    	  req.setAttribute("atchFileList", atchFileList);
	      }
		
		// 3. 요청객체에 데이터 저장
		req.setAttribute("mv", mv);

		// 4. 수정화면으로 전달
		req.getRequestDispatcher("/views/member/updateForm.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		String atchFileId = req.getParameter("atchFileId");

		IMemberService memberService = MemberServiceImpl.getInstance();
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		AtchFileVO atchFileVO = null;
		
		atchFileVO = fileService.saveAtchFileList(req); // 첨부파일 저장
		
		MemberVO mv = new MemberVO(memId, memName, memTel, memAddr);

		if(atchFileVO == null) { // 새로운 첨부파일이 존재하지 않으면...
			mv.setAtchFileId(Long.parseLong(atchFileId));
		}else { // 새로운 첨부파일이 존재하면
			mv.setAtchFileId(atchFileVO.getAtchFileId());
		}
		
		int cnt = memberService.modifyMember(mv);

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

		resp.sendRedirect(req.getContextPath() + "/member/list.do");

	}
}