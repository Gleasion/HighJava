package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Set;

public class T07EqualsHashCodeTest {
/*
 해쉬함수(hash function)는 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수이다.
 해시함수에 의해 얻어지는 값은 해시값, 해시코드, 해시체크섬 또는 간단하게 해시라고 부른다.
 
 HashSet, HashMap, Hashtable 같은 객체들을 사용할 경우...
 객체가 서로 같은지를 비교하기 위해 equals() 메소드와 hashCode() 메소드를 호출한다.
 그래서 객체가 서로 같은지 여부를 결정하려면 두 메소드를 적절히 재정의 해주어야한다.
 객체가 같은지 여부는 데이터를 추가할 때 검사한다.
 
 - equals()와 hashCode() 메소드에 관련된 규칙
 1. 두 객체가 같으면 반드시 같은 해시코드를 가져야 한다.
 2. 두 객체가 같으면 equals() 메소드를 호출했을 때 true를 반환해야 한다.
 	즉, 객체 a, b가 같다면 a.equals(b)와 b.equals(a) 둘다 true 이어야한다.
 3. 두 객체의 해시값이 같다고 해서 두 객체가 반드시 같은 객체는 아니다.
 	하지만, 두 객체가 같으면 반드시 해시값이 같아야한다.
 4. equals()를 재정의하면 반드시 hashCode()도 재정의 해주어야 한다.
 5. hashCode()는 기본적으로 힙메모리에 존재하는 각 객체에 대한 메모리 주소 매핑정보를 기반으로 한 정수값을 반환한다.
 	그러므로, 해당 클래스에서 재정의 하지 않으면 절대로 해당 객체는 같은 객체로 간주될 수 없다(해시코드가 다르기 때문에)
 	
 	-hashCode()에서 사용하는 '해싱 알고리즘'에서 서로 다른 객체에 대해서 같은 hashCode 값을 만들어 낼 수 있다.
 	그래서 객체가 같지 않더라도 hashCode가 같을 수 있다.
 */

	public static void main(String[] args) {
		
		System.out.println("Aa".hashCode());
		System.out.println("BB".hashCode());
		
		Person p1 = new Person(1, "홍길동");
		Person p2 = new Person(1, "홍길동");
		Person p3 = new Person(1, "이순신");
		
		//override 안해줘서 Object의 equals로 자동으로 처리됨
		System.out.println("p1.equals(p2) : " + p1.equals(p2));
		System.out.println("p1 == p2 : " + (p1 == p2));
		
		Set<Person> hs = new HashSet<Person>();
		// Set은 중복을 허용하지 않지만 p1과 p2는 같지 않다고 판단하였음
		// 따라서, equals와 hashCode 메소드를 Override 해주면 정상적으로 처리
		System.out.println("add(p1) 성공여부 : " + hs.add(p1));
		System.out.println("add(p2) 성공여부 : " + hs.add(p2));
		
		System.out.println("p1, p2 등록 후 데이터 : ");
		for(Person p : hs) {
			System.out.println(p.getId() + " : " + p.getName());
		}
		
		System.out.println("add(p3) 성공여부 : " + hs.add(p3));
		System.out.println("p3 등록 후 데이터 : ");
		for(Person p : hs) {
			System.out.println(p.getId() + " : " + p.getName());
		}
		
		System.out.println("remove(p2) 성공여부 : " + hs.remove(p2));
		System.out.println("remove(p2) 후 데이터 : ");
		for(Person p : hs) {
			System.out.println(p.getId() + " : " + p.getName());
		}
		
	}
	
}

class Person{
	
	private int id;
	private String name;
		
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		Person p = (Person) obj;
//		return this.getId() == p.getId() && this.getName().equals(p.getName());
//	}
//	
//	@Override
//	public int hashCode() {
//		return (name + id).hashCode();
//	}
//	
	
}








