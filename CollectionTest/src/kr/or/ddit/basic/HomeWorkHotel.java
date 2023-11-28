package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HomeWorkHotel {
	private Scanner scan;
	private Map<String, RoomVO> hotelMap;
	
	public HomeWorkHotel() {
		scan = new Scanner(System.in);
		hotelMap = new HashMap<String, RoomVO>();
	}
	
	public void hotelStart() {
		System.out.println("*********************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("*********************");
		
		while(true) {
			displayMenu();
			
			int menuNum = scan.nextInt();
			
			switch(menuNum) {
				case  1 : checkIn();
					break;
				case  2 : checkOut();
					break;
				case  3 : roomCheck();
					break;
				case  4 :
					System.out.println();
					System.out.println("*********************");
					System.out.println("호텔 문을 닫았습니다.");
					System.out.println("*********************");
					System.exit(0);
				default :
					System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
			}
		}
	
	}

// 객실상태 확인 하는 메소드
private void roomCheck() {
	if(hotelMap.size() == 0) {
		System.out.println("모든 객실 예약 가능합니다.");
	}else {
		Set<String> keySet = hotelMap.keySet();
		Iterator<String> it = keySet.iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			RoomVO rv = hotelMap.get(key);
			
			System.out.println();
			System.out.println("방번호 : " + rv.getRoomNo() + ", 투숙객 : " + rv.getName());	
		}
		
		}
}

// 체크아웃 하는 메소드
private void checkOut() {
	System.out.println();
	System.out.println("어느 방을 체크아웃 하시겠습니까?");
	System.out.print("방번호 입력 => ");
	String roomNo = scan.next();
	
	// 투숙객이 있는 방인지 검사하기
	if(hotelMap.remove(roomNo) == null) {
		System.out.println(roomNo + "방에는 체크인한 사람이 없습니다.");
	}else {
		System.out.println("체크아웃 되었습니다.");
	}
	
}

// 체크인하는 메소드
private void checkIn() {
		System.out.println();
		System.out.println("어느 방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String roomNo = scan.next();
		
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = scan.next();
		
		// 이미 등록한 사람이 있는 호텔 키번호 인지 검사하기
		if(hotelMap.get(roomNo) != null) {
			System.out.println(roomNo + "방에는 이미 사람이 있습니다.");
			return;
		}
		
		RoomVO rv = new RoomVO(roomNo, name);
		hotelMap.put(roomNo, rv);
		System.out.println("체크인 되었습니다.");
		
}

//프로그램 메뉴를 출력하는 메소드
	private void displayMenu() {
		System.out.println();
		System.out.println("****************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.print("1. 체크인    ");
		System.out.print("2. 체크아웃    ");
		System.out.print("3. 객실상태    ");
		System.out.println("4. 업무종료");
		System.out.println("****************************************");
		System.out.print("메뉴선택 => ");
		
	}

//main 메소드
	public static void main(String[] args) {
		new HomeWorkHotel().hotelStart();
	}

}

// 룸번호를 저장하기 위한 VO클래스
class RoomVO{
	private String roomNo;
	private String name;
	
	public RoomVO() {}

	public RoomVO(String roomNo, String name) {
		super();
		this.roomNo = roomNo;
		this.name = name;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RoomVO [roomNo=" + roomNo + ", name=" + name + "]";
	}

	
}