package kr.or.ddit.basic;

/* 싱글턴 클래스 구성하는 방법
 
 * 1. 자기 자신 타입의 참조변수를 멤버변수로 선언(private static)
 * 
 * 2. 생성자를 private으로 설정 (외부에서 생성자에 접근하지 못하게 하기 위해서)
 *  (즉, 외부에서 new 키워드 사용 못하게 하기 위해서)
 *  
 * 3. 객체(인스턴스)는 내부에서 생성해서 이 생성된 객체를 반환하는 매소드를 만든다.
 *   (보통 static 클래스명 getInstance()로) */   

 //   static : 이미 존재하기 때문에 객체를 만들지 않아도 참조 가능

public class MySingleton {
	
   // 자기 자신의 타입의 참조값을 저장하기 위한 멤버변수 선언
	private static MySingleton single;
	
	private MySingleton() {}
	
	public static MySingleton getInstance() {
		if(single == null) { // null이라면
			single = new MySingleton(); // 객체를 한번만 생성
		}
		
		return single; // 아니면 이미 있는 객체 리턴
	}

	public void displayText() {
		System.out.println("안녕하세요. 싱글턴 객체입니다.");
	}

}
