package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeWorkList {

	public static void main(String[] args) {
		
		List<Student> memList = new ArrayList<>();
		
		memList.add(new Student(230401, "장원영", 100, 90, 88));
		memList.add(new Student(230402, "카리나", 80, 70, 20));
		memList.add(new Student(230403, "김제니", 50, 66, 89));
		memList.add(new Student(230404, "안유진", 40, 89, 77));
		memList.add(new Student(230405, "크리스탈", 98, 13, 46));
		memList.add(new Student(230406, "이수지", 45, 72, 23));
		memList.add(new Student(230407, "강지원", 90, 60, 69));
		memList.add(new Student(230408, "채서연", 78, 88, 90));
		memList.add(new Student(230409, "홍지윤", 88, 92, 46));
		memList.add(new Student(230410, "박수빈", 65, 82, 97));
		memList.add(new Student(230411, "황예지", 64, 77, 78));
		memList.add(new Student(230412, "전소미", 88, 65, 90));
		memList.add(new Student(230413, "아이린", 46, 98, 80));
		memList.add(new Student(230414, "김지원", 100, 45, 45));
		memList.add(new Student(230415, "박진", 96, 60, 30));

		Collections.shuffle(memList);
		System.out.println("정렬 전 : ");
		for(Student stu : memList) {
			System.out.println(stu);
		}
		System.out.println();
		
		Collections.sort(memList);
		
		System.out.println("학번으로 오름차순 정렬 후 : ");
		for(Student stu : memList) {
			System.out.println(stu);
		}
		System.out.println();
		
		System.out.println("총점을 기준으로 내림차순, 총점이 같으면 학번을 기준으로 내림차순 정렬 후 : ");
		Collections.sort(memList, new sortTotalDesc());
		for(Student stu : memList) {
			System.out.println(stu);
		}
		
//		int rank = 1;
//		int total2 = -1;
//		for(Student stu : memList) {
//			if(stu.getTotal() != total2) {
//				rank = memList.indexOf(stu) + 1;
//			}
//			stu.setRank(rank);
//			stu.setTotal(stu.getTotal()); 
//			total2 = stu.getTotal();
//		}
		
		int rank = 1;
		for(Student total1 : memList) {
			for(Student total2 : memList) {
				if(total1.getTotal() < total2.getTotal()) {
					rank++;
				}
			}
			total1.setRank(rank);
			total1.setTotal(total1.getTotal());
		}
		
		System.out.println("등수까지 출력 : ");
		Collections.sort(memList, new sortTotalDesc());
		for(Student stu : memList) {
			System.out.println(stu);
		}
		
	}
	
}

// 총점을 기준으로 내림차순 정렬,,
class sortTotalDesc implements Comparator<Student>{
	
//	@Override
//	public int compare(Student tot1, Student tot2) {       
//		// 총점이 다른 경우 내림차순으로 정렬
//		if (tot1.getTotal() != tot2.getTotal()) {
//            return new Integer(tot1.getTotal()).compareTo(tot2.getTotal()) * -1;
//        } else { // 총점이 같은 경우 학번을 내림차순으로 정렬
//            return Integer.compare(tot1.getStuNo(), tot2.getStuNo()) *-1;
//        }
//	}
//}
	@Override
	public int compare(Student tot1, Student tot2) {
		if(tot1.getTotal() > tot2.getTotal()) {
			return -1;
		}else if(tot1.getTotal() > tot2.getTotal()){
			if(tot1.getStuNo() > tot2.getStuNo()){
				return -1;
			}else if(tot1.getStuNo() == tot2.getStuNo()){
				return 0;
			}else{
				return 1;
			}
		}else {
			return 1;
		}
	
	}
	
}
		
	
//학번을 기준으로 오름차순 정렬 하는 것,,
class Student implements Comparable<Student> {
	private int stuNo;
	private String name;
	private int koreanscore;
	private int engscore;
	private int mathscore;
	private int total;
	private int rank;

	public Student() {}
	
	public Student(int stuNo, String name, int koreanscore, int engscore, int mathscore) {
		super();
		this.stuNo = stuNo;
		this.name = name;
		this.koreanscore = koreanscore;
		this.engscore = engscore;
		this.mathscore = mathscore;
	}
	
	public int getStuNo() {
		return stuNo;
	}

	public void setStuNo(int stuNo) {
		this.stuNo = stuNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKoreanscore() {
		return koreanscore;
	}

	public void setKoreanscore(int koreanscore) {
		this.koreanscore = koreanscore;
	}

	public int getEngscore() {
		return engscore;
	}

	public void setEngscore(int engscore) {
		this.engscore = engscore;
	}

	public int getMathscore() {
		return mathscore;
	}

	public void setMathscore(int mathscore) {
		this.mathscore = mathscore;
	}

	public int getTotal() {
		return koreanscore + engscore + mathscore;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	// 학번을 기준으로 오름차순 정렬이 되도록 구현
	@Override
	public int compareTo(Student stu) {
		return new Integer(this.getStuNo()).compareTo(stu.stuNo);
	}

	@Override
	public String toString() {
		return "Student [stuNo=" + stuNo + ", name=" + name + ", koreanscore=" + koreanscore + ", engscore=" + engscore
				+ ", mathscore=" + mathscore + ", total=" + total + ", rank=" + rank + "]";
	}

	
	
}





