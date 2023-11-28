/* 링크
http://localhost:8888/ServletTest/formdata.html

*/
package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
요청 객체를 이용하여 파라미터 데이터를 가져오는 방법
	- getParameter(): 파라미터 값이 한개인 경우에 가져올때 사용
		=> 여러개를 선택해도 무조건 첫번째 값만을 가져옴
	- getParameterValues(): 파라미터값이 여러개인 경우에 사용 ex) checkbox
	- getParameterNames(): 요청메세지에 존재하는 모든 파라미터 정보를 가져올 때 사용
 */
public class T03ServletParameterTest extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Post 방식으로 넘어오는 Body 데이터를 가져오기 위한 인코딩 정보를 설정함
		// Get 방식인 경우에는 톰캣의 URIEncoding 설정을 통해 처리할 수 있다.
		// 반드시 요청메세지로부터 값을 꺼내기 전에 설정해야 적용됨
		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String hobby = req.getParameter("hobby"); // 여러개 선택해도 1개만 가져옴
		String rlgn = req.getParameter("rlgn");
		
		String[] food = req.getParameterValues("food"); // 여러개 받아옴
		
		//////////////////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
//		resp.setContentType("text/plain");
		
		PrintWriter out = resp.getWriter();
		out.println("<html><body>");
		out.println("<p>username: " + username + "</p>");
		out.println("<p>password: " + password + "</p>");
		out.println("<p>gender: " + gender + "</p>");
		out.println("<p>hobby: " + hobby + "</p>");
		out.println("<p>rlgn: " + rlgn + "</p>");
		
		if(food != null) {
			for(String f : food) {
				out.println("<p>food: " + f + "</p>");
			}
		}
		
		Enumeration<String> paramNames = req.getParameterNames();
		
		while(paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			out.println("<p>파라미터 이름: " + paramName + "</p>");
		}
		out.println("</body></html>");
		
		System.out.println();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		
	}
	
}


