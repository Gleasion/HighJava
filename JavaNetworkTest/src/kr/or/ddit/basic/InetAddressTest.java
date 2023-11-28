package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

/* getByName()
 www.naver.com 및 SEM-PC 등과 같은 머신이름이나 IP주소를 사용하여
 유효한 InetAddress 객체를 제공한다.
=> IP주소 자체를 넣을 경우, 주소 구성 자체의 유효성 정도만 체크 */

// InetAddress 클래스 => IP주소 정보를 다루기 위한 클래스
public class InetAddressTest {
	
	public static void main(String[] args) throws UnknownHostException {
		
	// 네이버 사이트의 IP정보 가져오기
		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		// InetAddress tempIp = InetAddress.getByName("111.111.111.1");
		
		System.out.println("HOST Name => " + naverIp.getHostName());
		System.out.println("HOST Address => " + naverIp.getHostAddress());
		System.out.println("");
		
	// 자기 자신 컴퓨터의 IP 정보 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println(
				"내 컴퓨터의 Host Name => " + localIp.getHostName());
		System.out.println("내 컴퓨터의 Host Address => " 
							+ localIp.getHostAddress());
		System.out.println();
		
	// IP주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] naverIps = InetAddress.getAllByName("www.naver.com");
		for(InetAddress iAddr : naverIps) {
			System.out.println(iAddr.toString());
		}
		
		
	}
	
	
}
