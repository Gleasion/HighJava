package kr.or.ddit.basic;

public class HomeWorkPlanet {
	
	// 행성의 반지름을 열거하여 객체를 선언
	public enum Planet{
		수성(2439), 
		금성(6052), 
		지구(6371), 
		화성(3390), 
		목성(69911), 
		토성(58232), 
		천왕성(25362), 
		해왕성(24622);
		
		//행성의 반지름들을 저장하는 변수 선언
		private long data;
		
		// 생성자를 만듬
		Planet (long data) {
			this.data = data;
		}
		
		// 행성의 면적을 구한 데이터를 꺼내기 위한 getters
		public long getsizeData() {
			return (long)(data*data*3.14);
		}
	}
	
	// 행성을 나타내 주는 메인 메소드
	public static void main(String[] args) {
		
		Planet[] myPlanet = Planet.values(); // 열거형 객체 변수 선언
		
		for(int i = 0; i < myPlanet.length; i++) {
			System.out.println(myPlanet[i].name() + "의 면적은 " + myPlanet[i].getsizeData() + "km 입니다.");
		}
		
	}

}
