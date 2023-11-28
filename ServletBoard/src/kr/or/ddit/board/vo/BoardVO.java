package kr.or.ddit.board.vo;

import java.util.Date;

/**
 * DB테이블에 있는 컬럼명을 기준으로 데이터를 객체화 하기 위한 클래스
 * @author PC-21
 *
 * <P>
 * 	DB테이블의 '컬럼명'을 참고하여 클래스의 '멤버변수'를 작성한다.
 * </P>
 */
public class BoardVO {
	
	private String board_no;
	private String board_title;
	private String board_writer;
	private String board_content;
	private Date regDt;
	
	public BoardVO() {}

	public BoardVO(String board_writer, String board_title, String board_content) {
		super();
		this.board_writer = board_writer;
		this.board_title = board_title;
		this.board_content = board_content;
	}

	
	public String getBoard_no() {
		return board_no;
	}

	public void setBoard_no(String board_no) {
		this.board_no = board_no;
	}

	public String getBoard_title() {
		return board_title;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public String getBoard_content() {
		return board_content;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "MemberVo [board_title=" + board_title + ", board_writer=" + board_writer + ", board_content=" + board_content 
				+ "]";
	}
	
	
}
