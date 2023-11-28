package kr.or.ddit.Util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * SqlSession 객체를 제공하는 팩토리 클래스
 */
public class BoardUtil {
	
	private static SqlSessionFactory sessionFactory;
	
	static {
		
		try {
			
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("config/boardBatis-config.xml");
			
			sessionFactory = new SqlSessionFactoryBuilder().build(rd);
			
			rd.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 		
	}
	
	/**
	 * SqlSession 객체를 제공하는 팩토리 메소드
	 * @return SqlSession객체
	 */
	public static SqlSession getInstance() {
		return sessionFactory.openSession();
	}
	
	/**
	 * SqlSession 객체를 제공하는 팩토리 메소드
	 * @param autoCommit 오토커밋 여부
	 * @return SqlSession 객체
	 */
	public static SqlSession getInstance(boolean autoCommit) {
		return sessionFactory.openSession(autoCommit);
	}
	
}
