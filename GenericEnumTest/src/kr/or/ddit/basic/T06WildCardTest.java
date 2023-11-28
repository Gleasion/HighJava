package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T06WildCardTest {

	//장바구니 항목조회를 위한 메소드(모든 항목용)
	public static void displayCartItemInfo(Cart<?> cart) {
		System.out.println("= 음식류 장바구니 항목 리스트 = ");
		for(Object obj : cart.getList()) {
			System.out.println(obj);
		}
		System.out.println("---------------------------------");
	}
	
	//장바구니 항목조회를 위한 메소드(음료나 그 하위 항목용)
	public static void displayCartItemInfo2(Cart<? extends Drink> cart) {
		System.out.println("= 음료나 그 하위 항목 장바구니 항목 리스트 = ");
		for(Object obj : cart.getList()) {
			System.out.println(obj);
		}
		System.out.println("---------------------------------");
	}
	
	//장바구니 항목조회를 위한 메소드(고기나 그 상위 항목용)
	public static void displayCartItemInfo3(Cart<? super Meat> cart) {
		System.out.println("= 고기나 그 상위 항목 장바구니 항목 리스트 = ");
		for(Object obj : cart.getList()) {
			System.out.println(obj);
		}
		System.out.println("---------------------------------");
	}
	
	public static void main(String[] args) {
		
		Cart<Food> foodCart = new Cart<>();
		foodCart.addItem(new Meat("돼지고기", 5000));
		foodCart.addItem(new Meat("소고기", 500000));
		foodCart.addItem(new Juice("키위주스", 1200));
		foodCart.addItem(new Coffee("아메리카노", 1500));

		Cart<Meat> meatCart = new Cart<>();
		meatCart.addItem(new Meat("돼지고기", 5000));
		meatCart.addItem(new Meat("소고기", 500000));
		//meatCart.addItem(new Juice("키위주스", 1200));
		//meatCart.addItem(new Coffee("아메리카노", 1500));

		Cart<Drink> drinkCart = new Cart<>();
		//drinkCart.addItem(new Meat("돼지고기", 5000));
		//drinkCart.addItem(new Meat("소고기", 500000));
		drinkCart.addItem(new Juice("키위주스", 1200));
		drinkCart.addItem(new Coffee("아메리카노", 1500));
		
		displayCartItemInfo(foodCart);
		displayCartItemInfo(meatCart);
		displayCartItemInfo(drinkCart);
		
		//displayCartItemInfo2(foodCart);
		//displayCartItemInfo2(meatCart);
		displayCartItemInfo2(drinkCart);
		
		displayCartItemInfo3(foodCart);
		displayCartItemInfo3(meatCart);
		//displayCartItemInfo3(drinkCart);
		
	}
}

class Food{
	private String name;
	private int price;

	public Food(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return this.name + "(" + this.price + "원)";
	}
}

class Meat extends Food{

	public Meat(String name, int price) {
		super(name, price);
	}
}

class Drink extends Food{

	public Drink(String name, int price) {
		super(name, price);
	}	
}

class Juice extends Drink{

	public Juice(String name, int price) {
		super(name, price);
	}
}

class Coffee extends Drink{

	public Coffee(String name, int price) {
		super(name, price);
	}
}

// 장바구니
class Cart<T>{
	
	private List<T> list;
	
	public Cart() {
		list = new ArrayList<T>();
	}

	public List<T> getList() {
		return list;
	}

	public void addItem(T item) {
		list.add(item);
	}
	
}

