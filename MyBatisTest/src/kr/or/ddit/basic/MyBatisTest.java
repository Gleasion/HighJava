package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.member.vo.MemberVO;

// MyBatis를 이용하여 DB 데이터를 처리하는 작업 순서
public class MyBatisTest {
	public static void main(String[] args) {

		
// 1. myBatis의 환경설정파일을 읽어와 실행시킨다.
		SqlSessionFactory sessionFactory = null;
		
		try {
			// 1-1. xml 설정문서 읽어오기
			Charset charset = Charset.forName("UTF-8"); // 설정파일에 있는 한글처리를 위해서
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");
			
			// 1-2. 위에서 생성한 Reader 객체를 이용하여 SqlSessionFactory 객체 생성하기
			sessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			// 1-3. 사용 끝난 Reader 객체 닫아주기
			rd.close();
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
		/*
	// 2-1. Insert작업 연습		
		System.out.println("Insert 작업 시작...");
		
		// 1) 저장할 데이터를 VO에 담는다.
		MemberVO mv = new MemberVO();
		mv.setMemId("d002");
		mv.setMemName("강감찬");
		mv.setMemTel("010-1111-1111");
		mv.setMemAddr("경남 진주시");
		
		// 2) SqlSession 객체를 이용하여 해당 쿼리문을 실행한다.
		// 형식) sqlsession객체.insert("namespace값.id값", 파라미터 객체);
		// 반환값 : 성공한 레코드 수
		SqlSession sqlSession = sessionFactory.openSession(false); // 오토커밋 여부 설정 (true : 자동으로 커밋)
		
		try {
			int cnt = sqlSession.insert("memberTest.insertMember", mv);
			
			if(cnt > 0) {
				System.out.println("Insert 작업 성공 : " + cnt);
				sqlSession.commit(); // 커밋
			}else {
				System.out.println("Insert 작업 실패!!!");
			}
			
		}catch(PersistenceException ex) {
			sqlSession.rollback(); // 롤백
			ex.printStackTrace();
		}finally {
			sqlSession.close(); // 세션 닫기
		}
		System.out.println("------------------------------------------------");
		
	// 2-2. update 연습
		System.out.println("update 작업 시작...");
		
		MemberVO mv = new MemberVO();
		mv.setMemId("d002");
		mv.setMemName("박성민");
		mv.setMemTel("010-2222-2222");
		mv.setMemAddr("대전시");
		
		SqlSession sqlSession = sessionFactory.openSession(false); // 오토커밋 여부 설정 (true : 자동으로 커밋)
		
		try {
			int cnt = sqlSession.update("memberTest.updateMember", mv);
			
			if(cnt > 0) {
				System.out.println("Update 작업 성공 : " + cnt);
				sqlSession.commit(); // 커밋
			}else {
				System.out.println("Update 작업 실패!!!");
			}
			
		}catch(PersistenceException ex) {
			sqlSession.rollback(); // 롤백
			ex.printStackTrace();
		}finally {
			sqlSession.close(); // 세션 닫기
		}
		System.out.println("------------------------------------------------");
		*/
	// 2-3. delete 연습
		System.out.println("delete 연습 시작...");
		
//		MemberVO mv = new MemberVO();
//		mv.setMemId("d002");

		SqlSession sqlSession = sessionFactory.openSession(false); // 오토커밋 여부 설정 (true : 자동으로 커밋)
		
		try {
//			int cnt = sqlSession.delete("memberTest.deleteMember", mv);
			int cnt = sqlSession.delete("memberTest.deleteMember", "a001");
			
			if(cnt > 0) {
				System.out.println("Delete 작업 성공 : " + cnt);
				sqlSession.commit(); // 커밋
			}else {
				System.out.println("Delete 작업 실패!!!");
			}
			
		}catch(PersistenceException ex) {
			sqlSession.rollback(); // 롤백
			ex.printStackTrace();
		}finally {
			sqlSession.close(); // 세션 닫기
		}
		System.out.println("------------------------------------------------");
		
	// 2-4. select 연습
		// 1) 응답결과가 여러개일 경우...
		System.out.println("select 연습(결과가 여러개일 경우...)");
		List<MemberVO> memberList = null;
		
		// 응답의 결과가 여러개일 경우에는 selectList() 메소드를 이용한다.
		// 이 메소는 여러개의 레코드를 VO에 담은 후 이 VO객체를 List에 추가해 주는 작업을 자동으로 수행한다.
		sqlSession = sessionFactory.openSession(true); // 오토커밋 여부 설정
		
		try {
			memberList = sqlSession.selectList("memberTest.getAllMember");
			
			for(MemberVO mv : memberList) {
				
				System.out.println("ID : " + mv.getMemId());
				System.out.println("이름 : " + mv.getMemName());
				System.out.println("전화 : " + mv.getMemTel());
				System.out.println("주소 : " + mv.getMemAddr());
				System.out.println("===================================");
			}
			
			System.out.println("출력 끝...");
			
		}finally {
			sqlSession.close();
		}
		
		// 2) 응답 결과가 1개일 경우...
		System.out.println("select 연습 시작(결과가 1개일 경우...");
		
		
		sqlSession = sessionFactory.openSession(true); // 오토커밋 여부 설정
		
		try {
			// 응답 결과가 1개가 확실할 경우에는 selectOne() 메서드를 사용한다.
			MemberVO mv2 = sqlSession.selectOne("memberTest.getMember", "a001");
			
			System.out.println("ID : " + mv2.getMemId());
			System.out.println("이름 : " + mv2.getMemName());
			System.out.println("전화 : " + mv2.getMemTel());
			System.out.println("주소 : " + mv2.getMemAddr());
			
			System.out.println("출력 끝...");
			
		}finally {
			sqlSession.close();
		}
		
		
		
	}
}
