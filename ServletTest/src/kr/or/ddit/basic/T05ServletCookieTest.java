/* 링크
http://localhost:8888/ServletTest/T05ServletCookieTest
*/

package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 쿠키
	=> 웹서버와 브라우저는 애플리케이션을 사용하는 동안 필요한 값을 쿠키를 통해 공유하여 상태를 유지함.
	
	1. 구성요소
	- 이름
	- 값
	- 유효시간(초)
	- 도메인: ex) www.homehost.com, .somehost.com
		=> 쿠키의 도메인이 쿠키를 생성한 도메인을 벗어나면 브라우저는 쿠키를 저장(생성)하지 않는다.
		=> 보안에 취약하기 때문에 저장하지 않고 전송함
	
	2. 동작방식
	- 쿠키 생성단계: 생성한 쿠키를 응답데이터의 헤더에 저장하여 웹브라우저에 전송한다.
	- 쿠키 저장단계: 웹브라우저 응답데이터에 포함된 쿠키를 쿠키 저장소에 보관한다.
	- 쿠키 전송단계: 브라우저는 저장한 쿠키를 요청이 있을 때마다 웹서버에 전송한다.(삭제되기 전까지...)
				웹서버는 브라우저가 전송한 쿠키를 사용하여 필요한 작업을 수행한다.
 */

	public class T05ServletCookieTest extends HttpServlet{
	
	// Get 방식은 URL에 써야함
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 쿠키 설정하기
//		 setCookieExam(req, resp); // 쿠키 설정 예제
		
		// 쿠키 정보 읽기
//		readCookieExam(req, resp);
		
		// 쿠키 정보 삭제
		deleteCookieExam(req, resp);
	}
	
	   
	private void deleteCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/* 사용중인 쿠키 정보 삭제 방법
		1. 사용중인 쿠키 정보를 이용하여 쿠키객체를 생성한다.
		2. 쿠키객체의 최대 지속시간을 0으로 설정한다.
		3. 설정한 쿠키 객체를 응답헤더에 추가하여 전송한다.
		 */
		
		Cookie[] cookies = req.getCookies();
		
		////////////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
	      
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키 삭제 예제";
	      
		out.print("<html><head><title>" + title + "</title></head>");
		out.print("<body>");
	      
		if(cookies != null) {
			out.print("<h2>" + title + "</h2>");
	         
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("userId")) {
					// 쿠키 제거하기
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
					out.print("<p>삭제한 쿠키: " + cookie.getName() + "</p><br>");
				}
				
				out.print("<p>쿠키 이름: " + cookie.getName() + ", "
						+ "쿠키 값: " + cookie.getValue() + "</p><br>");
			}
		}else {
			out.print("<h2>쿠키정보가 없습니다.<h2>");
		}
		out.print("</body></html>");
	}


	private void readCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	   
	      Cookie[] cookies = req.getCookies();
	      
	      //////////////////////////////////////
	      
	      resp.setCharacterEncoding("UTF-8");
	      resp.setContentType("text/html");
	      
	      PrintWriter out = resp.getWriter();
	      
	      String title = "쿠키 읽기 예제";
	      
	      out.print("<html><head><title>" + title + "</title></head>");
	      out.print("<body>");
	      
	      if(cookies != null) {
	         out.print("<h2>" + title + "</h2>");
	         
	         for(Cookie cookie : cookies) {
	            out.print("<p>name : " + cookie.getName() + "</p><br>");
	            out.print("<p>value : " + cookie.getValue() + "</p><br>");
	            
	            out.print("<hr>");
	            
	            }
	         }else {
	            out.print("<h2>쿠키 정보가 없습니다.</h2>");
	         }
	         out.print("</body></html>");
	}

	private void setCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		// 쿠키 생성하기
		Cookie userId = new Cookie("userId", req.getParameter("userId"));
		Cookie userName = new Cookie("userName", req.getParameter("userName"));
		//URLEncoder.encode(req.getParameter("userName"), "UTF-8"));
		
		// 쿠키 소멸시간 설정(초단위) => 지정하지 않으면 브라우저 종료시 쿠키를 함께 삭제한다.
		userId.setMaxAge(60 * 60 * 24); // 1일
		userId.setHttpOnly(true); 		// javascript(front-end 상)를 이용한 직접 접근방지
		
		userName.setMaxAge(60 * 60 * 48); // 2일
		
		///////////////////////////////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		// 응답헤더에 쿠키 추가하기 // set하는법 => addCookie
		resp.addCookie(userId); 
		resp.addCookie(userName);
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키 설정 예제";
		
		out.print("<html><head><title>" + title + "</title></head>");
		out.print("<body><h1 align=\"center\">" + title + "</h1>");
		out.print("<ul><li><b>ID: </b> " 
				+ req.getParameter("userId") + "</li>"
				+ "<li><b>이름</b>:"
				+ req.getParameter("userName") + "</li></ul></body></html>");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	
	}
	
	
}
