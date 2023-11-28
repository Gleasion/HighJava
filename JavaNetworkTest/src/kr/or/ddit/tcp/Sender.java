package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread{

	private Scanner scan;
	private String name;
	private DataOutputStream dos;
	
	public Sender(Socket socket) {
		
		scan = new Scanner(System.in);
		
		name = "[" + socket.getInetAddress() + " : " 
				+ socket.getLocalPort() + "]"; 
		
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		while(dos != null) {
			
			try {
				
				dos.writeUTF(name + " >>> " + scan.nextLine());
				
			}catch(IOException ex){
				ex.printStackTrace();
			}
			
		}
	
	
	}
}
