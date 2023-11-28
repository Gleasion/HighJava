package kr.or.ddit.comm.dao;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MyBatisUtil;

public class MyBatisDao {
	
	/**
	 * 데이터가 한개인 경우 사용할 메소드
	 * @param <T> 
	 * @param statement 실행할 쿼리ID
	 * @return 실행 결과
	 */
	public <T> T selectOne(String statement) {
		
		SqlSession sqlSession = MyBatisUtil.getInstance(true);
		
		T obj = null;
		
		try {
			obj = sqlSession.selectOne(statement);
		}catch(PersistenceException ex) {
			throw new RuntimeException("마이바티스에서 데이터 조회 중 예외발생!", ex);
		}finally {
			sqlSession.close(); // 세션 닫기
		}
		return obj;
	}
	
	/**
	 * 데이터가 한개인 경우 사용할 메소드
	 * @param <T>
	 * @param statement 실행할 쿼리ID
	 * @param parameter 쿼리 실행에 사용할 파라미터
	 * @return 실행 결과
	 */
	public <T> T selectOne(String statement, Object parameter) {
		
		SqlSession sqlSession = MyBatisUtil.getInstance(true);
		
		T obj = null;
		
		try {
			obj = sqlSession.selectOne(statement, parameter);
		}catch(PersistenceException ex) {
			throw new RuntimeException("마이바티스에서 데이터 조회 중 예외발생!", ex);
		}finally {
			sqlSession.close(); // 세션 닫기
		}
		return obj;
	}
	
	/**
	 * 목록 조회 메소드
	 * @param <T> 사용하는 시점에 결정되어 제너릭하게 선언
	 * @param statement
	 * @return list
	 */
	public <T> List<T> selectList(String statement){
		
		SqlSession sqlSession = MyBatisUtil.getInstance(true);
		
		List<T> list = Collections.EMPTY_LIST;
		
		try {
			
			list = sqlSession.selectList(statement);
		}catch(PersistenceException ex) {
			throw new RuntimeException("마이바티스에서 목록 조회중 예외 발생!", ex);
		}finally {
			sqlSession.close();
		}
		return list;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public <T> List<T> selectList(String statement, Object parameter){
		
		SqlSession sqlSession = MyBatisUtil.getInstance(true);
		
		List<T> list = Collections.EMPTY_LIST;
		
		try {
			list = sqlSession.selectList(statement, parameter);
		}catch(PersistenceException ex) {
			throw new RuntimeException("마이바티스에서 목록 조회중 예외 발생!", ex);
		}finally {
			sqlSession.close();
		}
		return list;
	}
	
	/**
	 * 
	 * @param statement
	 * @param parameter
	 * @return cnt
	 */
	public int insert(String statement, Object parameter) {
		
		SqlSession sqlSession = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			
			cnt = sqlSession.insert(statement, parameter);
			
			sqlSession.commit();
		}catch(PersistenceException ex){
			sqlSession.rollback();
			
			throw new RuntimeException("마이바티스에서 데이터 등록 중 예외 발생", ex);
		}finally {
			sqlSession.close();
		}
		return cnt;
	}
	
	/**
	 * 
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int update(String statement, Object parameter) {
		
		SqlSession sqlSession = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			
			cnt = sqlSession.update(statement, parameter);
			
			sqlSession.commit();
		}catch(PersistenceException ex){
			sqlSession.rollback();
			
			throw new RuntimeException("마이바티스에서 데이터 수정 중 예외 발생", ex);
		}finally {
			sqlSession.close();
		}
		return cnt;
	}
	
	/**
	 * 
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int delete(String statement, Object parameter) {
		
		SqlSession sqlSession = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			
			cnt = sqlSession.delete(statement, parameter);
			
			sqlSession.commit();
		}catch(PersistenceException ex){
			sqlSession.rollback();
			
			throw new RuntimeException("마이바티스에서 데이터 삭제 중 예외 발생", ex);
		}finally {
			sqlSession.close();
		}
		return cnt;
	}
	
	
}
