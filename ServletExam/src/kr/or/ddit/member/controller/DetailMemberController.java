package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.comm.vo.AtchFileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/detail.do") // value만 있는 경우에는 'value=' 생략 가능
public class DetailMemberController extends HttpServlet {
   
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
      
      // 4. 상세 페이지로 전달
      req.getRequestDispatcher("/views/member/detail.jsp").forward(req, resp);
      
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
   
}