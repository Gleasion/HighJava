package kr.or.ddit.basic;

import javax.swing.JOptionPane;

// 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하세요.
public class HomeWorkGame {
	
	static boolean inputCheck = false; // 입력상태는 디폴트 false	
	static String writteData = ""; // 작성 데이터 (가위,바위,보 중)
	
	public static void main(String[] args) {
		
	// 난수를 이용하여 컴퓨터의 가위바위보를 정함
		String[] rndData = {"가위", "바위", "보"};
		
	// index 값을 이용하여 가위바위보 난수 만들기
		int index = (int)(Math.random() * 3);
		String comData = rndData[index];
		
	//카운트다운 시작
		GameTime gt = new GameTime();
		gt.start();
		
	//사용자 입력 다이로그 시작
		showInputDialog personData = new showInputDialog();
		personData.start();
		
	// 입력받을때까지 기다리시오.
		try {
			personData.join();
		}catch (InterruptedException ex) {
			ex.printStackTrace();
		}

//결과 판정
	String result = "";
	if(writteData.equals(comData)) {
		result ="비겼습니다.";
	}else if( (writteData.equals("가위") && comData.equals("바위")) 
			|| (writteData.equals("바위") && comData.equals("보"))
			|| (writteData.equals("보") && comData.equals("가위"))) {
		result = "당신은 졌습니다.";
	}else {
		result = "당신은 이겼습니다.";	
	}
	
//결과 출력
	System.out.println("=== 결 과 ===");
	System.out.println("컴퓨터 : " + comData);
	System.out.println("당  신 : " + writteData);
	System.out.println("결  과 : " + result);
	
	}
}

// 사용자 입력처리를 위한 메소드
class showInputDialog extends Thread{
	
	@Override
	public void run() {
		String inputData = "";
		
		do {
			inputData = JOptionPane.showInputDialog(" 5초 안에 [가위/바위/보]를 입력하세요.");
			if(inputData.equals("가위")) {
				break;
			}else if(inputData.equals("바위")) {
				break;
			}else if(inputData.equals("보")) {
				break;
			}else {
				System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
			}
		}while(true);

//sem Code
//		do {
//			inputData = JOptionPane.showInputDialog("5초 안에 [가위/바위/보]를 입력하세요.");
//		}while(
//				!inputData.equals("가위")
//				 && !inputData.equals("바위")
//				 && !inputData.equals("보")
//				);
//		
		HomeWorkGame.inputCheck = true; //입력이 완료 됨
		HomeWorkGame.writteData = inputData; // 입력한 데이터 저장
		
	}
}

// 5초 카운트 다운을 위한 메소드
class GameTime extends Thread{
	
	@Override
	public void run() {
		
		for(int i = 5; i >= 1; i--) {
			if(HomeWorkGame.inputCheck) {
				return;
			}
			System.out.println(i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("5초가 지나 당신이 졌습니다...");
		System.exit(0);
		
	}
}
