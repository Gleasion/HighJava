<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	BoardVO bv = (BoardVO) request.getAttribute("bv");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 정보 상세</title>
<link href="../css/css.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="board_wrap">
		<div class="board_title">
			<strong>게시판</strong>
			<p>게시글을 모아놓은 공간입니다.</p>
		</div>
		<div class="board_view_wrap">
			<div class="board_view">
				<div class="title"><%=bv.getBoard_title()%></div>
				<div class="info">
					<dl>
						<dt>번호</dt>
						<dd><%=bv.getBoard_no()%></dd>
					</dl>
					<dl>
						<dt>글쓴이</dt>
						<dd><%=bv.getBoard_writer()%></dd>
					</dl>
					<%
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					%>
					<dl>
						<dt>작성일</dt>
						<dd><%=sdf.format(bv.getRegDt())%></dd>
					</dl>

				</div>
				<div class="cont"><%=bv.getBoard_content()%></div>
			</div>
			<div class="bt_wrap">
				<a href="./list.do" class="on">목록</a> 
				<a href="./update.do?board_no=<%=bv.getBoard_no()%>">게시글 수정</a> 
				<a href="./delete.do?board_no=<%=bv.getBoard_no()%>">게시글 삭제</a>
			</div>
		</div>
	</div>
</body>
</html>