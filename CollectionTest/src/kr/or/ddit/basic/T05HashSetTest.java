package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class T05HashSetTest {
	public static void main(String[] args) {
		
		Set hs = new HashSet();
		
		// Set에 데이터를 추가할 때도 add() 메소드를 이용한다.
		hs.add("DD");
		hs.add("AA");
		hs.add(2); // integer로 autoBoxing 됨
		hs.add("CC");
		hs.add("BB");
		hs.add(1);
		hs.add(3);
		
		System.out.println("Set 데이터 : " + hs);
		System.out.println();
		
		// Set은 데이터의 인덱스정보가 없고, 중복을 허용하지 않는다.
		// 그래서 이미 있는 데이터를 add하면 false를 반환하고, 데이터는 추가되지 않는다.
		boolean isAdded = hs.add("FF");
		System.out.println("중복되지 않을 때 : " + isAdded);
		System.out.println("Set 데이터 : " + hs);
		System.out.println();
		
		isAdded = hs.add("CC");
		System.out.println("중복 될 때 : " + isAdded);
		System.out.println("Set 데이터 : " + hs);
		System.out.println();
		
		// Set의 데이터를 수정하려면 수정하는 명령이 따로 없기 때문에 해당 자료를 삭제한 후
		// 새로운 데이터를 추가해 주어야 한다.
		
		// 집합이기 때문에 인덱스라는 개념을 적용할 수 없음
		// 수정을 하고 싶으면 삭제를 하고 새로운 데이터 추가해야함
		
		// 삭제처리를 위한 메소드
		// 1) clear() => Set 데이터 전체 삭제
		// 2) remove(삭제할 자료) => 해당 자료 삭제
		
		// ex) 'FF"를 "EE"로 수정하기
		hs.remove("FF");
		System.out.println("FF 삭제 후 Set 데이터 : " + hs);
		System.out.println();
		
		hs.add("EE");
		System.out.println("EE 추가 후 Set 데이터 : " + hs);
		System.out.println();
		
		hs.clear(); //전체 자료 삭제
		System.out.println("Set의 데이터 개수 : " + hs.size());
		System.out.println();
		
		// Set은 데이터의 인덱스 정보(개념)이 없기 때문에 List처럼 인덱스로 데이터를 하나씩 불러올 수 없다.
		// 그래서 데이터를 하나씩 얻기 위해서는 Iterator 객체를 이용하여 처리한다.
		Iterator it = hs.iterator();
		
		// 데이터 개수만큼 반복하기
		// hasNext() => 포인터가 다음 위치에 있으면 true, 없으면 false를 반환한다.
		while(it.hasNext()) {
			//next() => 포인터를 다음 위치로 이용하고, 이동한 위치의 데이터를 반환한다.
			System.out.println(it.next());
		}
		
		// 1~100사이의 중복되지 않는 정수 5개 만들기
		Set<Integer> intRnd = new HashSet<Integer>();
		while(intRnd.size() < 5) { //0부터 4까지 총 5번 돈다 단, 중복이 있으면 더 돌 수 있음
			int num = (int)(Math.random() * 100 + 1);
			intRnd.add(num);
		}
		
		System.out.println("만들어진 난수들 : " + intRnd);
		System.out.println();
		
		// Collection유형의 객체들은 서로 다른 자료구조로 쉽게 변경해서 사용할 수 있다.
		// 다른 종류의 객체를 생성할 때 생정자에 변경할 데이터 구조를 넣어주면 된다.
		// list는 중복가능
		List<Integer> intRndList = new ArrayList<Integer>(intRnd);
		intRndList.add(1);
		intRndList.add(2);
		intRndList.add(3);
		System.out.println("List의 자료 출력...");
		
		for(Integer num : intRndList) {
			System.out.print(num + " ");
		}
		System.out.println();
		
		// set은 중복제거
		Set<Integer> hs2 = new HashSet<Integer>(intRndList);
		System.out.print(hs2);
	}
}

