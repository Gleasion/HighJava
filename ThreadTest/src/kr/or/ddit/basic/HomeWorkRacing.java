package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 10마리의 경주마가 달리는스레드 (그림이 이쁘게 안나옴)
public class HomeWorkRacing {
	
	static int CURR_RANK = 1; // 현재 순위 정보
	
	public static void main(String[] args) {
		
		List<RunningHorse> runHorseList = new ArrayList<>();
		
		for(int i = 1; i <= 10; i++) {
			runHorseList.add(new RunningHorse(i +"번 말"));
		}
		
		for(RunningHorse rhl : runHorseList) {
			rhl.start();
		}
		
		for(RunningHorse rhl : runHorseList) {
			try {
				rhl.join();
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
		
		Collections.sort(runHorseList);
		
		for(RunningHorse rhl : runHorseList) {
			System.out.println(rhl.getRank() + "\t:\t" + rhl.getName());
		}
		System.out.println("------------------------");
		
	}
}

// 경주마 달리는 스레드
class RunningHorse extends Thread implements Comparable<RunningHorse>{
	private String name;
	private int rank;
	
	public RunningHorse(String name) {
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
		System.out.print(name + " : ");
		
		for(int i = 1; i <= 50; i++) {
			for(int j = 1; j <= 50; j++) {
				System.out.print("-");
			}
			System.out.println(">");
			System.out.println();
			
			try {
				Thread.sleep((int)(Math.random())*1000 + 50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(name + "경기 종료");
		
		setRank(HomeWorkRacing.CURR_RANK++);
	}

	@Override
	public int compareTo(RunningHorse rh) {
		return new Integer(this.getRank()).compareTo(rh.getRank());
	}
	
}