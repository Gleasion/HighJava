package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3, maxFileSize = 1024 * 1024 * 40)
@WebServlet("/basic/upload2.do")
public class UploadController2 extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 멀티파트 파싱 전에 파라미터 정보 가져와 보기
		System.out.println("멀티파트 파싱 전 => " + req.getParameter("sender"));
	
		try {
			String fileName = "";
			for(Part part : req.getParts()){
				
				//fileName = getFileName(part);
				fileName = part.getSubmittedFileName();
				
				// 폼필드가 아니거나 파일이 선택되어 있는 경우...
				if(fileName != null && !fileName.equals("")) {
					part.write("e:/D_Other/" + fileName); // 파일 업로드(저장) 처리
					System.out.println("파일명 : " + fileName + " 저장 완료~");
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Part객체 파싱하여 파일이름 가져오기
	 * @param part
	 * @return 파일이름
	 */
	private String getFileName(Part part) {
		/* Content-Disposition 헤더
		 
		 1. 응답헤더에서 사용되는 경우... ex) 파일을 다운로드 하는 경우...
		 
		 Content-Disposition: inline(default)
		 Content-Disposition: attachment
		 Content-Disposition: attachment; filename="abc.jpg // 해당 파일명으로 파일 다운로드
		 
		2.	multipart body를 위한 헤더 정보로 사용되는 경우... ex) 파일 업로드
		
		Content-Disposition: form-data
		Content-Disposition: form-data; name="필드명"
		Content-Disposition: form-data; name="필드명"; filename="파일명";
		
		 */
		
		for(String content : part.getHeader("Content-Disposition").split(";")) {
			if(content.trim().startsWith("filenam")){
				return content
						.substring(content.indexOf("=") + 1)
						.trim()
						.replace("\"", "");
			}
		}
		return null;
	}
}
