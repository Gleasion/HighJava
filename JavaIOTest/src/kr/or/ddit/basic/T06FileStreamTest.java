package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 파일 출력 예제
 * @author PC-14
 *
 */
public class T06FileStreamTest {

	public static void main(String[] args) {

		//파일에 출력하기 
		FileOutputStream fos = null;
		
		try {
			//파일 출력용 스트림 객체 생성 
			fos = new FileOutputStream("d:/D_Other/out.txt", true);
			
			for(char ch='A'; ch<='Z'; ch++) {
				fos.write(ch);
			}
			
			System.out.println("쓰기 작업 완료...");
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(new File("d:/D_Other/out.txt"));
			
			int data = 0; 
			
			while((data = fis.read()) != -1) {
				System.out.print((char) data);
			}
			
			System.out.println();
			System.out.println("읽기 작업 끝...");
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

}
