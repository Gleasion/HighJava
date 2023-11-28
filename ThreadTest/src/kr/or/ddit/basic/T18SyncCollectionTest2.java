package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 동기화 처리 리스트
 * 
 * 이전부터 존재하던 Collection 클래스들(Vector, Hashtable 등)은 내부에 동기화 처리가 되어있음
 * 
 * 최근에 새로 구성된 Collection 클래스들은 동기화 처리X => 동기화가 필요한 경우, 동기화 처리 후 사용 */

// List에서 동기화 처리
public class T18SyncCollectionTest2 {
	
	// 동기화 처리
	private static List<Integer> List2 =
			Collections.synchronizedList(new ArrayList<Integer>());
	
	public static void main(String[] args) {
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i = 1; i <= 10000; i++) {
					List2.add(1);
				}
			}
		};
		
		Thread[] ths = new Thread[] {
			new Thread(r), new Thread(r),
			new Thread(r), new Thread(r), new Thread(r)
		};
		
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
		
		System.out.println("list의 개수 : " + List2.size());
	}

}
