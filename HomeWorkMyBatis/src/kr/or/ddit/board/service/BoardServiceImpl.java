package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService{

	private static IBoardService boardService;
	
	public static IBoardService getInstance() {
		if(boardService == null) {
			boardService = new BoardServiceImpl();
		}
		
		return boardService;
	}
	
	private IBoardDao boardDao;

	public BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
	}
	
	@Override
	public int registerNotice(BoardVO bv) {
		return boardDao.insertNotice(bv);
	}

	@Override
	public int modifyNotice(BoardVO bv) {
		return boardDao.updateNotice(bv);
	}

	@Override
	public boolean checkBoardNo(int boardNo) {
		return boardDao.checkBoardNo(boardNo);
	}

	@Override
	public int removeNotice(int boardNo) {
		return boardDao.deleteNotice(boardNo);
	}

	@Override
	public List<BoardVO> searchNotice(BoardVO bv) {
		return boardDao.findNotice(bv);
	}

	@Override
	public boolean checkWriter(String writer) {
		return boardDao.checkWriter(writer);
	}

	@Override
	public List<BoardVO> getDisplayAll() {
		return boardDao.displayAll();
	}

}
