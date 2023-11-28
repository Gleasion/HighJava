package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

public interface IMemberService {
	
	/**
	 * 회원정보를 등록 하기 위한 메소드
	 * @param mv 등록할 회원정보를 담고있는 MemberVO객체
	 * @return 등록작업이 성공하면 1, 실패하면 0리턴
	 */
	public int registerMemeber(MemberVO mv);
	
	/**
	 * 주어진 회원 ID가 이용하여 회원정보가 존재하는지 여부를 체크하기 위한 메소드
	 * @param memId 회원정보존재 여부 체크용 회원ID
	 * @return 해당 회원정보가 존재하면 true, 존재하지 않으면 false 리턴
	 */
	public boolean checkMember(String memId);
	
	/**
	 * 모든 회원정보를 가져와 List에 담아서 반환하는 메소드
	 * @return 모든 회원정보를 담은 List 객체
	 */
	public List<MemberVO> getAllMember();
	
	/**
	 * MemeberVO에 담긴 테이터를 업데이트 하기 위한 메소드
	 * @param mv 업데이트 할 데이터를 담고있는 MemberVO 객체
	 * @return 수정 작업이 성공하면 1, 실패하면 0리턴
	 */
	public int modifyMember(MemberVO mv);

	/**
	 * 주어진 회원 ID에 해당하는 회원정보를 삭제하기 위한 메소드
	 * @param memId 삭제할 회원ID
	 * @return 삭제 작업이 성공하면 1, 실패하면 0리턴함
	 */
	public int removeMember(String memId);
	
}
