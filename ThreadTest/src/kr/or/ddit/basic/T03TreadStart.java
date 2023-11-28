package kr.or.ddit.basic;

// 스레드의 수행시간 체크하기
public class T03TreadStart {
	public static void main(String[] args) {
		
		Thread th = new Thread(new MyRunner());
		
		/* UTC(Universal Time Coordinated 협정 세계 표준시)를 사용하여
		 * 1970년 1월 1일 0시0분0초를 기준으로  경과한 시간을 밀리세컨드(1/1000초)단위로 나타낸다. */
		long startTime = System.currentTimeMillis(); // 시작시간
		
		th.start();
		
		try {
			// .join() 현재 스레드의 스택 추적
			th.join(); // 현재 실행 중인 스레드에서 작업중인 스레드(지금은 th)가 종료될때까지 기다린다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis(); // 끝나는 시간
		
		System.out.println("경과 시간(ms) : " + (endTime - startTime)); // 현재시간
	}
}

// 1~10억까지의 합계를 구하는 스레드
class MyRunner implements Runnable {

	@Override
	public void run() {
		long sum = 0;
		for(int i = 1; i<=1000000000; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
}



