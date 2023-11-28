package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T09FileEncodingTest {
/*
 * 파일인코딩 방식에 대하여...
 * 
 * 한글 인코딩 방식은 크게 UTF-8과 EUC-K 방식 두가지로 나뉜다. 
 * 원래 한글윈도우는 CP949 방식을 사용하여 한글을 표시하는데, 윈도우를 개발한 마이크로소프트사에서 EUC-KR방식에서
 * 확장하였기 때문에 MS949 방식이라고도 부른다.
 * 한글윈도우의 메모장에서 말하는 ANSI인코딩이란 CP949(Code Page 949)를 말한다. 
 * CP949는 EUC-KR의 확장이며, 하위 호환성이 있다. 
 * - MS949 => 윈도우의 한글 인코딩 방식(ANSI 계열)
 * - UTF-8 => 유니코드 UTF-8인코딩 방식(영문자 및 숫자: 1byte, 한글: 3byte) => 가변적 
 * - US-ASCII => 영문전용 인코딩 방식 
 * 
 * ANSI는 영어를 표기하기 위해 만든 코드로 규격 자체에 한글이 없었다가 나중에 여기에 한글이 포함되면서 
 * EUC-KR, CP949 등으로 생성됨.
 */
	public static void main(String[] args) {

		FileInputStream fis = null;
		InputStreamReader isr =null;
		
		try {
			
			//fis = new FileInputStream("d:/D_Other/test_ansi.txt"); //바이트 기반 스트림 
			fis = new FileInputStream("d:/D_Other/test_utf8.txt"); 
			
			//파일 인코딩 정보를 이용하여 읽어오기
			//InputStreamReader객체는 파일의 인코딩 방식을 저장할 수 있다.
			isr = new InputStreamReader(fis, "UTF-8"); //파라미터에 CP949 인코딩 방식을 넣어준 것  
			
			int data = 0;
			
			while((data = isr.read()) != -1) {
				System.out.print((char) data);
			}
			
			System.out.println();
			System.out.println("출력 끝...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
			isr.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	 }
   }
}
