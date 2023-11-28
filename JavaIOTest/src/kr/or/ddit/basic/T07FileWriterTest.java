package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class T07FileWriterTest {
	public static void main(String[] args) {
	
		//사용자가 입력한 내용을 그대로 파일로 저장하기 
		
		//콘솔(표준입력장치)과 연결된 입력용 문자스트림 생성 
		// InputStramReader => 바이트기반 스트림을 문자기반 스트림으로 변경해주는 보조스트림이다.
		InputStreamReader isr = new InputStreamReader(System.in); //보조스트림은 System.in(기본 스트림, 1바이트씩 읽음)이 필요
		
		FileWriter fw = null; // 파일 출력용 문자기반 스트림
		
		try {
			//파일 출력용 문자 스트림 객체 생성하기 
			fw = new FileWriter("d:/D_Other/testChar.txt");
			
			int data = 0;
			
			System.out.println("아무거나 입력하세요. (입력이 끝나면 Ctrl+Z 키를 눌러주세요.)");
			
			//콘솔에서 입력할 때 입력의 종료 표시는 Ctrl + Z 키를 누르면 된다. 
			while((data = isr.read()) != -1) {
				fw.write(data);
			}
			
			System.out.println("작업 끝...."); //Ctrl + Z 누르면 빠져나옴
			
			isr.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
}
