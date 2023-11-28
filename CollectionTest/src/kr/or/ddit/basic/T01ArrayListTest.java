package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T01ArrayListTest {

	public static void main(String[] args) {
		// Default_Capacity = 10
				ArrayList list1 = new ArrayList(); //list1에는 ArrayList만 담을 수 있음
				//ArrayList list1 = new LinkedList(); //안됨
				//List list1 = new LinkedList(); //됨
				
				//add() 메소드르 사용해서 데이터를 추가한다.
				list1.add("aaa");
				list1.add("bbb");
				list1.add("ccc");
				list1.add(new Integer(111)); //Wrapper class가 기본적으로 값을 int로 만들어 줌
				list1.add(new Character('k'));
				list1.add(true);
				list1.add(12.34); //double
				list1.add(12.34f); //float
				
				//size() => 데이터 개수
				System.out.println("size => " + list1.size());
				//[aaa, aaa, bbb, 111, k, true, 12.34, 12.34]
				System.out.println("list1 => " +list1);
				
				//get() 으로 데이터 가져오기, 1번째 자료 => aaa
				System.out.println("1번째 자료 => " + list1.get(0)); //get(int 값) 넣어서 몇번째 값인지 표시
				
				//데이터끼워넣기
				list1.add(0, "zzz");
				System.out.println("zzz 끼워 넣은 후 : " + list1);
				
				//데이터 변경하기 => set() 메소드 사용
				String temp = (String) list1.set(0, "YYY");
				System.out.println("temp1 => " + temp);
				temp = (String) list1.set(1, "WWW");
				System.out.println("temp2 => " + temp);
				
				temp = (String) list1.set(2, "VVV");
				System.out.println("temp3 => " + temp);
				System.out.println("list1(데이터 변경 후) =>" + list1);
				
				//데이터 삭제하기
				list1.remove(0);
				System.out.println("첫번째 데이터 삭제 후 : " + list1);
				
				list1.remove("bbb");
				System.out.println("bbb 삭제 후" + list1);
				
				//list1.remove(2);
				//System.out.println("3번째 데이터 삭제 후" + list1);
				list1.remove(new Integer(111));
				System.out.println("111 삭제 후" + list1);
				
				System.out.println("==============================================");
				
				//제네릭을 이용하여 선언할 수 있다.
				List<String> list2 = new ArrayList<String>(); //<String> 타입지정
				list2.add("AAA");
				list2.add("BBB");
				list2.add("CCC");
				list2.add("DDD");
				list2.add("EEE");

				for(int i=0; i<list2.size(); i++) {
					System.out.println(i +" : "+list2.get(i));
				}
				
				System.out.println("-------------------------------------------");
				
				//contains(비교객체) => 리스트에 '비교객체'가 있으면 true, 없으면 false 리턴함
				System.out.println(list2.contains("DDD"));
				System.out.println(list2.contains("ZZZ"));
			
				//indexOf(비교객체) => 리스트에서 '비교객체를 찾아 '비교객체'가 있는 index 값을 반환함(없으면 -1반환함)
				System.out.println(list2.contains("DDD의 index 값 : " + list2.indexOf("DDD")));
				System.out.println(list2.contains("DDD의 index 값 : " + list2.indexOf("DDD")));
				
				System.out.println("------------------------------------------");
				
				// 리스트의 삭제처리에 대하여
				// 처음부터 지우지 말고 끝에서부터 지워야 다 지워짐
				for(int i=0; i<list2.size(); i++) {
					list2.remove(list2.get(i));
				}
				
				System.out.println("삭제 후 list2의 개수 : " + list2.size());
				
				
				
				
				
			}
		}
