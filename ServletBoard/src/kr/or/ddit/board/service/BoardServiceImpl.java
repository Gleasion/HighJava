package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IBoardDaoforJDBC;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.dao.BoardDaoImpl;

public class BoardServiceImpl implements IBoardService {

	private IBoardDaoforJDBC bdDao;
	
	public BoardServiceImpl() {
		bdDao = BoardDaoImpl.getInstance();
	}
	
	private static IBoardService bdService;
	
	public static IBoardService getInstance() {
		if(bdService == null)
			bdService = new BoardServiceImpl();
		return bdService;	
	}
	
	@Override
	public int registerBoard(BoardVO bv) {
		// TODO Auto-generated method stub
		return bdDao.insertBoard(bv);
	}

	@Override
	public boolean checkBoard(String bdNo) {
		// TODO Auto-generated method stub
		return bdDao.checkBoard(bdNo);
	}

	@Override
	public List<BoardVO> getAllBoard() {
		// TODO Auto-generated method stub
		return bdDao.getAllBoard();
	}

	@Override
	public int modifyBoard(BoardVO bv) {
		// TODO Auto-generated method stub
		return bdDao.updateBoard(bv);
	}

	@Override
	public int removeBoard(String bdNo) {
		// TODO Auto-generated method stub
		return bdDao.deleteBoard(bdNo);
	}

	@Override
	public List<BoardVO> getBoard(BoardVO bv) {
		// TODO Auto-generated method stub
		return bdDao.getBoard(bv);
	}

	@Override
	public BoardVO getBoardone(String board_no) {
		// TODO Auto-generated method stub
		return bdDao.getBoardone(board_no);
	}
	
}
