package kr.or.ddit.basic;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PrintAnnotationTest {

	public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class<?> clazz = Class.forName("kr.or.ddit.basic.Service");
		
		// 선언된 메소드에 접근하기
		Method[] methodArr = clazz.getDeclaredMethods();
		
		for(Method m : methodArr) {
			System.out.println("메소드 이름 : " + m.getName()); //메소드 명 출력
			
			Annotation[] annos = m.getDeclaredAnnotations();
			
			for(Annotation anno : annos) {
				if(anno.annotationType().getSimpleName().equals("PrintAnnotation")) {
					PrintAnnotation printAnno = (PrintAnnotation) anno;
					System.out.println(printAnno.value() + " : " + printAnno.count()
								+ " : " + printAnno.myValue());
				}
			}
			
			m.invoke(new Service());
			
			
		}
		
	
	}

}
