package kr.or.ddit.basic;

public class Service {
		
	@PrintAnnotation
	public void method1() {
		System.out.println("메소드 1에서 출력 되었습니다.");
	}
		
	@PrintAnnotation("%") // value 한개일 경우 생략가능
		public void method2() {
		System.out.println("메소드 2에서 출력 되었습니다.");
	}
	
	@PrintAnnotation(value = "#", count = 25, myValue = "안녕하세요.")
	public void method3() {
		System.out.println("메소드 3에서 출력 되었습니다.");
	}
	
}

