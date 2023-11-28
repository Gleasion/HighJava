package kr.or.ddit.basic;

//멀티스레드 프로그램
public class T02ThreadTest {
	public static void main(String[] args) {
		
/* 방법1
 * Thread클래스를 상속한 클래스의 인스턴스를 생성한 후 이 인스턴스의 start() 메서드를 호출한다. */
	Thread th1 = new MyThread1();
	//th.run(); //메인이 작업
	th1.start(); //각 메소드가 작업
	
/* 방법2
 * Runnable 인터페이스를 구현한 클래스의 인스턴스를 생성한 후
 * 이 인스턴스를 Thread 객체를 생성할 때 생성자의 매개변수로 넘겨준다.
 * 이렇게 생성한 스레드객체의 start() 메소드를 호출한다. */	
	Runnable r = new MyThread2();
	Thread th2 = new Thread(r);
	//th2.run(); //메인이 작업
	th2.start(); //각 메소드가 작업
	
/* 방법3
 * 익명클래스를 이용
 * Runnable 인터페이스를 구현한 익명클래스를 Thread객체를 생성할 때 매개변수로 넘겨준다. */
	Thread th3 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			for(int i = 1; i <=200; i++) {
				System.out.print("@");
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});
	//th3.run(); //메인이 작업
	th3.start(); //각 메소드가 작업
	
	System.out.println("메인스레드종료..."); //메인 스레드 먼저 사라짐
	}
}

// 방법1
class MyThread1 extends Thread {
	
	@Override
	public void run() {
		for(int i = 1; i <=200; i++) {
			System.out.print("*");
			
			try {
				// Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다.
				// (1초 = 1000밀리세컨드)
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

// 방법2
class MyThread2 implements Runnable {

	@Override
	public void run() {
		for(int i = 1; i <=200; i++) {
			System.out.print("$");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
