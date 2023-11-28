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
<title>새로운 게시글 등록</title>
</head>
<body>
	<form action="insert.do" method="post">
		<table>
<!-- 			<tr> -->
<!-- 				<td>게시판 번호:</td> -->
<%-- 				<td><%= bv.getBoardNo()%></td> --%>
<!-- 			</tr> -->
			<tr>
				<td>작성자:</td>
				<td><input type="text" name="writer" value=""></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>작성일:</td> -->
<%-- 				<td><%= bv.getWriteDate()%></td> --%>
<!-- 			</tr> -->
			<tr>
				<td>제목:</td>
				<td><textarea name="title" ></textarea></td>
			</tr>
			<tr>
				<td>내용:</td>
				<td><textarea name="content" ></textarea></td>
			</tr>
		</table>
		<input type="submit" value="게시글 등록">
	</form>
</body>
</html>