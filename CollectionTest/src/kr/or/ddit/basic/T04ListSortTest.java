package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T04ListSortTest {

	public static void main(String[] args) {
		List<Member> memList = new ArrayList<Member>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "변학도", "010-2222-2222"));
		memList.add(new Member(9, "성춘향", "010-3333-3333"));
		memList.add(new Member(3, "이순신", "010-4444-4444"));
		memList.add(new Member(6, "강감찬", "010-5555-5555"));
		memList.add(new Member(2, "일지매", "010-6666-6666"));
		
		System.out.println("정렬 전 : ");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		
		System.out.println();
		
		Collections.sort(memList);
		
		System.out.println("정렬 후 : ");
		for(int i = 0; i < memList.size(); i++) {
			System.out.println(memList.get(i));
		}
		
		Collections.shuffle(memList);
		System.out.println("섞은 후 : ");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		
		Collections.sort(memList, new sortNumDesc());
		System.out.println("외부 정렬자를 이용한 정렬 후: ");
		for(Member mem : memList) {
			System.out.println(mem);
		}
	}
}

//Member의 번호(num)에 대해 내림차순으로 정렬하기
class sortNumDesc implements Comparator<Member>{

	@Override
	public int compare(Member mem1, Member mem2) {
		
//		if(mem1.getNum() > mem2.getNum()) {
//			return -1;
//		}else if(mem1.getNum() == mem2.getNum()) {
//			return 0;
//		}else {
//			return 1;
//		}
		
		//Wrapper 클래스에서 제공하는 메소드 이용방법
		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
	}	
}

// 회원 이름을 기준으로 오름차순 정렬이 되도록 구현
class Member implements Comparable<Member> {
	private int num;		//번호
	private String name;	//이름
	private String tel;		//전화번호
	
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	// 회원 이름을 기준으로 오름차순 정렬이 되도록 구현
	@Override
	public int compareTo(Member mem) { //본인과 비교 this
		return this.getName().compareTo(mem.getName());
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}
	
	
}
	