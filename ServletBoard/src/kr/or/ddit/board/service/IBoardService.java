package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {
	/**
	 * 게시글을 등록 하기 위한 메서드
	 * @param bv 등록할 게시판정보를  담고있는 BoardVO객체
	 * @return 등록작업이 성공하면 1, 실패하면 0 리턴함
	 */
	public int registerBoard (BoardVO bv);
	
	/**
	 * 주어진 게시글No를 이용하여 게시글이 존재하는지 여부를 체크하기 위한 메서드
	 * @param bdNo 게시글 존재 여부 체크용 BoardNo
	 * @return 해당 회원정보가 존재하면 true, 존재하지 않으면 false 리턴함
	 */
	public boolean checkBoard(String bdNo);
	
	/**
	 * 모든 게시글정보를 가져와 List에 담아서 반환하는 메서드
	 * @return 모든 게시글정보를 담은 List객체
	 */
	public List<BoardVO> getAllBoard();
	
	/**
	 * BoardVO에 담긴 데이터를 DB에 업데이트 하기 위한 메서드
	 * @param mv 업데이트 할 데이터를 담고있는 BoardVO객체
	 * @return 수정작업이 성공하면 1, 실패하면 0 리턴함
	 */
	public int modifyBoard(BoardVO bv);
	
	/**
	 * 주어진 게시글No에 해당하는 게시글 정보를 삭제하기 위한 메서드
	 * @param bdNo 삭제할 게시글번호
	 * @return 삭제작업이 성공하면 1, 실패하면 0 리턴함
	 */
	public int removeBoard(String bdNo);
	
	/**
	 * 게시글 정보를 가져와 List에 담아서 반환하는 메서드
	 * @return 모든 게시글 정보를 담은 List객체
	 */
	public List<BoardVO> getBoard(BoardVO bv);
	
	/**
	 * 게시글 정보를 가져와 반환하는 메서드
	 * @param bdNo 조회할 게시글번호
	 * @return 모든 게시글 정보를 담은 List객체
	 */
	public BoardVO getBoardone(String board_no);
}
