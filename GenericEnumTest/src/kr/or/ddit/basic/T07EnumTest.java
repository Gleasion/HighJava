package kr.or.ddit.basic;
/*
	열거형 => 상수값들을 선언하는 방법
	static final int A = 0;
	static final int B = 1;
	static final int C = 2;
	static final int D = 3;
	
	enum Data {A, B, C, D};
	
	- 열거형 선언하는 방법
	enum 열거형 이름 {상수값1, 상수값2, 상수값3, ..., 상수값n};
 */

/*
	열거형에서 사용되는 메소드
	1. name() => 열거형 상수의 이름을 문자열로 반환한다.
	2. ordinal() => 열거형 상수가 정의된 순서값을 반환한다.(기본적으로 0부터 시작)
	3. valueOf("열거형상수이름"); => 지정된 열거형에서 '열거형상수이름'과 일치하는 열거형 상수를 반환한다.
 */

class Flower{
	static final int ROSE = 1;
	static final int TULIP = 1;	
}

class Animal {
	static final int LION = 1;
	static final int TIGER = 2;
}

public class T07EnumTest {
	
	//City 열거형 객체 선언(기본값을 이용하는 열거형)
	public enum City {서울, 부산, 대구, 광주, 대전}
	
	public enum HomeTown{광주, 부산, 진주, 서울, 대구, 대전, 울산}
	
	// 데이터 값을 임의로 지정한 열거형 객체 선언
	// 데이터 값을 정해줄 경우에는 생성자를 만들어서 괄호속의 값이 변수에 저장되도록 해야한다.
	public enum Season{
		봄("3월부터 5월까지 "), 여름("6월부터 8월까지"),
		가을("9월부터 11월까지 "), 겨울("12월부터 2월까지");
		
		//괄호안의 값이 저장될 변수 선언
		private String data;
		
		//생성자 만들기(열거형의 생성자는 접근제어자가 묵시적으로 'private'이다.)
		Season(String data) {
			this.data = data;
		}
		
		//값을 꺼내기 위한 getter 함수 생성
		public String getData() {
			return data;
		}
	}
	
	public static void main(String[] args) {
		
		City myCity1; //열거형 객체변수 선언
		City myCity2; //열거형 객체변수 선언
		
		//열거형 객체변수에 값 저장하기.
		myCity1 = City.서울;
		myCity2 = City.valueOf("서울"); //"열거형상수이름"과 일치하는 열거형 상수
		
		System.out.println("myCity1 : " + myCity1.name()); //상수의 이름
		System.out.println("myCity1의 ordinal : " + myCity1.ordinal()); //순서값
		System.out.println();
		
		System.out.println("myCity2 : " + myCity2.name());
		System.out.println("myCity2의 ordinal : " + myCity2.ordinal());
		System.out.println("==================================================");
		
		Season ss = Season.valueOf("여름");
		System.out.println("name : " + ss.name());
		System.out.println("ordinal : " + ss.ordinal());
		System.out.println("get메소드 : " + ss.getData());
		System.out.println("--------------------------------------------------");
		
		//열거형이름.values() => 데이터를 배열로 가져온다.
		Season[] enumArr = Season.values();
		for(int i = 0; i < enumArr.length; i++) {
			System.out.println(enumArr[i].name() + " : " + enumArr[i].getData());
		}
		System.out.println();
		
		for(City city : City.values()) {
			System.out.println(city + " : " + city.ordinal());
		}

		System.out.println();

		City city = City.대구;
		HomeTown ht = HomeTown.대구;
		
		System.out.println(city == City.대전);
		System.out.println(city == City.대구);
		// 상수를 이용한 것은 맞지만 타입이 호환되지 않음
		// System.out.println(city == ht);
		
		System.out.println();
		
		//ordinal 값을 가지고 오버라이딩
		System.out.println("대구  => " + city.compareTo(City.대구));
		System.out.println("서울  => " + city.compareTo(City.서울));
		System.out.println("대전  => " + city.compareTo(City.대전));
	}

}
