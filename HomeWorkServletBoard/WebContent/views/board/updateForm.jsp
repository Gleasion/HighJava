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
<title>회원정보 변경</title>
</head>
<body>
	<!-- ./update.do 이것도 가능 -->
	<form action="<%=request.getContextPath()%>/board/update.do"
		method="post">
		<!-- 절대경로 -->
		<input type="hidden" name=boardNo value="<%=bv.getBoardNo()%>">
		<table>
			<tr>
				<td>게시판 번호:</td>
				<td><%=bv.getBoardNo()%></td>
			</tr>
			<tr>
				<td>작성자:</td>
				<td><input type="text" name="writer" value="<%=bv.getWriter()%>"></td>
			</tr>
			<tr>
				<td>작성일:</td>
				<td><%=bv.getWriteDate()%></td>
			</tr>
			<tr>
				<td>제목:</td>
				<td><textarea name="title"><%=bv.getTitle()%></textarea></td>
			</tr>
			<tr>
				<td>내용:</td>
				<td><textarea name="content"><%=bv.getContent()%></textarea></td>
			</tr>
		</table>
		<input type="submit" value="회원정보 수정">

	</form>
</body>
</html>