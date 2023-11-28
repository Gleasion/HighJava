package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpFileSender {
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private InetAddress receiverAddr;
	private int port;
	private File file;
	
	public UdpFileSender(String receiverIP, int port) {
		
		try {
			ds = new DatagramSocket();
			this.port = port;
			receiverAddr = InetAddress.getByName(receiverIP);
			file = new File("e:/D_Other/som4.jpg");
			
			if(!file.exists()) {
				System.out.println("해당 파일이 존재하지 않습니다.");
				System.exit(0);
			}
			
		}catch (SocketException e) {
			e.printStackTrace();
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void start() throws IOException, InterruptedException {
		long fileSize = file.length();
		long totalReadBytes = 0;
		
		long startTime = System.currentTimeMillis();
				
		sendData("start".getBytes()); // 전송시작을 알려주기 위한 문자열 전송
		
		sendData(file.getName().getBytes()); // 파일명 전송
		
		sendData(String.valueOf(fileSize).getBytes()); // 총 파일 크기 정보 전송
		
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[1000];
		
		while(true) {
			Thread.sleep(10); // 패킷 전송간의 간격을 주기 위해
			
			int readBytes = fis.read(buffer, 0, buffer.length);
			
			if(readBytes == -1) {
				break;
				
			}
			
			sendData(buffer, readBytes); // 읽은 데이터 전송
			
			totalReadBytes += readBytes;
			
			System.out.println("진행 상태 : " + totalReadBytes + " / " 
							+ fileSize + " Byte(s) (" 
							+ (totalReadBytes * 100 / fileSize) + " %)");
		}
		
		long endTime = System.currentTimeMillis();
		long diffTime = endTime - startTime;
		double transferSpeed = fileSize / diffTime;
		
		System.out.println("걸린시간 : " + diffTime + " (ms)");
		System.out.println("평균 전송 속도 : " + transferSpeed + "Bytes/ms");
		
		System.out.println("전송 완료...");
		
		fis.close();
		ds.close();
	}
	
	/**
	 * 바이트배열 데이터 전송하기
	 * @param data 전송할 바이트 배열 데이터
	 * @throws IOException 
	 */
	private void sendData(byte[] data) throws IOException {
		sendData(data, data.length);
		
	}

	/**
	 * 바이트배열 데이터 전송하기
	 * @param data 전송할 바이트배열 데이터
	 * @param length 실제 전송할 바이트배열 크기
	 * @throws IOException 
	 */
	private void sendData(byte[] data, int length) throws IOException {
		
		dp = new DatagramPacket(data, length, receiverAddr, port);
		ds.send(dp);
		
	}
	
	public static void main(String[] args) throws Exception {
		new UdpFileSender("192.168.144.8", 8888).start();
	}
	
	
}
