package kr.or.ddit.basic;

// 데이터를 읽어오는 스레드 / 데이터 세팅하는 스레드
// data가 null일 때 data 반환 / null이 아닐 때 data 세팅 스레드
public class T20WaitNotifyTest {
	public static void main(String[] args) {
		
		DataBox dataBox = new DataBox();
		
		Thread pTh = new ProducerThread(dataBox);
		Thread cTh = new ConsumerThread(dataBox);
		
		pTh.start();
		cTh.start();
		
	}

}

//공용으로 사용할 클래스
class DataBox{
	
	private String data;
	
	// data가 null이 아닐때 data값을 반환하는 메소드
	public synchronized String getData() {
		System.out.println(Thread.currentThread().getName()
				 + " : getData() 메소드 시작");
	
		if(this.data == null) {
			try {
				System.out.println(Thread.currentThread().getName()
						 + " : getData() 메소드 안에서 wait() 메소드 호출");
				wait();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
		String returnData = this.data;
		System.out.println("읽어온 데이터 : " + returnData);
		
		this.data = null; // 데이터 제거
		System.out.println("데이터 제거됨");
		
		System.out.println(Thread.currentThread().getName()
				 + " : getData() 메소드 안에서 notify() 메소드 호출");
		notify();
		
		System.out.println(Thread.currentThread().getName()
				 + " : getData() 메소드 끝...");
		
		return returnData;
		
	}
	
	// data가 null일 경우에만 데이터를 세팅하는 메소드
	public synchronized void setData(String data) {
		
		System.out.println(Thread.currentThread().getName()
				 + " : setData() 메소드 시작...");
		
		if(this.data != null) {
			try {
				System.out.println(Thread.currentThread().getName()
					 + " : setData() 메소드 안에서 wait() 메소드 호출");
				wait();
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
		this.data = data; //데이터 설정
		System.out.println("세팅한 데이터 : " + this.data);
		System.out.println(Thread.currentThread().getName()
				 + " : setData() 메소드 안에서 notify() 메소드 호출");
		notify();
		
		System.out.println(Thread.currentThread().getName()
				 + " : setData() 메소드 끝...");
	}
}

// 데이터를 세팅만 하는 스레드
class ProducerThread extends Thread{
	private DataBox dataBox;
	
	public ProducerThread(DataBox dataBox) {
		super("ProducerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 5; i++) {
			String data = "Data-" + i;
			System.out.println(this.getName()
					 + "가 dataBox.setData(" + data + ") 호출함.");
			dataBox.setData(data);
		}
	}
}

// 데이터를 읽어오기만 하는 스레드
class ConsumerThread extends Thread{
	private DataBox dataBox;
	
	public ConsumerThread(DataBox dataBox) {
		super("ConsumerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 5; i++) {
			String data = dataBox.getData();
			System.out.println(this.getName()
					 + "가 dataBox.getData() 호출 후 결과 : " + data);
		}
	}
}

