package kr.or.ddit.comm.dao;

import java.util.List;

import kr.or.ddit.comm.vo.AtchFileVO;

public class AtchFileDaoImpl extends MyBatisDao implements IAtchFileDao{

	private static IAtchFileDao fileDao;
	
	private AtchFileDaoImpl() {

	}
	
	public static IAtchFileDao getInstance() {
		
		if(fileDao == null) {
			fileDao = new AtchFileDaoImpl();
		}
		
		return fileDao;
	}
	
	@Override
	public int insertAtchFile(AtchFileVO atchFileVO) {
		
		int cnt = 0;
		
		cnt = insert("atchFile.insertAtchFile", atchFileVO);
		
		return cnt;
	}

	@Override
	public int insertAtchFileDeatail(AtchFileVO atchFileVO) {
		
		int cnt = 0;
		
		cnt = insert("atchFile.insertAtchFileDetail", atchFileVO);
		
		return cnt;
	}

	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) {
		
		List<AtchFileVO> atchFileList = selectList("atchFile.getAtchFileList", atchFileVO);
		
		return atchFileList;
	}

	@Override
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) {
		
		AtchFileVO fileVO = selectOne("atchFile.getAtchFileDetail", atchFileVO);
		
		return fileVO;
	}

}
