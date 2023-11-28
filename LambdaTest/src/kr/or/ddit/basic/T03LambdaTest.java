package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T03LambdaTest {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		list.add("김명철");
		list.add("박성민");
		list.add("박지석");
		list.add("박유민97");
		
		for(String str : list) {
			System.out.println(str);
		}
		System.out.println("-------------------");
		
		// 람다식을 이용한 list 이름 출력됨
		list.forEach(name -> System.out.println(name));
		
		// 메소드를 참조하여 출력
		System.out.println("-------------------");
		list.forEach(System.out::println);
	}

}
