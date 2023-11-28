package kr.or.ddit.basic;

/* yield() 메소드
 * 1. 현재 실행 대기 중인 동등한 우선순위 이상의 다른 스레드에게 실행 기회를 제공한다.(양보)
 * 2. 현재 실행중인 스레드의 상태를 Runnable 상태로 바꾼다.
 * 3. yield()메소드를 실행한다고 해서 현재 실행중인 스레드가 곧바로
	RUNNABLE 상태로 전이된다고 확신할 수 없다. */

// 스레드 양보하기
public class T12ThreadYieldTest {
	public static void main(String[] args) {
		
		Thread th1 = new YieldThreadEx1();
		Thread th2 = new YieldThreadEx2();

		th1.start();
		th2.start();	
	}

}

class YieldThreadEx1 extends Thread{
	public YieldThreadEx1() {
		// default 이름은 스레드-1
		// super안에 이름을 지정해주면 스레드의 이름을 줄 수 있음
		super("양보 스레드");
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
		
			for(int j = 0; j <= 1000000000; j++) {}
			
			Thread.yield(); //양보하기	
		}
	}
}

class YieldThreadEx2 extends Thread{
	public YieldThreadEx2() {
		super("비양보 스레드");
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
			
			for(int j = 0; j <= 1000000000; j++) {}
		}
	}
}