package kr.or.ddit.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpFileReceiver {
	
	private DatagramSocket ds;
	private DatagramPacket dp;
	
	private byte[] buffer;
	
	public UdpFileReceiver(int port) {
		
		try {
			ds = new DatagramSocket(port);
		} catch (SocketException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 데이터 수신하기
	 * @return 수신한 바이트배열 데이터
	 * @throws IOException
	 */
	public byte[] receiveData() throws IOException{
		buffer = new byte[1000];
		dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);
		
		return dp.getData();
	}
	
	public void start() throws IOException{
		
		long fileSize = 0;
		long totalReadBytes = 0;
		
		int readBytes = 0;
		
		System.out.println("수신 대기 중...");
		
		String str = new String(receiveData()).trim();
		
		if(str.equals("start")) {
			
			// 전송 파일명 받기
			str = new String(receiveData()).trim();
			
			FileOutputStream fos = new FileOutputStream("e:/D_Other/" + str);
			
			// 전송파일 크기 받기
			str = new String(receiveData()).trim();
			fileSize = Long.parseLong(str);
			
			long startTime = System.currentTimeMillis();
			
			while(true) {
				
				byte[] data = receiveData();
				
				readBytes = dp.getLength();
				
				fos.write(data, 0, readBytes);
				
				totalReadBytes += readBytes;
				
				if(totalReadBytes >= fileSize) {
					break;
				}
				
				System.out.println("진행 상태 : " + totalReadBytes + " / " 
						+ fileSize + " Byte(s) (" 
						+ (totalReadBytes * 100 / fileSize) + " %)");
				
			}
			
			long endTime = System.currentTimeMillis();
			long diffTime = endTime - startTime;
			double transferSpeed = fileSize / diffTime;
			
			System.out.println("걸린시간 : " + diffTime + " (ms)");
			System.out.println("평균 수신 속도 : " + transferSpeed + "Bytes/ms");
			
			System.out.println("수신 완료...");
			
			fos.close();
			ds.close();
			
		}else {
			System.out.println("비정상 데이터 발견 !!!");
			ds.close();
		}
	}
	
	public static void main(String[] args) throws Exception {
		new UdpFileReceiver(8888).start();
	}
	
	
}
