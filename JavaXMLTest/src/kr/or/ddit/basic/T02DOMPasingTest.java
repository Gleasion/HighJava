package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class T02DOMPasingTest {

	public void parse() throws ParserConfigurationException, SAXException, IOException {
		
		// XML문서를 생성하기 위한 DocumentBuilder 객체 생성하기 
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
				
		// Document 객체 생성 
		Document document = builder.parse(new File("./src/new_book.xml"));
		
		// DOM Document객체로부터 루트 엘리먼트 객체 가져오기
		Element root = document.getDocumentElement();
		System.out.println("루트엘리먼트 태그명: " + root.getTagName());
		
		// 하위 엘리먼트 접근방법1 : getElementByTagName() 메서드 이용 
		NodeList bookNodeList = root.getElementsByTagName("book"); 
		Node firstBookNode = bookNodeList.item(0); //첫번째 항목 
		Element firstBookElement = (Element) firstBookNode;
		
		// 속성 접근방법1 : 엘리먼트 객체의 getAttribute() 메소드 이용
		System.out.println("엘리먼트 객체의 getAttribute() 이용 => "
							+ firstBookElement.getAttribute("isbn"));
		
		// 속성 접근방법2 : 노드객체의 getAttributes() 메소드 이용
		NamedNodeMap nodeMap = firstBookNode.getAttributes();
		System.out.println("노드 객체의 getAttributes() 메소드 이용 => "
							+ nodeMap.getNamedItem("isbn").getNodeValue());
		
		// 하위 엘리먼트 접근방법2 : getChillNodes();
		NodeList firstBookChildNodeList = firstBookNode.getChildNodes();
		
		// #Text노드(공백문자) 때문에 인덱스값을 1로 설정해야 한다.
		Node titleNode = firstBookChildNodeList.item(1); // 공백도 하나의 노드
		Element titleElement = (Element) titleNode;
		System.out.println("titleElement.getTagName() : " + titleElement.getTagName());
		System.out.println("titleElement.getTextContent() : " 
							+ titleElement.getTextContent());
		
		// 전체 출력하기
		System.out.println("-------------------------------------------------------");
		System.out.printf("%8s %8s %12s %10s %8s\n", "ISBN", "분류", "제목", "저자", "가격");
		System.out.println("-------------------------------------------------------");
		for(int i = 0; i < bookNodeList.getLength(); i++) {
			
			Element bookEl = (Element) bookNodeList.item(i);
			String isbn = bookEl.getAttribute("isbn");
			String kind = bookEl.getAttribute("kind");
			String title = bookEl.getElementsByTagName("title").item(0).getTextContent();
			String author = bookEl.getElementsByTagName("author").item(0).getTextContent();
			String price = bookEl.getElementsByTagName("price").item(0).getTextContent();
			
			System.out.printf("%8s %8s %12s %10s %8s\n", isbn, kind, title, author, price);
			
			
		}
		
		
	}
	
	public static void main(String[] args) throws Exception {
		new T02DOMPasingTest().parse();
	}
	
	

}
