package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] msg; // 패킷 송수신을 위한 바이트 배열 선언
	
	public UdpServer(int port) {
		
		try {
			ds = new DatagramSocket(port); // 메세지 수신을 위한 포트번호 설정
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void start() throws IOException {
		while(true) {
			
			// 데이터를 수신하기 위한 패킷 객체 생성
			msg = new byte[1];
			dp = new DatagramPacket(msg, msg.length);
			
			System.out.println("패킷 수신 대기중...");
			
			// 패킷을 통해 데이터를 수신한다.
			ds.receive(dp);
			
			System.out.println("패킷 수신 완료...");
			
			// 수신한 패킷으로부터 Client의 IP주소와 Port번호를 알아낸다.
			InetAddress addr = dp.getAddress();
			int port = dp.getPort();
			
			//서버의 현재 시간을 시분초 형태([hh:mm:ss])로 반환한다.
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			msg = time.getBytes(); // 시간문자열을 byte배열로 변환
			
			// 패킷을 생성해서 Client에게 접속(Send)한다.
			dp = new DatagramPacket(msg, msg.length, addr, port);
			ds.send(dp); // 전송시작
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		new UdpServer(8888).start();
	}
	
	
}
