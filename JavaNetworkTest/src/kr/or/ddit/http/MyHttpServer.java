package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.StringTokenizer;

/**
 * 간단한 웹서버 예제
 */
public class MyHttpServer {
	
	private final int port = 80;
	private final String encoding = "UTF-8";
	
	public void start() {
		
		System.out.println("HTTP 서버가 시작되었습니다.");
		
		ServerSocket server = null;
		
		try {
			server = new ServerSocket(this.port);
			
			while(true) {
				
				Socket socket = server.accept();
				
				HttpHandler handler = new HttpHandler(socket);
				handler.start();
			}
			
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	class HttpHandler extends Thread {
		private Socket socket;
		
		public HttpHandler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			
			OutputStream out = null;
			BufferedReader br = null;
			
			try {
				
				out = new BufferedOutputStream(socket.getOutputStream());
				br = new BufferedReader(
						new InputStreamReader(
							socket.getInputStream()));
				
				// 요청헤더 정보 파싱하기
				StringBuilder sb = new StringBuilder();
				
				// Request Line(첫줄은 Request Line임.)
				String reqLine = br.readLine();
				
				System.out.println("Request Line : " + reqLine);
				
				while(true) {
					String str = br.readLine();
					
					if(str.equals("")) break;
					
					sb.append(str + "\n");
				}
				
				// 헤더 정보(Header)
				String reqHeaderStr = sb.toString();
				
				//System.out.println("요청 헤더 : " + reqHeaderStr);
				
				String reqPath = ""; // 요청경로 
				
				// 요청 페이지 정보 가져오기
				StringTokenizer st = new StringTokenizer(reqLine);
				while(st.hasMoreTokens()) {
					String token = st.nextToken();
					
					if(token.startsWith("/")) {
						reqPath = token;
						break;
					}
				}
				
				System.out.println("요청경로 : " + reqPath);
				
				// URL 디코딩 처리(한글깨짐 문제..)
				reqPath = URLDecoder.decode(reqPath, encoding);
				
				String filePath = "./WebContent" + reqPath;
				
				// 해당 파일이름을 이용하여 Content-Type 정보 추출하기
				String contentType = URLConnection.getFileNameMap()
										.getContentTypeFor(filePath);
				
				// CSS파일인 경우 인식이 안되서 추가함.
				if(contentType == null && filePath.endsWith(".css")) {
					contentType = "text/css";
				}
				
				
				File file = new File(filePath);
				if(!file.exists()) {
					makeErrorPage(out, 404, "Not Found");
					return;
				}
				
				byte[] body = makeResponseBody(filePath);
				
				byte[] header = makeResponseHeader(body.length, contentType);
				
				// 브라우져에 응답메시지 보내기..
				
				out.write(header); // 헤더 정보 전송(Status Line 포함)
				
				out.write("\r\n\r\n".getBytes()); // Empty Line 전송
				
				out.write(body); // 응답내용(Body) 전송
				
				out.flush();
				
			}catch(IOException ex) {
				
			}finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		private void makeErrorPage(
				OutputStream out, int statusCode, String errorMsg) {
			String statusLine = "HTTP/1.1" + " " + statusCode
					+  " " + errorMsg;
			
			try {
				out.write(statusLine.getBytes());
				out.flush();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * 응답헤더 생성하기
		 * @param contentLength 응답내용 크기
		 * @param mimeType Content-Type
		 * @return 헤더정보담은 바이트 배열
		 */
		private byte[] makeResponseHeader(int contentLength, String mimeType) {
			String header = "HTTP/1.1 200 OK\r\n"
			       + "Server: MyHttpServer 1.0\r\n"
			       + "Content-length: " + contentLength + "\r\n"
			       + "Content-type: " + mimeType + "; charset=UTF-8" + "\r\n";
			
			return header.getBytes();
		}
		
		/**
		 * 응답내용 생성하기
		 * @param filePath 응답으로 사용할 파일 경로
		 * @return 응답내용 바이트 배열
		 */
		private byte[] makeResponseBody(String filePath) {
			byte[] data = null;
			FileInputStream fis = null;
			
			try {
				fis = new FileInputStream(filePath);
				File file = new File(filePath);
				data = new byte[(int)file.length()]; 
				
				fis.read(data);
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			return data;
		}
	}
	
	public static void main(String[] args) {
		new MyHttpServer().start();
	}
	
	
}
