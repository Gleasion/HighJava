package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 자카르타 프로젝트의 fileUpload모듈을 이용한 파일 업로드 예제
 * @author PC-07
 *
 */
@WebServlet("/basic/upload.do")
public class UploadController extends HttpServlet {

	// 메모리 임계크기(이 크기가 넘어가면 레파지토리 위치에 임시파일로 저장됨)
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;
	
	// 파일 1개당 최대 크기
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 40;
	
	// 요청파일 최대 크기
	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 50;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*
		System.out.println("========================================================");
		
		BufferedReader br = new BufferedReader(
							new InputStreamReader(req.getInputStream()));
		
		String readLine = "";
		while((readLine = br.readLine()) != null) {
			System.out.println(readLine);
		}
		
		System.out.println("========================================================");
		*/
		
		// 멀티파트 파싱 전에 파라미터 정보 가져와 보기
		System.out.println("멀티파트 파싱 전 => " + req.getParameter("sender"));
		
		if(ServletFileUpload.isMultipartContent(req)) {
			
			// 폼필드 저장용 맵
			Map<String, String> formFieldMap = new HashMap<>();
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			System.out.println("임시 저장경로 => " + System.getProperty("java.io.tmpdir"));
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setFileSizeMax(MAX_REQUEST_SIZE);
			
			try {
				List<FileItem> fileItems = upload.parseRequest(req);
				
				if(fileItems != null && fileItems.size() > 0) {
					for(FileItem item : fileItems) {
						if(!item.isFormField()) { // 파일인 경우...
							String fileName = item.getName();
							String filePath = "e:/D_Other/" + fileName;
							File storeFile = new File(filePath);
							
							item.write(storeFile); // 파일 업로드(저장)
							System.out.println("업로드 완료됨. 파일명: " + fileName);
							
						}else { // 폼데이터인 경우...
							formFieldMap.put(item.getFieldName()
											, item.getString("UTF-8"));
						}
					}
				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
			for(Entry<String, String> entry : formFieldMap.entrySet()) {
				System.out.println("파라미터 이름 : " + entry.getKey());
				System.out.println("파라미터 : " + entry.getValue());
			}
		}
	}
}