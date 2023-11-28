package kr.or.ddit.comm.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.comm.vo.AtchFileVO;

@WebServlet("/comm/download.do")
public class DownloadController extends HttpServlet{
	
	/* Content-Disposition 헤더
	 
	 1. 응답헤더에서 사용되는 경우... ex) 파일을 다운로드 하는 경우...
	 
	 Content-Disposition: inline(default)
	 Content-Disposition: attachment
	 Content-Disposition: attachment; filename="abc.jpg // 해당 파일명으로 파일 다운로드
	 
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String fileId = req.getParameter("fileId");
		String fileSn = req.getParameter("fileSn");
		
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setAtchFileId(Long.parseLong(fileId));
		atchFileVO.setFileSn(Integer.parseInt(fileSn));
		
		atchFileVO = fileService.getAtchFileDeatail(atchFileVO);
		
		String filePath = atchFileVO.getFileStreCours();
		String orignFileName = atchFileVO.getOrignlFileNm();
		String endcodedFileName = URLEncoder.encode(orignFileName, "UTF-8"); // 한글명 파일 처리를 위한 코딩
		
		// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기
//		resp.setContentType("image/jpg");
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename =\"" + endcodedFileName + "\""); 
	
		BufferedInputStream bis = new BufferedInputStream(
									new FileInputStream(filePath));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		int data = 0;
		
		while((data = bis.read()) != -1) {
			bos.write(data);
		}
		
		bis.close();
		bos.close();
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
	
}
