package kr.or.ddit.member.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.comm.dao.MyBatisDao;
import kr.or.ddit.member.vo.MemberVO;

public class MemberDaoImpl extends MyBatisDao implements IMemberDao {
	
	private static IMemberDao memDao;
	
	private MemberDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}

	@Override
	public int insertMember(MemberVO mv) {
		
		int cnt = insert("member.insertMember", mv);
			
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		
		boolean isExist = false;

		int cnt = selectOne("member.checkMember", memId);
			
		if(cnt > 0) {
			isExist = true;
		}	

		return isExist;
	}
	
	@Override
	public MemberVO getMember(String memId) {
		
		MemberVO mv = selectOne("member.getMember", memId);
			
		return mv;
	}

	@Override
	public List<MemberVO> getAllMember() {
	
		List<MemberVO> memList = new ArrayList<MemberVO>();

		memList = selectList("member.getAllMember");
			
		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {
		
		int cnt = update("member.updateMember", mv);
			
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = delete("member.deleteMember", memId);
					
		return cnt;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		
		List<MemberVO> memList = Collections.EMPTY_LIST;
			
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("memId", mv.getMemId());
		paramMap.put("memName", mv.getMemName());
		paramMap.put("memTel", mv.getMemTel());
		paramMap.put("memAddr", mv.getMemAddr());
			
		memList = selectList("member.searchMember2", paramMap);

		return memList;
	}

}
