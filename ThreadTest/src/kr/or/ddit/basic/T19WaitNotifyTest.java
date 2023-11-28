package kr.or.ddit.basic;

/* 동기화 처리를 위한 메소드  
 1. wait() => 동기화 영역에서 락을 풀고 Wait-Set영역(공유객체별 존재)으로 이동 시킴
  
 2. notify() 또는 notifyAll() => Wait-Set 영역에 있는 스레드를 깨워서 실행될 수 있도록 한다.
  (notify()는 하나 / notifyAll()은 Wait-Set에 있는 전부를 깨움)
 
 3. => wait()과, notify(), notifyAll() 메소드는 Object 클래스에서 제공하는 메소드이고, 동기화 영역에서만 실행할 수 있음 */

public class T19WaitNotifyTest {
	public static void main(String[] args) {
		
		WorkObject workObj = new WorkObject();
		
		Thread thA = new ThreadA(workObj);
		Thread thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
	}
}

// 공통으로 사용할 객체
class WorkObject{
	
	public synchronized void methodA() {
		System.out.println("methodA()메소드 작업 중...");
		
		System.out.println(Thread.currentThread().getName() + ": notify() 호출");
		notify();
		
		try {
			System.out.println(Thread.currentThread().getName() + ": wait() 호출");
			wait();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public synchronized void methodB() {
		System.out.println("methodB()메소드 작업 중...");
		
		System.out.println(Thread.currentThread().getName() + ": notify() 호출");
		notify();
		
		try {
			System.out.println(Thread.currentThread().getName() + ": wait() 호출");
			wait(3000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}

// WorkObject의 methodA만 호출하는 스레드
class ThreadA extends Thread{
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObject) {
		super("ThreadA");
		this.workObj = workObject;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 5; i++) {
			workObj.methodA();
		}
		System.out.println("ThreadA 종료...");
	}
}

//WorkObject의 methodB만 호출하는 스레드
class ThreadB extends Thread{
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObject) {
		super("ThreadB");
		this.workObj = workObject;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 5; i++) {
			workObj.methodB();
		}
		System.out.println("ThreadB 종료...");
	}
}

