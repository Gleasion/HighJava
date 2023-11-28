package kr.or.ddit.basic;


// 위치값 잘 나오게 해서 다시 제출
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeWorkRacing2 {
	
	public static void main(String[] args) {
		
		System.out.println("경기 시작");
		System.out.println();
		
		List<Horse2> horseList2 = new ArrayList<Horse2>();
		horseList2.add(new Horse2("1번 말"));
		horseList2.add(new Horse2("2번 말"));
		horseList2.add(new Horse2("3번 말"));
		horseList2.add(new Horse2("4번 말"));
		horseList2.add(new Horse2("5번 말"));
		horseList2.add(new Horse2("6번 말"));
		horseList2.add(new Horse2("7번 말"));
		horseList2.add(new Horse2("8번 말"));
		horseList2.add(new Horse2("9번 말"));
		horseList2.add(new Horse2("10번 말"));
		
		// 말 위치 출력 스레드
		Thread p = new Position(horseList2);
		
		// 말 경주 시작
		for(Horse2 horse : horseList2) {
			horse.start();
		}
		
		// 위치 출력 시작
		p.start();
		
		// 잠시 기다림
		try {
			p.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 순위별로 정렬
		Collections.sort(horseList2);
		
		// 결과/순위 출력
		System.out.println();
			System.out.println("=============================");
			System.out.println("\t [경기 끝!!!]");
			System.out.println("-----------------------------");
			System.out.println("\t [경기 결과]");
			System.out.println("-----------------------------");
			System.out.println("\t 순위    :  이름");
			System.out.println("=============================");
			
			for(Horse2 horse : horseList2) {
				System.out.println("\t" + horse.getRank() + "위     :  " + horse.getName());
			}

	}

}


// 말을 달리게 하고 순위를 오름차순으로 정렬
class Horse2 extends Thread implements Comparable<Horse2>{
	private String horseName;
	private int rank;
	private int position;
	public static int currentRank;
	
	public Horse2(String horseName) {
		super(horseName);
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public static int getCurrentRank() {
		return currentRank;
	}

	public static void setCurrentRank(int currentRank) {
		Horse2.currentRank = currentRank;
	}

	// 등수 오름차순 정렬
	@Override
	public int compareTo(Horse2 o) {
		return Integer.compare(this.getRank(), o.getRank());
	}
	
	@Override
	public void run() {
		
		for(int i = 1; i <= 50; i++) {
			setPosition(i);
		
			try {
				Thread.sleep((int)((Math.random() * 100) + 50));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setRank(++currentRank); // 순위정보 설정
		}
	}

}

//말의 위치를 출력하는 메소드
class Position extends Thread{
	private List<Horse2> hs;
	
	public Position(List<Horse2> hs) {
		super();
		this.hs = hs;
	}
	
	@Override
	public void run() {
		while(true) {
			if(Horse2.getCurrentRank() == hs.size()) {
				break;
			}
			
			for(int i = 1; i <= 10; i++) {
				System.out.println();
			}
			
			for(Horse2 h : hs) {
				System.out.print(h.getHorseName() + " : ");
				for (int i = 1; i <= 50; i++) {
					if(h.getPosition() == i) {
						System.out.print(">");
					}else {
						System.out.print("-");
					}
				}
					
				if(h.getPosition() == 50) {
					System.out.print(" 완주");
				}
				System.out.println();
			}
			
			try {
				Thread.sleep(200);				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	
}
