package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

/*
	와일드카드(?)는 제너릭 타입을 이용한 타입
	안전한 코드를 위해 사용되는 특별한 종류의 인수(Argument)로서,
	변수선언, 객체생성 및 메소드를 정의할 때 사용된다. (제너릭 타입 선언시에는 사용할 수 없다.)
	
	<? extends T> => 와일드 카드의 상한 제한. (T와 그 자손들만 가능)
	<? super T> => 와일드 카드의 하한 제한. (T와 그 조상들만 가능)
	<?>			=> (허용 가능한) 모든타입 가능, 상황에 따라서 달라질 수 있음
 */

public class T05WildCardTest {
	
	public static void main(String[] args) {
		
		FruitBox<Fruit> fruitBox = new FruitBox<>(); // new 뒤에 <Fruit> 생략가능
		FruitBox<Apple> appleBox = new FruitBox<>(); 		
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		
		appleBox.add(new Apple());
		// appleBox.add(new Grape()); 안됨
		appleBox.add(new Apple());
		
		Juicer.makeJuice(fruitBox);
		Juicer.makeJuice(appleBox); // T 타입으로 바꿔주어서 잘 됨
		
		// WildCard ?
		List<?> myList = new ArrayList<String>();
		
	}
}

class Juicer{
	
	//static <T extends Fruit> void makeJuice(FruitBox<? extends Fruit> box) {
	static void makeJuice(FruitBox<? extends Fruit> box) { //제너릭 메소드 아님, 타입만 제너릭 메소드만 올 수 있게 함
		
		String fruitListStr = ""; //과일목록
		
		int cnt = 0;
		for(Object f : box.getFruitList()) {
			if(cnt == 0) {
				fruitListStr += f;
			}else {
				fruitListStr += "," + f;
			}
		}
		System.out.println(fruitListStr + " => 쥬스 완성!!");
	}
	
}

class Fruit {
	private String name; //과일이름

	public Fruit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "과일(" + name + ")";
	}
	
}

class Apple extends Fruit {
	
	public Apple() {
		super("사과");
	}
}

class Grape extends Fruit{
	
	public Grape() {
		super("포도");
	}
}

class FruitBox<T>{ // 과일상자
	
	private List<T> fruitList;
	
	public FruitBox() {
		fruitList = new ArrayList<T>();
	}

	public List<T> getFruitList() {
		return fruitList;
	}

	public void add(T fruit) {
		fruitList.add(fruit);
	}
	
}
