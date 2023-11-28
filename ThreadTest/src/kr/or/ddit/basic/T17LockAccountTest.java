package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Lock 객체를 이용한 동기화 처리

/* 락 기능을 제공하는 클래스
 * 1. ReentrantLock : Read 및 Write 구분없이 사용
 *					synchronized를 이용한 동기화 처리보다 부가적인 기능이 제공
 *					ex) Fairness 설정 등 => 가장 오래 기다린 스레드가 가장 먼저 락을 획득하게 함
 *
 * 2. ReentrantReadWriteLock : Read 및 Write 락을 구분하여 사용
 *					=> Read : 여러 스레드 동시 가능
 *					=> Write: 하나의 스레드만 가능
 *					=> 작업량 : Read > Write 인 경우 사용하면 처리량(Throughput)이 좋아진다. */

// 은행의 입출금 작업을 스레드로 처리하는 예제2.
public class T17LockAccountTest {
	public static void main(String[] args) {
		
		// 공정성(Fairness) 정책을 사용하는 ReentrantLock 인스턴스를 생성
		ReentrantLock lock = new ReentrantLock(true); // 락을 요청한 순서대로 스레드에게 락을 부여
		
		LockAccount lAcc = new LockAccount(lock);
		
		lAcc.deposit(10000);
		
		Thread th1 = new BankThread2(lAcc);
		Thread th2 = new BankThread2(lAcc);
		
		th1.start();
		th2.start();
	}

}

// 입출금 처리를 위한 클래스(공유 객체용)
class LockAccount{
	private int balance;
	
	// lock 객체변수 => 되도록 private final로 만든다.
	private final Lock lock;
	
	public LockAccount(Lock lock) {
		this.lock = lock;
	}
	
	public int getBalance() {
		return balance;
	}
	
// 입금하는 메소드
	public void deposit(int money) {
		
		// Lock 객체의 lock()메소드가 동기화 시작이고, unlock() 메소드가 동기화의 끝을 나타낸다.
		// lock() 메소드로 동기화를 설정한 곳에서는 반드시 unlock() 메소드로 해제해주어야한다.
		
		lock.lock(); // 동기화 시작, 락 설정(락을  획득하기 전에까지 BLOCKED 상태 됨)
		balance += money; // 동기화 처리부분
		lock.unlock(); // 동기화 해제, 락 해제
	}
	
// 출금하는 메소드
	public boolean withdraw(int money) {
		boolean chk = false;
		
		// try ~ catch 사용할 경우에는 finally 에서 unlock() 메소드 호출
		try {
			lock.lock(); // 동기화 시작
		
			if(balance >= money) { // 잔액이 충분하면
				for(int i = 1; i <= 1000000000; i++) {} //시간 때우기 용
				balance -= money;
				System.out.println("메소드 안에서 balance = " + getBalance());
				chk = true;
			}else {
				chk = false;
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			lock.unlock(); // 동기화 해제, unlock를 안해주면 계속 기다리고 있음(얼어있음)
		}
		
		return chk;
	}	
}

//은행업무를 처리하기 위한 스레드
class BankThread2 extends Thread {
	private LockAccount lAcc;
	
	public BankThread2(LockAccount lAcc) {
		this.lAcc = lAcc;
	}
	
	@Override
	public void run() {
		boolean result = lAcc.withdraw(6000); // 6000원 인출
		System.out.println("스레드 안에서 result = " + result 
				+ ", balance = " + lAcc.getBalance());
	}
}
