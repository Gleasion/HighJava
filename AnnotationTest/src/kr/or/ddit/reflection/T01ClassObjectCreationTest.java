package kr.or.ddit.reflection;

/* Java Reflection에 대하여

1. 런타임 시점에 클래스 또는 맴버변수, 메소드, 생성자 등에 대한 정보를 가져오거나 수정할 수있고
	새로운 객체를 생성하거나, 메소드를 실행할 수 있다.
	(컴파일 시점에 해당 정보를 알 수 없는 경우(소스코드의 부재)에 유용하게 사용될 수 있음)

2. Reflection API는 java.lang.reflect 패키지와 java.lang.Calss를 통해서 제공된다.

3. java.lang.Class의 주요 메소드
	- getName(), getSuperclass(), getInstance(), getModifiers() 등

4. java.lang.reflect 패키지의 주요 클래스
	- Field, Method, Constructor, Modifier 등
*/

//Class 오브젝트(클래스 정보를 담고 있는)를 생성하기
public class T01ClassObjectCreationTest{
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		// 1번째 방법 : Class.forName() 메소드 이용
		Class<?> klass = Class.forName("kr.or.ddit.reflection.T01ClassObjectCreationTest");
		
		// 2번째 방법 : getClass() 메소드 이용
		T01ClassObjectCreationTest obj = new T01ClassObjectCreationTest();
		klass = obj.getClass();
		
		// 3번째 방법 : .class 이용하는 방법
		klass = T01ClassObjectCreationTest.class;
	}
	
}
