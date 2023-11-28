package kr.or.ddit.member.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.MyBatisUtil;

public class MemberDaoImpl implements IMemberDao {
	
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
		
		int cnt = 0;
		
		SqlSession session = MyBatisUtil.getInstance();
		
		try {
			
			cnt = session.insert("memberTest.insertMember", mv);
			
			session.commit();
		}catch(PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		
		boolean isExist = false;
		
		SqlSession session = MyBatisUtil.getInstance(true);
		
		try {
			
			int cnt = session.selectOne("memberTest.checkMember", memId);
			
			if(cnt > 0) {
				isExist = true;
			}
			
			session.commit();
		}catch(PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return isExist;
	}

	@Override
	public List<MemberVO> getAllMember() {
	
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		SqlSession session = MyBatisUtil.getInstance();
		
		try {
			
			memList = session.selectList("memberTest.getAllMember");
			
		}catch(PersistenceException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		
		
		
		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {
		
		int cnt = 0;
		
		SqlSession session = MyBatisUtil.getInstance();
		
		try {
			
			cnt = session.update("memberTest.updateMember", mv);
			
			session.commit();
		}catch(PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		
		SqlSession session = MyBatisUtil.getInstance();
		
		try {
			
			cnt = session.delete("memberTest.deleteMember", memId);
			
			session.commit();
		}catch(PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return cnt;
		
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		
		List<MemberVO> memList = Collections.EMPTY_LIST;
		
		SqlSession session = null;
		
		try {
			session = MyBatisUtil.getInstance(true);
			
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("memId", mv.getMemId());
			paramMap.put("memName", mv.getMemName());
			paramMap.put("memTel", mv.getMemTel());
			paramMap.put("memAddr", mv.getMemAddr());
			
			memList = session.selectList("memberTest.searchMember2", paramMap);
			
		}catch(PersistenceException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		
		return memList;
	}

}
