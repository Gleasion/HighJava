/* 링크
http://localhost:8888/T02ServletTest
*/

package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 동작 방식
 
  1. HTTP 요청
  	사용자(클라이언트)가 URL을 클릭하면 HTTP 요청을 서블릿 컨테이너로 전송(요청)
  
  2. 처리 서블릿 검색
  	컨테이너는 web.xml에 정의된 url패턴 정보를 확인하여 어느 서블릿을 통해 처리할지를 검색
  	(서블릿 로딩이 안된 경우에는 로딩처리 함. 로딩(생성)시 init()메소드 호출됨)
  
  3. service() 호출
  	서블릿 컨테이너는 요청을 처리할 개별 스레드 객체를 생성하여 해당 서블릿 객체의 service()메소드 호출
  	(HttpServletRequest 및 HttpServletResponse 타입의 객체를 생성하여 파라미터로 넘겨줌
  
  4. (doGet, doPost, doPut, doDelete 등) 호출
  	service() 메소드는 메소드타입을 확인하여 적절한 메소드를 호출
  
  5. 객체 소멸
  	요청처리가 완료되면(응답완료 후),
  	HttpServletRequest 및 HttpServletResponse 타입의 객체는 소멸
  
  6. destory() 호출
  	컨테이너로부터 서블릿이 제거되는 경우에는 destroy() 메소드 호출 
 */

public class T02ServletTest extends HttpServlet{
	
	// delete 작업시
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Request 객체의 메소드 확인하기
		System.out.println("getCharacterEncoding() : " + req.getCharacterEncoding());
		System.out.println("getContentLength() : " + req.getContentLength());
		System.out.println("getQueryString() : " + req.getQueryString());
		System.out.println("getProtocol() : " + req.getProtocol());
		System.out.println("getMethod() : " + req.getMethod());
		System.out.println("getRequestURI() : " + req.getRequestURI());
		System.out.println("getHeaderNames() : " + req.getHeaderNames());
		System.out.println("getRemoteAddr() : " + req.getRemoteAddr());
		System.out.println("getRemotePort() : " + req.getRemotePort());
		
		String name = req.getParameter("name"); // 요청메세지로부터 name 값을 가져옴
		String age = req.getParameter("age");
		
		// http://localhost:8888/ServletTest/T02ServletTest?name=홍길동&age=18
		System.out.println("name => " + name);
		System.out.println("age => " + age);
		
		// 요청 객체에 정보 저장하기
		req.setAttribute("tel", "010-5555-5555");
		req.setAttribute("addr", "대전시 중구 오류동");
		
		// 요청 객체에 저장된 정보 가져오기
		System.out.println("tel => " + req.getAttribute("tel"));
		System.out.println("addr => " + req.getAttribute("addr"));
		
		//////////////////////////////////////////////////////////////////////////////////
		
		resp.setContentType("text/html;charset=UTF-8");
		//resp.setCharacterEncoding("UTF-8");
		//resp.setContentType("text/plain");
		
		PrintWriter out = resp.getWriter();
		
		out.println("name => " + name);
		out.println("서블릿 경로 : " + req.getServletPath()); // /T02ServletTest
		out.println("컨텍스트 경로 : " + req.getContextPath()); // 애플리케이션 이름 호출: /ServletTest
	}
	
	// insert, update 작업시,,
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
	
	
}
