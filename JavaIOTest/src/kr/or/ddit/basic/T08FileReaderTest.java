package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class T08FileReaderTest { //문자기반

	public static void main(String[] args) {
		
		// 문자기반 스트림을 이용한 파일 내용 읽기 
		//FileReader fr = null;
		
		FileReader fr = null;
		
		try {
			fr = new FileReader("d:/D_Other/testChar.txt");
			
			int data = 0;
			
			while((data = fr.read()) != -1) {
				System.out.print((char) data);
			}
			
			System.out.println("읽기 끝...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
