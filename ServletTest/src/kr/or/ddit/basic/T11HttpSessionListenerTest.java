/* 링크
http://localhost:8888/ServletTest/T11HttpSessionListenerTest
*/

package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class T11HttpSessionListenerTest extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		HttpSession httpSession = req.getSession();
		
		httpSession.setAttribute("ATTR1", "속성1"); // 속성 추가
		httpSession.setAttribute("ATTR1", "속성11"); // 속성 변경
		httpSession.setAttribute("ATTR2", "속성2"); // 속성 추가
		httpSession.removeAttribute("ATTR1"); // 속성 제거
	
		httpSession.invalidate(); // 세션 소멸...
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
		
	}
	
}
