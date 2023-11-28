package kr.or.ddit.basic;

/* 스레드 상태
 * NEW : 스레드가 생성되고 아직 start()가 호출되지 않은 상태
 * RUNNABLE : 실행 중 또는 실행 가능한 상태
 * BLOCKED : 동기화블럭에 의해서 일시정지된 상태(LOCK이 풀릴때까지 기다리는 상태)
 * WAITING, TIMED_WAITING(일시정지 시간 지정) : 스레드의 작업이 종료되지는 않았지만 실행가능하지 않은 일시정지 상태
 * TERMINATED : 스레드의 작업이 종료된 상태 */

// 스레드 상태 알아보기
public class T10ThreadStateTest {
	public static void main(String[] args) {
		
		Thread target = new TargetThread();
		
		StatePrintThread spt = new StatePrintThread(target);
		spt.start();
	}
}

// 스레드의 상태를 출력하는 스레드
class StatePrintThread extends Thread{
	
	private Thread targetTread; // 상태를 출력할 스레드가 저장될 변수
	
	public StatePrintThread(Thread TargetThread) {
		this.targetTread = TargetThread;
	}
	
	@Override
	public void run() {
		
		while(true) {
			
			Thread.State state = targetTread.getState(); // 스레드의 상태 구하기
			System.out.println("타겟 스레드의 상태값 : " + state);
			
			if(state == Thread.State.NEW) { // NEW 상태인지 검사
				targetTread.start();
			}
			
			if(state == Thread.State.TERMINATED) { // 타겟스레드가 종료 상태인지 검사
				
				//targetTread.start(); // 비가역성으로 안끝나고 한방향임
				
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}

// 모니터링 대상 스레드
class TargetThread extends Thread{
	
	@Override
	public void run() {
		for(long i = 1; i <= 1000000000; i++) {} // 시간 지연용
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		
		for(long i = 1; i <= 1000000000; i++) {} // 시간지연용
	}
}