package kr.or.ddit.board.vo;

// DB 테이블에 있는 컬럼명을 기준으로 데이터를 객체화 하기 위한 클래스
// 컬럼명 참고하여 멤버변수 작성
public class BoardVO {
	private int boardNo;
	private String writer;
	private String writeDate;
	private String title;
	private String content;
	
	public BoardVO() {}
	
	public BoardVO(String writer, String title, String content) {
		super();
		this.writer = writer;
		this.title = title;
		this.content = content;
	}
	
	public BoardVO(int boardNo, String writer, String writeDate, String title, String content) {
		super();
		this.boardNo = boardNo;
		this.writer = writer;
		this.writeDate = writeDate;
		this.title = title;
		this.content = content;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", writer=" + writer + ", writeDate=" + writeDate + ", title=" + title
				+ ", content=" + content + "]";
	}
	
}
