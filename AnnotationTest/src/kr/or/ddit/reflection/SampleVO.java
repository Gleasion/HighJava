package kr.or.ddit.reflection;

import java.io.Serializable;

import kr.or.ddit.basic.PrintAnnotation;

public class SampleVO implements Serializable, Runnable {
	
	private String id;
	protected String name;
	public int age;
	
	@PrintAnnotation
	@Deprecated // 더이상 쓰지 말라는 의미
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "SampleVO [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
	@Override
	public void run() {}

}
