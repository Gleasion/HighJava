package kr.or.ddit.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class T03MethodMetaDataTest {

	public static void main(String[] args) {
		
		Class<?> clazz = SampleVO.class; 
		
		// 클래스에 선언된 모든 메소드의 메타데이터 정보 가져오기
		Method[] methodArr = clazz.getDeclaredMethods();
		
		for(Method m : methodArr) {
			System.out.println("메소드 명 : " + m.getName());
			System.out.println("메소드 리턴타입 : " + m.getReturnType());
			
			// 해당 메소드의 접근 제어자 정보 가져오기
			int modFlag = m.getModifiers();
			System.out.println("메소드 접근제어자 : " + Modifier.toString(modFlag));
			
			// 해당 메소드의 파라미터 타입 가져오기
			Class<?>[] paramArr = m.getParameterTypes();
			System.out.println("메소드 파라미터 타입 : ");
			for(Class<?> klass : paramArr) {
				System.out.print(klass.getName() + " | ");
			}
			System.out.println();
			
			// 해당 메소드에서 던지는 예외타입 가져오기
			Class<?>[] exTypeArr = m.getExceptionTypes();
			System.out.println("메소드에서 던지는 예외 타입 목록");
			for(Class<?> klass : exTypeArr) {
				System.out.print(klass.getName() + " | ");
			}
			System.out.println();
			
			// 해당 메소드에 존재하는 annotation 정보 가져오기
			Annotation[] annos = m.getDeclaredAnnotations();
			System.out.println("애너테이션 정보 : ");
			for(Annotation anno : annos) {
				System.out.print(anno.annotationType().getName() + " | ");
			}
			
			System.out.println();
			
			System.out.println("---------------------------------------------");
			
			
		}
		
	}

}
