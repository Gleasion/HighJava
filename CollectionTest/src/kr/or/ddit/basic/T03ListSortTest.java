package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T03ListSortTest {

/*
 정렬과 관련된 Interface는 Comparable과 Comparator가 있다.

	- 보통 객체 자체에 정렬 기능을 부여하기 위해서는 Comparable을 구현하고,
	- 정렬 기준을 별도로 구현하고 싶은 경우에는 Comparator를 구현하여 사용하면 된다.
	
 */	
	
	public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬 전 : " + list);
		
		//정렬은 Collection.sort() 메소드를 이용하여 설정한다.
		//정렬은 기본적으로 '오름차순 정렬'을 수행한다.
		
		Collections.sort(list); // 오름차순 정렬하기 
		//문자열을 오름차순 정렬 : 사전순
		//숫자를 오름차순 정렬 : 커지는 순
		System.out.println("내부 정렬기능을 이용하여 정렬 후 : " + list);
		
		//데이터 섞기
		Collections.shuffle(list); //데이터 섞는 메소드 shuffle()
		System.out.println("섞은 후 : " + list);
		
		// 정렬방식을 구현한 외부 정렬자 객체를 이용하여 정렬하기
		Collections.sort(list, new Desc());
		System.out.println("외부 정렬자를 이용한 정렬 후 : " + list); // 밑에서 정의해줌
		
	}

}

//내림차순 정렬을 위한 외부 정렬자
//return 타입 : int
//구현 된 method compare(T o1, T o2): Compares its two arguments for order.
class Desc implements Comparator<String> {
	/* 
	 * compare() 메소드의 반환값을 결정하는 방법
	 *=> 이 메소드가 양수를 반환하면 두 값의 순서가 바뀐다. (오름차순이 기본)
	 *
	 *- 오름차순 정렬일 경우...
	 *=> 앞의 값이 크면 양수, 같으면 0, 앞의 값이 작으면 음수를 반환하도록 구현한다.
	 */
	
	@Override
	public int compare(String str1, String str2) {
		return str1.compareTo(str2) * -1 ; // 내림차순
	}
	
}