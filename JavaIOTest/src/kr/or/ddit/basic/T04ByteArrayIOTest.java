package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOTest {

	public static void main(String[] args) throws IOException {
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4]; // 데이터를 읽을 때 사용할 버퍼용 배열 
		                           // temp : 바이트 데이터를 담기 위한 것
		
		
		//스트림 객체 생성하기
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc); //읽기
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); //출력
		
		int readBytes = 0; //읽어온 바이트 수를 저장할 변수 선언 
		
		
		//read() 메서드 => byte단위로 데이터를 읽어와 int형으로 변환한다. 
		//               더이상 읽어올 데이터가 없으면 -1을 리턴한다. 
		while((readBytes = bais.read(temp)) != -1) {
			
			System.out.println("readByte =>" + readBytes);
			
			System.out.println("temp => " + Arrays.toString(temp));
			//while문 3번 돌았음 
			//읽어야할 데이터가 10바이트 
			//임시로 할당받은 데이터는 4바이트이기 때문에 3번 도는 것 -> 6,7 한번 더 들어감 
			
			baos.write(temp, 0, readBytes); //데이터 출력하기 첫번째부터 읽은 바이트갯수만큼 읽어라 
		}
		
		//출력된 데이터를 바이트 배열로 반환하기 
		outSrc = baos.toByteArray();
		
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
		
		//사용한 스트림 객체 닫아주기 
		bais.close();
		baos.close();
		
		
	
		

	}

}
