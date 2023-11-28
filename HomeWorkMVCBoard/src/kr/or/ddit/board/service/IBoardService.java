package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {
	
	/**
	 * 게시글을 등록하기 위한 메소드
	 * @param bv 등록할 게시글 정보를 담고있는 BoardVO 객체
 	 * @return 등록 작업이 성공하면 1, 실패하면 0리턴
	 */
	public int registerNotice(BoardVO bv);
	
	/**
	 * 게시판 번호를 이용하여 게시글 데이터를 수정 하기 위한 메소드
	 * @param bv 업데이트할 데이터를 담고 있는 BoardVO 객체
 	 * @return 수정 작업이 성공하면 1, 실패하면 0리턴
	 */
	public int modifyNotice(BoardVO bv);
	
	/**
	 * 주어진 게시판 번호를 이용하여 게시글이 존재하는지 여부를 체크하기 위한 메소드
	 * @param boardNo 게시글 존재 여부 체크용 게시판 번호
 	 * @return 해당 게시글이 존재하면 true, 실패하면 false 리턴
	 */
	public boolean checkBoardNo(int boardNo);
	
	/**
	 * 주어진 게시판 번호에 해당하는 게시글을 삭제하기 위한 메소드
	 * @param boardNo 삭제할 게시판 번호
 	 * @return 삭제 작업이 성공하면 1, 실패하면 0 리턴
	 */
	public int removeNotice(int boardNo);
	
	/**
	 * 주어진 작성자 이름에 해당하는 게시글을 가져와 List에 담아서 반환하는 메소드
	 * @param writer 검색할 작성자 이름
	 * @return 작성자의 이름에 해당하는 게시글 정보를 담은 List 객체
	 */
	public List<BoardVO> searchNotice(String writer);
	
	/**
	 * 주어진 작성자 이름을 이용하여 게시글이 존재하는지 여부를 체크하기 위한 메소드
	 * @param writer 작성자의  존재 여부 체크용 작성자 이름
 	 * @return 해당 작성자의 게시글이 존재하면 true, 실패하면 false 리턴
	 */
	public boolean checkWriter(String writer);
	
	/**
	 * 모든 게시글을 가져와 List에 담아서 반환하는 메소드
	 * @return 모든 게시글 정보를 담은 List 객체
	 */
	public List<BoardVO> getDisplayAll();
}
