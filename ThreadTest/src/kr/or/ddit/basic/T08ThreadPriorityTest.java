package kr.or.ddit.basic;

// 스레드 처리시 우선순위를 정할 수 있음

// 소문자와 대문자를 출력하는 스레드를 만들어 우선순위를 줘보기
public class T08ThreadPriorityTest {
	public static void main(String[] args) {
		
		System.out.println("최대 우선순위 : " + Thread.MAX_PRIORITY); // CPU 10 할당
		System.out.println("최소 우선순위 : " + Thread.MIN_PRIORITY); // CPU 1 할당
		System.out.println("보통 우선순위 : " + Thread.NORM_PRIORITY); // CPU 5 할당

		Thread[] ths = new Thread[] {
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest1(),
			new ThreadTest2() // ths[5], 소문자 출력 스레드
		};
	
		// 우선 순위는 start()메소드를 호출하기 전에 설정해야한다.
		for(int i = 0; i < ths.length; i++) {
			if(i == 5) {
				ths[i].setPriority(10); //소문자 스레드에 우선순위 높임
			}else {
				ths[i].setPriority(1);
			}
		}	
		
		//현재 설정&할당된 우선순위 출력
		for(Thread th : ths) {
			System.out.println(th.getName() + "의 우선순위 : " + th.getPriority());
		}
		
		//스레드시작
		for(Thread th : ths) {
			th.start();
		}
	}
}

// 대문자를 출력하기 위한 스레드
class ThreadTest1 extends Thread{
	
	@Override
	public void run() {
		for(char ch = 'A'; ch <= 'Z'; ch++) {
			System.out.println(ch);
			
			// 아무것도 하지 않는 반복문(시간 때우기용)
			for(int i = 1; i <= 1000000000; i++) {}
		}
	}
}

// 소문자를 출력하기 위한 스레드
class ThreadTest2 extends Thread{
	
	@Override
	public void run() {
		for(char ch = 'a'; ch <= 'z'; ch++) {
			System.out.println(ch);
			
			// 아무것도 하지 않는 반복문(시간 때우기용)
			for(int i = 1; i <= 1000000000; i++) {}
		}
	}
}
