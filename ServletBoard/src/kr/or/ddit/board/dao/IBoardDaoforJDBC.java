package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

/**
 * 실제 DB에 연결해서 SQL문을 수행하여 결과를 서비스에 전달해주는
 *	DAO를 위한 인터페이스
 */
public interface IBoardDaoforJDBC {
	
	/**
	 * BoardVO에 담긴 데이터를 DB에 인서트 하기 위한 메서드
	 * @param bv DB에 인서트할 데이터를 담고있는 BoardVO객체
	 * @return DB작업이 성공하면 1, 실패하면 0 리턴함
	 */
	public int insertBoard (BoardVO bv);
	
	/**
	 * 주어진 bdNo를 이용하여 게시글 정보가 존재하는지 여부를 체크하기 위한 메서드
	 * @param bdNo 게시글존재 여부 체크용 bdNo
	 * @return 해당 게시글 정보가 존재하면 true, 존재하지 않으면 false 리턴함
	 */
	public boolean checkBoard(String bdNo);
	
	/**
	 * DB에 존재하는 모든 게시글정보를 가져와 List에 담아서 반환하는 메서드
	 * @return 모든 게시글 정보를 담은 List객체
	 */
	public List<BoardVO> getAllBoard();
	
	/**
	 * BoardVO에 담긴 데이터를 DB에 업데이트 하기 위한 메서드
	 * @param bv DB에 업데이트 할 데이터를 담고있는 BoardVO객체
	 * @return DB작업이 성공하면 1, 실패하면 0 리턴함
	 */
	public int updateBoard(BoardVO bv);
	
	/**
	 * 주어진 bdNo에 해당하는 게시글 정보를 삭제하기 위한 메서드
	 * @param bdNo 삭제할 게시글 번호
	 * @return DB작업이 성공하면 1, 실패하면 0 리턴함
	 */
	public int deleteBoard(String bdNo);
	
	/**
	 * DB에 존재하는 모든 게시글 정보를 가져와 List에 담아서 반환하는 메서드
	 * @return 모든 게시글 정보를 담은 List객체
	 */
	public List<BoardVO> getBoard(BoardVO bv);
	
	/**
	 * DB에 존재하는 모든 게시글 정보를 가져와 List에 담아서 반환하는 메서드
	 * @return 모든 게시글 정보를 담은 List객체
	 */
	public BoardVO getBoardone(String board_no);
}
