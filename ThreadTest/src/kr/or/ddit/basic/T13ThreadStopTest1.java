package kr.or.ddit.basic;

//interrupt() 메소드를 이용하여 스레드 멈추기

/* 방법 1-1.
 * sleep()메소드나 join()메소드 등을 사용 했을 때 boolean 값의 isStopped을 이용하는 방법 */

public class T13ThreadStopTest1 {
	public static void main(String[] args) {
		
		ThreadStopEx1 th1 = new ThreadStopEx1();
		th1.start();
		
		try {
			Thread.sleep(1000); // 1초동안 잠자고
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		//th1.stop(); //강제종료와 같은 기능으로 되도록 쓰지 말기
		th1.setStopped(true); // isStopped을 true로
	}
}

// 무한루프를 하나 돌리고 적절한 시간에 stop 걸기
class ThreadStopEx1 extends Thread {
	private boolean isStopped; // default false

	public void setStopped(boolean isStopped) {
		this.isStopped = isStopped;
	}
	
	@Override
	public void run() {
		while(!isStopped) { // false가 아니라면, 즉, true여서 무한반복
			System.out.println("스레드 처리 중..."); // 스레드 계속 처리
		}
		
		// true에 걸려 false가 됨
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료.");
	}
	
}



