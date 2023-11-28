package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;

/**
* 파일 읽기 예제
* @param args
*/
public class T05FileStreamTest {
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		
		try {
			
			fis = new FileInputStream("d:/D_Other/test2.txt");
			
			int data = 0;
			
			while((data = fis.read()) != -1) { 
				// 파일에서 데이터를 읽어올 수 있는 동안 계속 반복하라
				// 읽은 데이터 출력하기 
				System.out.print((char)data); // 파일에서 읽은 데이터를 한 글자씩 출력하는 것
			}
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
