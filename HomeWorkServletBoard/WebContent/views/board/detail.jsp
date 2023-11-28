<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    	BoardVO bv = (BoardVO) request.getAttribute("bv");
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 상세</title>
</head>
<body>
   <table border="1">
      <tr>
         <td>게시판 번호:</td>
         <td><%= bv.getBoardNo() %></td>
      </tr>
      <tr>
         <td>작성자:</td>
         <td><%= bv.getWriter() %></td>
      </tr>
      <tr>
         <td>작성일:</td>
         <td><%= bv.getWriteDate() %></td>
      </tr>
      <tr>
         <td>제목:</td>
         <td><%= bv.getTitle() %></td>
      </tr>
      <tr>
         <td>내용:</td>
         <td><%= bv.getContent() %></td>
      </tr>
      <tr>
         <td colspan="2">
         <a href="./list.do">[목록]</a>
         <a href="./update.do?boardNo=<%= bv.getBoardNo()%>">[게시글 수정]</a>
         <a href="./delete.do?boardNo=<%= bv.getBoardNo()%>">[게시글 삭제]</a>
         </td>
      </tr>
   </table>
</body>
</html>