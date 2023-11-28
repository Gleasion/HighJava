package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class T08HashMapTest {

	public static void main(String[] args) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		// 자료추가 => put(Key값, value 값)
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");
		
		System.out.println("map => " + map);
		
		//자료 수정 => 데이터를 저장할 때 key값이 같으면 나중에 입력한 값이 저장된다.
		//		=> put(수정할 key값, 새로운 value값)
		map.put("addr", "서울");
		System.out.println("map => " + map);
		
		//자료 삭제 => remove(삭제할 key값);
		map.remove("name");
		System.out.println("삭제 후... map => " + map);
		
		// 자료읽기 => get(key값);
		System.out.println("addr => " + map.get("addr"));
		System.out.println("=========================================");
		
		//Map 안의 모든 데이터를 접근해보기
		
		// 방법1) keySet() 이용하여 값들을 읽어와 처리하는 방법
		Set<String> keySet = map.keySet();
		
		System.out.println("Iterator를 이용하여 데이터 접근하기...");
		
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()) {
			String key = it.next();
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("=========================================");
		
		//방법2 => Set 타입의 데이터를 '향상된 for문'으로 처리하면 Iterator를 사용하지 않아도 된다/
		System.out.println("향상된 for 문을 이용하여 데이터 접근하기...");
		for(String key : keySet) {
			System.out.println(key + " : " + map.get(key));
		}
		System.out.println("=========================================");
		
		//방법3 => value 값만 읽어와 출력하기 => values() 메소드 이용하기
		System.out.println("values() 메소드 이용한 방법");
		for(String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("=========================================");
		
		//방법 4 => Map관련 클래스에는 Map.Entry 타입의 내부 class를 가지고 있다.
		// 		Map.Entry타입의 객체를 가져오기 위한 메소드를 제공한다.
		System.out.println("entrySet()을 이용한 방법");
		Set<Map.Entry<String, String>> entrySet = map.entrySet();
		
		//가져온 Entry객체들을 순서대로 처리하기 위해서 Iterator 객체를 가져온다.
		Iterator<Map.Entry<String, String>> entryIt = entrySet.iterator();
		
		while(entryIt.hasNext()) {
			Map.Entry<String, String> entry = entryIt.next();
			
			System.out.println("키 값 : " + entry.getKey());
			System.out.println("value 값 : " + entry.getValue());
			System.out.println();
		}
		
		
		
		
		
		
		
		
		
		
	}

}
