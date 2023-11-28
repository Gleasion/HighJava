package kr.or.ddit.basic;

import javax.swing.JOptionPane;

// 단일 스레드에서의 사용자 입력처리
public class T05ThreadTest {
	public static void main(String[] args) {
		
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		System.out.println("입력한 값은 " + str + "입니다");
		
		for(int i = 10; i >= 1; i--) {
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

// 사용자가 입력을 시간안에 할 수 있도록 하고자 함
// 단일 스레드로는 처리할 수 없으므로 T06에서 멀티 형태로 함 >> T06으로 가세요.