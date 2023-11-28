package kr.or.ddit.basic;

import java.util.LinkedList;

public class T02StackQueueTest {

	public static void main(String[] args) {
		/*
		 * Stack => 후입선출(LIFO)의 자료구조
		 * Queue => 선입선출(FIFO)의 자료구조
		 */
		
		//LinkedList를 이용해야 push pop 등의 메소드 사용 가능
		LinkedList<String> stack = new LinkedList<String>();
		//List<String> stack = new LinkedList<String>(); 안됨
		
		/*
		 * stack 명령
		 * 1) 자료입력 : push(저장할 값)
		 * 2) 자료출력 : pop() => 자료를 꺼내온 후 꺼내온 자료를 stack에서 삭제한다.
		 */
		
		stack.push("홍길동");
		stack.push("일지매");
		stack.push("변학도");
		stack.push("강감찬");
		System.out.println("현재 stack 값들 : " + stack);
		
		String data = stack.pop();
		System.out.println("꺼내온 데이터 : " + data);
		System.out.println("꺼내온 데이터 : " + stack.pop());
		System.out.println("현재 stack 값들 : " + stack);
		
		stack.push("성춘향");
		System.out.println("현재 stack 값들 : " + stack);
		System.out.println("꺼내온 데이터 : " + stack.pop());
		System.out.println("========================================");
		System.out.println();
		
/*
 * Queue의 명령
 * 1) 자료입력 : offer(저장할 값)
 * 2) 자료출력 : poll() 자료를 Queue에서 꺼내온 후 꺼내온 자료는 Queue에서 삭제한다.
 */
		LinkedList<String> queue = new LinkedList<String>();
		
		queue.offer("홍길동");
		queue.offer("일지매");
		queue.offer("변학도");
		queue.offer("강감찬");
		
		System.out.println("현재 queue 값들 : " + queue);
		
		String temp = queue.poll();
		System.out.println("꺼내온 데이터 : " + temp);
		System.out.println("꺼내온 데이터 : " + queue.poll());
		System.out.println("꺼내온 데이터 : " + queue);
		
		if(queue.offer("성춘향")){
			System.out.println("신규 등록 데이터 : " + queue);
		}
		System.out.println("현재 queue의 값 : " + queue);
		System.out.println("꺼내온 데이터 : " + queue.poll());

	}

}
