package kr.or.ddit.basic;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/*
 * ResourceBundle 객체 => 확장자가 properties인 파일 정보를 읽어와 key값과 value값을 분리한 정보를 갖는 객체
 * 	=> 읽어올 파일은 'key값 = value값' 형태로 되어 있어야 한다.
 *
 */

/* ResourceBundle 객체를 이용하여 파일 읽어오기
 * 
 * ResourceBundle 객체 생성하기
 * => 파일을 지정할 때는 '파일명'만 지정하고 확장자는 지정하지 않는다.
 * (확장자는 항상 properties이기 때문에...)
 */
public class T03ResourceBundleTest {

	public static void main(String[] args) {
		
		ResourceBundle bundle = ResourceBundle.getBundle("db", Locale.JAPANESE);
		
		// 모든 값들 읽어오기
		Enumeration<String> keys = bundle.getKeys();
		
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = bundle.getString(key);
			
			System.out.println(key + " = " + value);
			
		}
		System.out.println("출력 끝...");
	}

}
