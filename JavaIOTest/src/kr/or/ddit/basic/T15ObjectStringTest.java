package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T15ObjectStringTest {
	public static void main(String[] args) {
		// Member 객체 생성하기 
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("일지매", 30, "광주");
		Member mem3 = new Member("강감찬", 50, "부산");
		Member mem4 = new Member("성춘향", 40, "전주");
		
		ObjectOutputStream oos = null;
		
		try {
			//객체를 파일에 저장하기
			
			//출력용 스트림 객체 생성 
			oos = new ObjectOutputStream(
					   new BufferedOutputStream( //입출력 성능 향상을 위해 사용
							new FileOutputStream("d:/D_Other/memObj.bin")));
					
			//객체 쓰기 작업
			oos.writeObject(mem1); //직렬화
			oos.writeObject(mem2); //직렬화
			oos.writeObject(mem3); //직렬화
			oos.writeObject(mem4); //직렬화
			
			System.out.println("객체 저장 작업 완료...");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				oos.close();
		    }catch (IOException e) {
		    	e.printStackTrace();
		    }
	}
		/////////////////////////////////////////////////////////
		
		ObjectInputStream ois = null;
		
		try { 
			//저장한 객체를 읽어와 출력하기
			
			//입력용 스트림 객체 생성 
			ois = new ObjectInputStream(
						new BufferedInputStream(
							new FileInputStream("d:/D_Other/memObj.bin")));
			
			Object obj = null;
			
			while((obj = ois.readObject()) !=null) { // 역직렬화
				// 원래 타입의 객체형으로 캐스팅 한 후 사용한다. 
				Member mem = (Member) obj;
				System.out.println("이름 : " + mem.getName());
				System.out.println("나이 : " + mem.getAge());
				System.out.println("주소 : " + mem.getAddr());
				System.out.println("-------------------------");	
			}
			
		} catch (IOException ex) {
			//마지막에 다다르면 EOF 예외가 발생함. 
			//ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
}

/**
 * 회원정보를 담기 위한 VO클래스 
 * @author PC-14
 */
class Member implements Serializable { // 자바는 Serializable 인터페이스를 구현한 클래스만 직렬화할 수 있도록
	                                   // 제한하고 있음 
	private String name;
	
	/*
	   transient => 직렬화 대상에서 제외시키고자 할 때 사용 
	                  직렬화가 되지 않는 멤버변수는 기본값으로 저장된다. 
	         (참조형 변수: null, 숫자형 변수: 0)
	         (static 필드도 직렬화 되지 않는다.)
	 */
	
	private transient int age; //역직렬화 했을 때 null
	private transient String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}


