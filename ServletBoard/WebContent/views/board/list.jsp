<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	List<BoardVO> bdList = (List<BoardVO>) request.getAttribute("bdList");

String msg = session.getAttribute("msg") == null ? "" : (String) session.getAttribute("msg");
session.removeAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<%=request.getContextPath()%>/css/css.css" rel="stylesheet" type="text/css">
<title>게시글목록</title>

</head>
<body>
	<div class="board_wrap">
		<div class="board_title">
			<strong>게시판</strong>
			<p>게시글을 모아놓은 공간입니다.</p>
		</div>

		<div class="board_list_wrap">
			<div class="board_list">
				<div class="top">
					<div class="num">번호</div>
					<div class="title">제목</div>
					<div class="writer">글쓴이</div>
					<div class="date">작성일</div>
				</div>

				<%
					int bdSize = bdList.size();
						if (bdSize == 0) {
				%>
				<!-- 		<tr> -->
				<!-- 			<td colspan="5">게시글이 존재하지 않습니다.</td> -->
				<!-- 		</tr> -->

				<%
					} else {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						for (BoardVO bv : bdList) {
				%>
				<div>
					<div class="num"><%=bv.getBoard_no()%></div>
					<div class="title">
						<a href="./detail.do?board_no=<%=bv.getBoard_no()%>"><%=bv.getBoard_title()%></a>
					</div>
					<div class="writer"><%=bv.getBoard_writer()%></div>
					<div class="date"><%=sdf.format(bv.getRegDt())%></div>
				</div>

				<%
					}
				}
				%>
				<!-- 		<tr align="center"> -->
				<!-- 			<td colspan="5"><a href="insert.do">[게시글 작성]</a></td> -->
				<!-- 		</tr> -->

				<!-- 	</table> -->
				<%
					if (msg.equals("성공")) {
				%>

				<script>
					alert('정상적으로 처리되었습니다.');
				</script>
				<%
					}
				%>
			</div>
			<div class="board_page">
				<a href="#" class="bt first"><<</a> <a href="#" class="bt prev"><</a>
				<a href="#" class="num on">1</a> <a href="#" class="num">2</a> <a
					href="#" class="num">3</a> <a href="#" class="num">4</a> <a
					href="#" class="num">5</a> <a href="#" class="bt next">></a> <a
					href="#" class="bt last">>></a>
			</div>
			<div class="bt_wrap">
				<a href="insert.do">게시글 작성</a>
				<!--<a href="#">수정</a>-->
			</div>
		</div>
	</div>
</body>
</html>