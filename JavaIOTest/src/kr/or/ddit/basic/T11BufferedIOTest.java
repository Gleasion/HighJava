package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 입출력 성능 향상을 위한 보조스트림 예제
 * (바이트 기반의 Buffered 스트림 사용 예제)
 * @author PC-14
 */
public class T11BufferedIOTest {
	public static void main(String[] args) {
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null; //버퍼스트림은 단독으로 사용하지 못함 
		
		try {
			
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
			//버퍼 크기가 5인 스트림 객체 생성 
			//버퍼 크기를 지정하지 않으면 기본적으로 버퍼크기가 8192byte(8KB)로 설정된다.  
			bos = new BufferedOutputStream(fos, 5); //보조 스트림이기 때문에 넣어준 것
			                                        //1~5까지 내가 가지고 있는 버퍼에 차곡차곡 
			                                        //입출력 성능이 좋아짐 
			
			
			for(char ch='1'; ch<='9'; ch++) {
				bos.write(ch); //5개 꽉 차면 한번에 날림 
			}
			
			bos.flush(); // 작업을 종료하기 전에 버퍼에 남아 있는 데이터를 모두 출력시킨다. 
			             // close시에 자동 호출됨.
			
			System.out.println("작업 끝..."); 
			
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
