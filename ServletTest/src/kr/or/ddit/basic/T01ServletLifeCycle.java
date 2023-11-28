/* 링크
http://localhost:8888/T01ServletLifeCycle
*/

package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 라이프 사이클을 확인하기 위한 예제 */
public class T01ServletLifeCycle extends HttpServlet{
	
	public T01ServletLifeCycle() {
		System.out.println("T01ServletLifeCycle 생성자 호출됨");
	}
	
	@Override
	public void init() throws ServletException {
		// 초기화 코드 작성...
		System.out.println("T01ServletLifeCycle init() 호출됨");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 실제 작업이 수행이 시작되는 지점... (자바의 main메소드 역할)
		System.out.println("T01ServletLifeCycle service() 호출됨");
		super.service(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메소드 방식이 GET 방식인 경우 호출됨
		System.out.println("doGet 메소드 호출됨");
		
//		throw new ServletException("T01이 발생시킨 서블릿 예외입니다...");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메소드 방식이 POST 방식인 경우 호출됨
		super.doPost(req, resp);
	}
	
	@Override
	public void destroy() {
		// 서블릭 객체 소멸 시 (컨테이너로부터 서블릿 객체 제거시) 필요한 코드 작성...
		System.out.println("destroy() 메소드 호출됨");
	}
	
	
	
}
