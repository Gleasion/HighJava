package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class HomWorkLotto {
	private Scanner scan;
	
	public HomWorkLotto() {
	scan = new Scanner(System.in);
	}
	
	public void lottoStart() {
		System.out.println();
		System.out.println("\t==========================");
		System.out.println("\t Lotto 프로그램");
		System.out.println("\t--------------------------");
		
		while(true) {
			displayMenu();
			
			int menuNum = scan.nextInt();
			
			switch(menuNum) {
			case 1 : buyLotto();
				break;
			case 2 :
				System.out.println();
				System.out.println("\t감사합니다.");
				System.exit(0);
			default :
				System.out.println("\t잘못된 입력값입니다. 다시 입력해주세요.");
			}
		}
	}
	
	private void buyLotto() {
		System.out.println();
		System.out.println("\tLotto 구입 시작");
		System.out.println();
		System.out.println("\t1000원에 로또번호 하나입니다.");
		System.out.print("\t금액 입력 : ");
		
		int money = scan.nextInt();
		
		System.out.println();
		
		int jackpot = (money/1000);
		int charge = (money%1000);
		
		if(money < 0) {
			System.out.println("\t입력값이 잘못되었습니다.");
		}else if(money < 1000) {
			System.out.println("\t금액이 부족합니다.");
		}else {
			System.out.println("\t행운의 로또번호는 아래와 같습니다.");
			for(int i = 1; i <= jackpot; i++) {
				Set<Integer> intRnd = new HashSet<Integer>();
			
				//0번부터 5번까지 총 6회 반복 수행. 단, 중복이 있을 경우 6회 이상 반복할 수 있음
				while(intRnd.size() < 6) {
					int num = (int)(Math.random() * 45 + 1);
					intRnd.add(num);
				}
				System.out.println("\t로또번호" + i + " : " + intRnd);
			}
		
		}
		System.out.println();
		System.out.println("\t받은 금액은 " + money + "원이고 거스름돈은 " + charge +"원입니다.");
	}

	private void displayMenu() {
		System.out.println("\t1. Lotto 구입");
		System.out.println("\t2. 프로그램 종료");
		System.out.println("\t==========================");
		System.out.print("\t 메뉴선택 : ");
	}

	public static void main(String[] args) {
		
		new HomWorkLotto().lottoStart();
		

	}
	
	
}

