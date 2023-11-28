package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// 특정서버 정보와 파일 내용을 출력하는 예제

// URLConnection => 어플리케이션과 URL간의 통신연결을 위한 추상클래스
public class URLConnectionTest {
	
	public static void main(String[] args) throws IOException {
		
		URL url = new URL("https://www.naver.com/index.html"); // 네이버의 정보 가져오기
		
	// Header 정보 가져오기
		URLConnection urlConn = url.openConnection(); // URLConnection
		
		System.out.println("Content-Type : " + urlConn.getContentType());
		System.out.println("Encoding : " + urlConn.getContentEncoding());
		System.out.println("Content : " + urlConn.getContent());
		// └> Body 내용을 준것이 아니라 HTTPInputStream 객체를 던저줌
		// └> index.html 문서의 Body 내용 꺼내와야함(아래에 InputStreamReader 이용)
		System.out.println();
		
	// 전체 헤더 정보 출력하기
		Map<String, List<String>> headerMap = urlConn.getHeaderFields();
		
		Iterator<String> it = headerMap.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			System.out.println(key + " : " + headerMap.get(key));
		}
		System.out.println("----------------------------------------------");
		
	// Body 내용 읽어오기
		InputStreamReader isr = new InputStreamReader((InputStream)urlConn.getContent());
		
		// 버퍼 기능
		BufferedReader br = new BufferedReader(isr);
		
	// 한줄씩 변환하여 출력
		String tempStr = "";
		while((tempStr = br.readLine()) != null) {
			System.out.println(tempStr);
		}
		br.close();
		
//	한 문자씩 변환하여 출력
//		int data = 0;
//		while((data = isr.read()) != -1) {
//			System.out.print((char)data);
//		}
//		isr.close();
		
		
	}
	
	
	
	
	
	
}
