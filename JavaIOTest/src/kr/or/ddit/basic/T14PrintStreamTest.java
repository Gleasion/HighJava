package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 프린트 기능 제공 스트림 
 * @author PC-14
 *
 */
public class T14PrintStreamTest {
	public static void main(String[] args) throws IOException {
		
		/*
		 	PrintStream은 모든 자료형 데이터를 출력할 수 있는 기능을 제공하는 스트림 클래스이다. 
		 */
		
		FileOutputStream fos = new FileOutputStream("d:/D_Other/print.txt");
		
		PrintStream out = new PrintStream(fos);
		out.print("안녕하세요. PrintStream 입니다. \n");
		out.println("안녕하세요. PrintStream 입니다2.");
		out.println("안녕하세요. PrintStream 입니다3.");
		out.println(fos);
		out.println(3.14);
		
		System.out.println("작업 끝...");
		
		out.close();
		
		/////////////////////////////////////////////////////////////
		
		/*
		 
		 PrintStream은 데이터를 문자로 출력하는 기능을 제공함.
		 향상된 기능의 PrintWriter가 추가되었지만 계속 사용됨.
		 
		 PrintWriter가 PrintStream보다 다양한 인코딩 방식의 문자를 처리하는데 적합하다. 
		 
		 */
		
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/print.txt");
		
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos2, "UTF-8")); 
		              //보조스트림 안에 보조스트림을 넣을 수 있음(안해도 되긴함) -> 파일의 인코딩 처리를 할 수 있음
		pw.print("안녕하세요. PrintWriter 입니다. \n");
		pw.println("안녕하세요. PrintWriter 입니다2.");
		pw.println("안녕하세요. PrintWriter 입니다3.");
		pw.println(fos2);
		pw.println(3.14f);
		
		pw.close();
		
	}

}
