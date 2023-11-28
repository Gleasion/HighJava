package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpClient {
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] msg;
	
	private UdpClient() {
		msg = new byte[100];
		
		// 소켓 객체 생성(포트번호 명시하지 않으면 포트번호는 이용가능한 임의의 포트번호로 할당됨
		
		try{
			ds = new DatagramSocket();
		}catch(SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void clientStart() {
		
		try {
			InetAddress serverAddr = InetAddress.getByName("192.168.144.8");
			
			dp = new DatagramPacket(msg, 1, serverAddr, 8888);
			ds.send(dp);
			
			dp = new DatagramPacket(msg, msg.length);
			ds.receive(dp); // 패킷 수신
			System.out.println("현재 서버 시간 => " + new String(dp.getData()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new UdpClient().clientStart();
	}
	
}
