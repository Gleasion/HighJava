package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.memeber.dao.IMemberDao;
import kr.or.ddit.memeber.dao.MemberDaoImplForJDBC;

public class MemberServiceImpl implements IMemberService{

	private static IMemberService memSerivce;
	
	public static IMemberService getInstance() {
		if(memSerivce == null) {
			memSerivce = new MemberServiceImpl();
		}
		 return memSerivce;
	}
	
	private IMemberDao memDao;
	
	public MemberServiceImpl() {
		memDao = MemberDaoImplForJDBC.getInstance();
	}
	
	@Override
	public int registerMemeber(MemberVO mv) {
		return memDao.insertMemeber(mv);
	}

	@Override
	public boolean checkMember(String memId) {
		return memDao.checkMember(memId);
	}

	@Override
	public List<MemberVO> getAllMember() {
		return memDao.getAllMember();
	}

	@Override
	public int modifyMember(MemberVO mv) {
		return memDao.updateMember(mv);
	}

	@Override
	public int removeMember(String memId) {
		return memDao.deleteMember(memId);
	}

}
