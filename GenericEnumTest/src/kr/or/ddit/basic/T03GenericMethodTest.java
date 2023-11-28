package kr.or.ddit.basic;

class Util{
/*
	제너릭 메소드<T, R> R method(T t)
	=> 파라미터 타입과 리턴타입으로 타입글자를 가지는 메소드
	선언방법 : 리턴타입 앞에 <> 기호를 추가하고 타입글자를 기술 후 사용함.
 */
	
	public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
		
		boolean keyCompare = p1.getKey().equals(p2.getKey());
		boolean valueCompare = p1.getVal().equals(p2.getVal());
		
		return keyCompare && valueCompare;
	}
}

//멀티타입<K, V>를 가지는 제너릭 클래스
class Pair<K, V>{
	private K key;
	private V val;
	
	public Pair(K key, V val) {
		super();
		this.key = key;
		this.val = val;
	}
	
	public K getKey() {
		return key;
	}
	
	public void setKey(K key) {
		this.key = key;
	}
	
	public V getVal() {
		return val;
	}
	
	public void setVal(V val) {
		this.val = val;
	}
	
	//우선순위는 전역변수보다 지역변수가 더 높다
	public <K, V> void displayAll(K key, V val) {
		System.out.println(key + " : " + val);
	}
	
}

public class T03GenericMethodTest {

	public static void main(String[] args) {
		
		Pair<Integer, String> p1 = new Pair<Integer, String>(1, "홍길동");
		Pair<Integer, String> p2 = new Pair<Integer, String>(1, "홍길동");
		
		// compare 메소드도 제너릭 메소드이지만 명시적으로 타입을 써주는 것이 정석이지만 밑에서는 생략해줌
		boolean result1 = Util.<Integer, String>compare(p1, p2); 
		
		if(result1) {
			System.out.println("논리적으로 동일한 객체임");
		}else {
			System.out.println("논리적으로 동일한 객체 아님");
		}
		
		System.out.println();
		
		Pair<String, String> p3 = new Pair<String, String>("001", "홍길동");
		Pair<String, String> p4 = new Pair<String, String>("002", "홍길동");
		
		// 타입을 이미 알 수 있어서 .<Integer, String> 생략해줌
		boolean result2 = Util.compare(p3, p4);
		
		if(result2) {
			System.out.println("논리적으로 동일한 객체임");
		}else {
			System.out.println("논리적으로 동일한 객체 아님");
		}
		
		///////////////////////////////////////////////////////
		
		System.out.println();
		p1.displayAll(100, "180");
		//.<String, Integer> 생략가능
		p1.displayAll("키", 180); // 제너릭 메소드가 되어서 가능

	}

}
