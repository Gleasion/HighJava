package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class T10FileEncodingTest {

	public static void main(String[] args) {
		/*
		   OutputStreamWriter => OutputStream(바이트 기반)객체를 Writer(문자기반)객체로 변환하는 
		                                                  보조스트림.
		 */
		
		// 키보드로 입력한 내용을 파일로 저장하는데 out_utf8.txt파일은 'UTF-8'인코딩 방식으로 
		// out_ansi.txt 파일은 'ms949' 인코딩 방식으로 저장한다. 
		
		// 콘솔 입력용 
		InputStreamReader isr = null;
		
		// 파일 출력용 
		FileOutputStream fos1 = null;
		FileOutputStream fos2 = null;
		
		OutputStreamWriter osw1 = null;
		OutputStreamWriter osw2 = null;
		
		try {
			isr = new InputStreamReader(System.in);
			
			fos1 = new FileOutputStream("d:/D_Other/out_utf8.txt");
			fos2 = new FileOutputStream("d:/D_Other/out_ansi.txt");
			
			osw1 = new OutputStreamWriter(fos1, "UTF-8");
			osw2 = new OutputStreamWriter(fos2, "ms949");
			
			int data = 0;
			
			System.out.println("아무거나 입력하세요...");
			
			while((data = isr.read()) != -1) {
				osw1.write(data);
				osw2.write(data);
			}
			
			System.out.println("작업 완료...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				isr.close();
				osw1.close();
				osw2.close();
				//fos1.close(); 보조스트림을 닫으면 알아서 닫아짐 안써도 됨 
				//fos2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
