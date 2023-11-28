package kr.or.ddit.basic;

// 1~20억까지의 합계를 구하는데 걸린시간 체크하기

/* 1. 전체 합계를 구하는 작업을 단독으로 했을 때 (1개의 스레드를 이용했을때)
 * 2. 여러개의 스레드로 분할해서 작업했을 때 */
public class T04ThreadTest {
	public static void main(String[] args) {

	// 1. 단독처리
		Thread th1 = new SumThread(1L, 2000000000L);
		
		long startTime = System.currentTimeMillis();
		
		th1.start();
		
		try {
			th1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독으로 처리할 때의 처리 시간 : " + (endTime - startTime));
		System.out.println("\n\n");
		
		
	// 2. 여러개 처리
		Thread[] ths = new Thread[] {
			new SumThread(         1L,  500000000L),
			new SumThread( 500000001L, 1000000000L),
			new SumThread(1000000001L, 1500000000L),
			new SumThread(1500000001L, 2000000000L),
		};
		
		startTime = System.currentTimeMillis();
		
		for(Thread th : ths) {
			th.start();
		}
		
		for(Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		endTime = System.currentTimeMillis();
		
		System.out.println("협력 처리 했을 때의 처리시간 : " + (endTime - startTime));
	}
}

// 공통 사용
class SumThread extends Thread {
	private long min, max;
	
	public SumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public void run() {
		long sum = 0;
		for(long i = min; i <= max; i++) {
			sum += i;
		}
		System.out.println(min + " ~ " + max + "까지의 합 : " + sum);
	}
}