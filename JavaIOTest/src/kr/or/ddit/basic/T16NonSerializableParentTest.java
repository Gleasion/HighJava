package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T16NonSerializableParentTest {
/*
    부모클래스가 Serializable 인터페이스를 구현하고 있지 않을 경우, 부모 객체의 필드값 처리 방법... 
  
   1. 부모클래스가 Serializable 인터페이스를 구현하도록 해야 한다. -> 좋은 방법은 아님 
   2. 자식 클래스에 writeObject()와 readObject()메서드를 이용하여 부모클래스의 필드값을 
            처리할 수 있도록 직접 구현해 준다. 
 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		ObjectOutputStream oos = 
				new ObjectOutputStream(
						new FileOutputStream("d:/D_Other/nonSerializeTest.bin"));
		
		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");
		
		oos.writeObject(child); //직렬화 
		
		oos.close();
		
		/////////////////////////////////////////////////////////////////
		
		ObjectInputStream ois =
				new ObjectInputStream(
					new FileInputStream("d:/D_Other/nonSerializeTest.bin"));
		
		Child child2 = (Child) ois.readObject(); //역직렬화 
		
		System.out.println("parentName : " + child2.getParentName());
		System.out.println("childName : " + child2.getChildName());
		
		ois.close();
		
	}
}

/**
 * Serializable을 구현하지 않은 부모클래스 
 * @author PC-14
 */
class Parent {
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}
/**
 * Serializable을 구현한 자식클래스 
 * @author PC-14
 */
class Child extends Parent implements Serializable {
	
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	/**
	 * 직렬화 될 때 자동으로 호출됨.
	 * (접근 제한자가 private가 아니면 자동호출 되지 않는다.)
	 * @param out
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
		//ObjectOutPutStream객체를 이용하여 부모객체의 필드값 처리 
		out.writeUTF(getParentName()); 
		
		out.defaultWriteObject();
	}
	
	/**
	 * 역직렬화 될 때 자동으로 호출됨.
	 * (접근 제한자가 private이 아니면 자동 호출되지 않음.)
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		
		//ObjectInPutStream객체를 이용하여 부모객체의 필드값 처리 
		setParentName(in.readUTF());
		
		in.defaultReadObject();
	}
	
}

