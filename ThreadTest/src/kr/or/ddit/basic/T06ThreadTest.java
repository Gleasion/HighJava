package kr.or.ddit.basic;

import javax.swing.JOptionPane;

//멀티스레드를 활용한 카운트다운 처리
public class T06ThreadTest {

	//입력 여부를 확인하기 위한 변수 선언
	static boolean inputCheck = false;
	
	public static void main(String[] args) {
		
		Thread th1 = new DataInput();
		Thread th2 = new CountDown();
		
		th1.start();
		th2.start();
		
	}

}

// 데이터를 입력받기 위한 스레드
class DataInput extends Thread {
	
	@Override
	public void run() {

		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		
		// 입력이 완료되면  true로 설정한다. 
		T06ThreadTest.inputCheck = true;
		
		System.out.println("입력한 값은 " + str + "입니다");
	}
}

// 카운트다운 처리를 위한 스레드
class CountDown extends Thread {
	
	@Override
	public void run() {
		
		for(int i = 10; i >= 1; i--) { // 10초 안에 입력
			
			if(T06ThreadTest.inputCheck) { //입력이 완료되면
				return; // run()메소드가 종료되면 스레드도 끝난다.
			}
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// 10초가 경과되었는데도 입력이 없으면 프로그램을 종료한다.
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0); // 프로그램 강제 종료
	}
}
