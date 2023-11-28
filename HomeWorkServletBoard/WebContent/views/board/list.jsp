<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<%
	List<BoardVO> boardList = (List<BoardVO>) request.getAttribute("boardList");

	String msg = session.getAttribute("msg") == null ?
		"" : (String) session.getAttribute("msg");
	session.removeAttribute("msg");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
</head>
<body>
   <table border="1">
      <tr>
         <th>게시판 번호</th>
         <th>작성자</th>
         <th>작성일</th>
         <th>제목</th>
         <th>내용</th>
      </tr>
      
<%
      	int boardSize = boardList.size();
         if(boardSize == 0) {
      %>
      <tr>
         <td colspan="5">회원 정보가 존재하지 않습니다.</td>
      </tr>
<%
	}else {
      
      for(BoardVO bv : boardList) {
%>   
      <tr>
      	 <%-- <td><%out.print(mv.getMemId()); %></td> --%>
         <td><%= bv.getBoardNo()%></td>
         <td><a href="./detail.do?boardNo=<%= bv.getBoardNo()%>"><%= bv.getWriter()%></a></td>
         <td><%= bv.getWriteDate()%></td>
         <td><%= bv.getTitle()%></td>
         <td><%= bv.getContent()%></td>
      </tr>
<% 
      }
   }
%>   
   <tr align="center">
      <td colspan="5"><a href="insert.do">[게시글 등록]</a></td> <!-- 상대경로로 ./생략 가능 -->
   </tr>

   </table>
<%
	if(msg.equals("등록성공")){
%>
<script>
	alert("게시글이 정상적으로 등록되었습니다.")
</script>
<%
	}else if(msg.equals("수정성공")){
%>
<script>
	alert("게시글이 정상적으로 수정되었습니다.")
</script>
<%
	}else if(msg.equals("삭제성공")){
%>
<script>
	alert("게시글이 정상적으로 삭제되었습니다.")
</script>
<%
	}
%>

</body>
</html>