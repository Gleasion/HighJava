package kr.or.ddit.basic;

// interrupt() 메소드를 이용
// 방법 2. Interrupt() 메소드가 호출되었는지 검사하기
public class T13ThreadStopTest3 {

	public static void main(String[] args) {
		
		ThreadStopEx3 th3 = new ThreadStopEx3();
		th3.start();
	
		try {
			Thread.sleep(1000); // 1초 동안 잠자고
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		th3.interrupt(); // 인터럽트 걸기
	
	}
}

// 검사 방법 2-1. => 스레드의 인스턴스용 메서드를 이용하는 방법
class ThreadStopEx3 extends Thread {
		private boolean isStopped;
		
		public void setStopped(boolean isStopped) {
			this.isStopped = isStopped;
		}
		
		@Override
		public void run() {
			while(!isStopped) {
				System.out.println("스레드 처리 중...");
			
				if(this.isInterrupted()) {
					System.out.println("인스턴스용 isInterrupted() 호풀됨");
					break;
				}
			}
			System.out.println("자원 정리 중...");
			System.out.println("실행 종료.");
		}
		
	}
