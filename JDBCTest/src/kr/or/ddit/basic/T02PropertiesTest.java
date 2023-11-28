package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class T02PropertiesTest {

	public static void main(String[] args) {
		
		// 읽어온 정보를 저장할 Properties 객체 생성
		Properties prop = new Properties();
		
		try {
			//파일읽기를 수행할 FileInputStream 객체 생성하기
			FileInputStream fis = new FileInputStream("./res/db.properties");
			
			// 파일 내용 읽기
			prop.load(fis);
			
			// 읽어온 데이터 출력하기
			
			Enumeration<String> keys = 
					(Enumeration<String>) prop.propertyNames();
			
			while(keys.hasMoreElements()) {
				String key = keys.nextElement();
				String value = prop.getProperty(key);
				System.out.println(key + " = " + value);
			}
			System.out.println("출력 끝...");
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}

	}

}
