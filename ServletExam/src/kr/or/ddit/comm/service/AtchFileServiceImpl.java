package kr.or.ddit.comm.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import kr.or.ddit.comm.dao.AtchFileDaoImpl;
import kr.or.ddit.comm.dao.IAtchFileDao;
import kr.or.ddit.comm.vo.AtchFileVO;

public class AtchFileServiceImpl implements IAtchFileService{

	private IAtchFileDao atchFileDao;
	
	private static IAtchFileService fileService;
	
	public AtchFileServiceImpl() {
		
		atchFileDao = AtchFileDaoImpl.getInstance();
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static IAtchFileService getInstance() {
		
		if(fileService == null) {
			fileService = new AtchFileServiceImpl();
		}
		
		return fileService;
	}
	
	/**
	 * 
	 */
	@Override
	public AtchFileVO saveAtchFileList(HttpServletRequest req) {
		
		String uploadPath = "e:/D_Other/UPLOAD_DIR";
		
		File uploadDir = new File(uploadPath);
		
		if(!uploadDir.exists()) { // 업로드 폴더 존재하지 않으면
			uploadDir.mkdir();
		}
		
		AtchFileVO atchFileVO = null;
		
		boolean isFristFile = true; // 첫번째 파일 여부
		
		try {
			
			for(Part part : req.getParts()) {
				
				String fileName = part.getSubmittedFileName();
				
				if(fileName != null && !fileName.equals("")) {
					
					// 첫번째 파일의 경우에는 테이블 2개에 정보 입력
					// 2번째 파일부터 상세 테이블에 순번 입력
					if(isFristFile) { // 첫번째 파일인 경우
						
						isFristFile = false;
						// 파일 기본정보 저장하기
						atchFileVO = new AtchFileVO();
						
						atchFileDao.insertAtchFile(atchFileVO);
					}

					String orignFileName = fileName; // 원본파일명
					long fileSize = part.getSize(); // 파일크기
					String saveFileName = ""; // 저장파일명
					String saveFilePath = ""; // 저장파일 경로
					
					saveFileName = UUID.randomUUID().toString().replace("-", "");
					saveFilePath = uploadDir + "/" + saveFileName;
					
					// 확장자 추출(마지막 .의 index값 추출)
					String fileExtension = orignFileName.lastIndexOf(".") < 0 ? "" :
								orignFileName.substring(orignFileName.lastIndexOf(".") + 1);
					
					// 파일 업로드 처리
					part.write(saveFilePath);
					
					// 저장파일명
					atchFileVO.setStreFileNm(saveFileName);
					atchFileVO.setFileSize(fileSize);
					atchFileVO.setOrignlFileNm(orignFileName);
					atchFileVO.setFileStreCours(saveFilePath);
					atchFileVO.setFileExtsn(fileExtension);
					atchFileVO.setFileCn("");
					
					// 파일 세부 정보 저장
					atchFileDao.insertAtchFileDeatail(atchFileVO);
					
					part.delete(); // 임시 업로드 파일 삭제하기
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return atchFileVO;
	}
	
	/**
	 * 
	 */
	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) {
		
		return atchFileDao.getAtchFileList(atchFileVO);
	}
	
	/**
	 * 
	 */
	@Override
	public AtchFileVO getAtchFileDeatail(AtchFileVO atchFileVO) {
		
		return atchFileDao.getAtchFileDetail(atchFileVO);
	}

		
	
}
