<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 등록</title>
<link href="../css/css.css" rel="stylesheet" type="text/css">
</head>
<body>
	<form action="insert.do"  id="test" method="post">
		<div class="board_wrap">
        <div class="board_title">
            <strong>게시판</strong>
            <p>게시글을 모아놓은 공간입니다.</p>
        </div>
        <div class="board_write_wrap">
            <div class="board_write">
                <div class="title">
                    <dl>
                        <dt>제목</dt>
                        <dd><input type="text" placeholder="제목 입력" name="board_title"></dd>
                    </dl>
                </div>
                <div class="info">
                    <dl>
                        <dt>글쓴이</dt>
                        <dd><input type="text" placeholder="글쓴이 입력" name="board_writer"></dd>
                    </dl>
<!--                     <dl> -->
<!--                         <dt>비밀번호</dt> -->
<!--                         <dd><input type="password" placeholder="비밀번호 입력"></dd> -->
<!--                     </dl> -->
                </div>
                <div class="cont">
                    <textarea placeholder="내용 입력" name="board_content"></textarea>
                </div>
            </div>
            <div class="bt_wrap">
<!--             	<input type="submit" value="게시글 작성"> -->
                <a class="test" type="submit" class="on" onclick="document.getElementById('test').submit();" >등록</a>
                <a href="list.do">취소</a>
            </div>
        </div>
    </div>
	</form>
</body>
</html>