package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * SqlSession 객체를 제공하는 팩토리 클래스
 * @author 404SEM-PC
 *
 */
public class MyBatisUtil {
	
	private static SqlSessionFactory sessionFactory;
	
	static {
		
		try {
			// 1-1. xml 설정문서 읽어오기
			Charset charset = Charset.forName("UTF-8"); // 설정파일에 있는 한글처리를 위해서...
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");
			
			// 1-2. 위에서 생성한 Reader객체를 이용하여 SqlSessionFactory 객체 생성하기
			sessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			// 1-3. 사용 끝난 Reader 객체 닫아주기
			rd.close();
			
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * SqlSession객체를 제공하는 팩토리 메서드
	 * @return SqlSession객체
	 */
	public static SqlSession getInstance() {
		return sessionFactory.openSession(); // 기본값: autoCommit => false
	}
	
	/**
	 * SqlSession객체를 제공하는 팩토리 메서드
	 * @param autoCommit 오토커밋 여부
	 * @return SqlSession객체
	 */
	public static SqlSession getInstance(boolean autoCommit) {
		return sessionFactory.openSession(autoCommit); 
	}
}
