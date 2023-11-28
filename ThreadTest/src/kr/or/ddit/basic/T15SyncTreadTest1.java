package kr.or.ddit.basic;

/* 동기화 방법
 * 1. 메소드 자체에 동기화 설정하기 => 1개의 스레드만 처리하게 해줌
 * 2. 동기화 블럭으로 설정하기
 */

// 스레드 공통객체 동기화
public class T15SyncTreadTest1 {
	public static void main(String[] args) {
		
		ShareObject sObj = new ShareObject();
		
		Thread th1 = new WorkerThread("1번 스레드", sObj);
		Thread th2 = new WorkerThread("2번 스레드", sObj);
		Thread th3 = new WorkerThread("3번 스레드", sObj);
		
		th1.start();
		th2.start();
		th3.start();
	}
}

// 공통으로 사용할 객체
class ShareObject{
	private int sum;
	
	// 방법 1.
	public synchronized void add() {
		for(int i = 0; i < 1000000000; i++) {} // 동기화 처리 전까지의 시간벌기용

			int n = sum;
			n += 10; // 10증가
			sum = n;
		
			System.out.println(Thread.currentThread().getName() + "합계 : " + sum);

	}
}

// 작업을 수행하는 스레드
class WorkerThread extends Thread {
	private ShareObject sObj;
	
	public WorkerThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			
			synchronized (sObj) {
				sObj.add();
			}
		}
	}
	
}