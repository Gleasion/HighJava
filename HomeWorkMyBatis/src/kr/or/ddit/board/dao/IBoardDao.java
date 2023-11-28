package kr.or.ddit.board.dao;

import java.util.List;
import kr.or.ddit.board.vo.BoardVO;

/**
 * 실제 DB에 연결해서 SQL문을 수행하여 결과를 서비스에 전달해주는 DAO를 위한 인터페이스
 * @author PC-07
 *
 */
public interface IBoardDao {
	
	/**
	 * BoardVO에 담긴 데이터를 DB에 인서트 하기 위한 메소드
	 * @param bv DB에 인서트할 데이터를 담고 있는 BoardVO 객체
 	 * @return DB 작업이 성공하면 1, 실패하면 0리턴
	 */
	public int insertNotice(BoardVO bv);
	
	/**
	 * BoardVO에 담긴 데이터를 DB에 업데이트 하기 위한 메소드
	 * @param bv DB에 업데이트할 데이터를 담고 있는 BoardVO 객체
 	 * @return DB 작업이 성공하면 1, 실패하면 0리턴
	 */
	public int updateNotice(BoardVO bv);
	
	/**
	 * 주어진 게시판 번호를 이용하여 게시글이 존재하는지 여부를 체크하기 위한 메소드
	 * @param boardNo 게시글 존재 여부 체크용 게시판 번호
 	 * @return DB 작업이 성공하면 true, 실패하면 false 리턴
	 */
	public boolean checkBoardNo(int boardNo);
	
	/**
	 * 주어진 게시판 번호에 해당하는 게시글을 삭제하기 위한 메소드
	 * @param boardNo 삭제할 게시판 번호
 	 * @return DB 작업이 성공하면 1, 실패하면 0 리턴
	 */
	public int deleteNotice(int boardNo);
	
	/**
	 * 주어진 검색키워드에 해당하는 DB안의 게시글을 가져와 List에 담아서 반환하는 메소드
	 * @param bv 검색할 키워드
	 * @return 작성자의 이름에 해당하는 게시글 정보를 담은 List 객체
	 */
	public List<BoardVO> findNotice(BoardVO bv);
	
	/**
	 * 주어진 작성자 이름을 이용하여 게시글이 존재하는지 여부를 체크하기 위한 메소드
	 * @param writer 작성자의  존재 여부 체크용 작성자 이름
 	 * @return DB 작업이 성공하면 true, 실패하면 false 리턴
	 */
	public boolean checkWriter(String writer);
	
	/**
	 * DB에 존재하는 모든 게시글을 가져와 List에 담아서 반환하는 메소드
	 * @return 모든 게시글 정보를 담은 List 객체
	 */
	public List<BoardVO> displayAll();
	
}
