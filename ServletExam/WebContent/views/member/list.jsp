<!-- jsp : back-end (%@선언)
	 html : front-end -->
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!--
자바 코드 쓰고 싶으면 <% %> 안에 쓰면 됨 
request : 내장 객체
오브젝트 타입이라서 List로 캐스팅 해줘야 함
-->
<!-- HTML 문서 안에 java code를 다이나믹하게 넣을 수 있음 -->
<%
	List<MemberVO> memList = (List<MemberVO>)request.getAttribute("memList");

	String msg = session.getAttribute("msg") == null ? 
				"" : (String) session.getAttribute("msg");
	session.removeAttribute("msg");


%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>
   <table border="1">
      <tr>
         <th>ID</th>
         <th>이름</th>
         <th>전화번호</th>
         <th>주소</th>
         <th>첨부파일ID</th>
      </tr>
      
<%
   int memSize = memList.size();
   if(memSize == 0) {


%>
      <tr>
         <td colspan="5">회원 정보가 존재하지 않습니다.</td>
      </tr>
      
<%
   }else {
      
      for(MemberVO mv : memList) {

%>   
      <tr>
      	 <%-- <td><%out.print(mv.getMemId()); %></td> --%>
         <td><%= mv.getMemId()%></td>
         <td><a href="./detail.do?memId=<%= mv.getMemId()%>"><%= mv.getMemName()%></a></td>
         <td><%= mv.getMemTel()%></td>
         <td><%= mv.getMemAddr()%></td>
         <td><%= mv.getAtchFileId()%></td>
      </tr>
<% 
   
      }

   }
%>   
   <tr align="center">
      <td colspan="5"><a href="insert.do">[회원 등록]</a></td> <!-- 상대경로로 ./생략 가능 -->
   </tr>

   </table>
   
<% 
	if(msg.equals("성공")){
%>
<script>
	alert('정상적으로 처리되었습니다.');
</script>
<%
	}
%>

</body>
</html>