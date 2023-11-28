package kr.or.ddit.basic;

//interrupt() 메소드를 이용하여 스레드를 멈추게 하는 방법

/* 방법 1-2.
 * sleep()메소드나 join()메소드 등을 사용 했을 때 interrupt()를 호출
 * => InterruptedException이 발생하며 멈춤 */

public class T13ThreadStopTest2 {
	public static void main(String[] args) {
		
		ThreadStopEx2 th2 = new ThreadStopEx2();
		th2.start();
	
		try {
			Thread.sleep(1000); // 1초 동안 잠자고
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		th2.interrupt(); // 인터럽트 걸기
	
	}
}

// while 안에 sleep걸어서 예외 발생되도록 함
class ThreadStopEx2 extends Thread{
	
	@Override
	public void run() {
		
		try {
			while(true) {
				System.out.println("스레드 작업 처리 중...");
				Thread.sleep(1); // while문 안에 걸어줌
			}
		}catch(InterruptedException ex) {
			System.out.println("인터럽트 발생했어요!!!!");
		}

		// interrupt 안걸면 무한루프를 돌기때문에 밑에 실행이 안되므로 오류..
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료.");
		
	}
}

