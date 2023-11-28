package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 스레드의 완료 순서대로 결과 출력

/* 1. 알파벳 대문자를 출력하는 각각의 3개(명)의 스레드
 * 2. 출력을 끝낸 순서대로 결과를 나타내는 프로그램을 작성(sleep 시간을 랜덤하게) */

public class T11DisplayCharacterTest {

	static int CURR_RANK = 1; // 현재 순위 정보
	
	public static void main(String[] args) {
		
		List<DisplayCharacter> disCharList = new ArrayList<DisplayCharacter>();
		disCharList.add(new DisplayCharacter("홍길동"));
		disCharList.add(new DisplayCharacter("성춘향"));
		disCharList.add(new DisplayCharacter("이순신"));
		disCharList.add(new DisplayCharacter("곽재우"));
		
		for(DisplayCharacter dc : disCharList) {
			dc.start();
		}
		
		for(DisplayCharacter dc : disCharList) {
			try {
				dc.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println("-------------------------");
		System.out.println("\t경기 끝...");
		System.out.println("-------------------------");
		System.out.println("\t경기 결과");
		System.out.println("=========================");
		System.out.println("순위\t:\t이름");
		
		Collections.sort(disCharList);
		
		for(DisplayCharacter dc : disCharList) {
			System.out.println(dc.getRank() + "\t:\t" + dc.getName());
		}
		System.out.println("-------------------------");
	
	}

}

// 영어 대문자를 출력하는 스레드
class DisplayCharacter extends Thread implements Comparable<DisplayCharacter>{
	private String name;
	private int rank;
	
	public DisplayCharacter(String name) {
		super(name);
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public void run() {
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(name + "의 출력 문자 : " + ch);
			
			// 난수 활용으로 누가 이길지 예측 불가
			try {
				Thread.sleep((int)(Math.random() * 301 + 200)); // 200~500 난수 이용
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + "출력 끝...");
		
		setRank(T11DisplayCharacterTest.CURR_RANK++); // 순위정보 설정
	}

	@Override
	public int compareTo(DisplayCharacter dc) {
		return new Integer(this.getRank()).compareTo(dc.getRank());
	}
	
}
