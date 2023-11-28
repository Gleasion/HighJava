package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class T06TreeSetTest {
	public static void main(String[] args) {
		// TreeSet 자료 구조는 검색속도가 빠르다, 구분검색 쉬움
		// headSet 기준선을 기준으로 작은 것
		// tailSet 기준선을 기준으로 큰 것
		// subSet 부분집합, 같은것을 가져올 때
		// TreeSet에는 자동정렬 기능이 포함되어 있다.
		TreeSet<String> ts = new TreeSet<String>();
		
		List<String> abcList = new ArrayList<String>();
		
		// 알파벳 대문자를 문자열로 변환하여 List에 저장하기
		for(char ch='A'; ch<='Z'; ch++) {
			String temp = String.valueOf(ch);
			abcList.add(temp);
		}
		Collections.shuffle(abcList);
		System.out.println("abcList 데이터 : " + abcList);
		
		for(String str : abcList) {
			ts.add(str);
		}
		System.out.println("TreeSet 데이터 : " + ts);
		System.out.println("Iterator 이용한 출력 시작 ..... ");
		
		// get을 사용할 수 iterator 사용함
		Iterator<String> it = ts.iterator();
		
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();
		System.out.println("Iterator 이용한 출력 끝 ..... ");
		
		// TreeSet에 저장된 자료 중 특정한 자료보다 작은 자료를 찾아서 SortedSet형으로 반환하는 메소드가 있다.
		// => headSet(기준값) : 기본적으로 '기준값'은 포함시키지 않는다.
		// => tailSet(기준값, 논리값) : 논리값이 true이면 '기준값'을 포함시킨다.
		SortedSet<String> ss1 = ts.headSet("K");
		System.out.println("K 이전 자료 : " + ss1);
		System.out.println("K 이전 자료(기준값 포함) : " + ts.headSet("K", true));
		
		// '기준값'보다 큰 자료를 찾아 SrotedSet형으로 반환하는 메소드
		// => tailSet(기준값) : 기본적으로 '기준값'을 포함시킨다.
		// => tailSet(기준값, 논리값) : 논리값이 false이면 '기준값'을 포함시키지 않는다.
		SortedSet<String> ss2 = ts.tailSet("K");
		System.out.println("K 이후 자료 : " + ss2);
		System.out.println("K 이후 자료(기준값 미포함) : " + ts.tailSet("K", false));
		
		// => subSet(기준값1, 기준값2) : 기준값1 ~ 기준값2 사이의 값을 가져온다.(기준값1 포함, 기준값2 미포함)
		// => subSet(기준값1, 논리값2, 기준값2, 논리값2) : 각 기준값을 포함할 지의 여부를 설정한다.
		System.out.println("K(포함) ~ N(미포함)까지 : " + ts.subSet("K", "N"));
		System.out.println("K(포함) ~ N(포함)까지 : " + ts.subSet("K", true, "N", true));
		System.out.println("K(미포함) ~ N(미포함)까지 : " + ts.subSet("K", false, "N", false));
		System.out.println("K(미포함) ~ N(포함)까지 : " + ts.subSet("K", false, "N", true));
		
	}
}
