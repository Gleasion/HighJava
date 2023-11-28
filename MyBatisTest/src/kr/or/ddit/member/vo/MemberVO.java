package kr.or.ddit.member.vo;

import java.util.Date;

/**
 * DB테이블에 있는 컬럼명을 기준으로 데이터를 객체화 하기 위한 클래스
 * @author PC-07
 * 
 * <p>
 * 	DB테이블의 '컬럼명'을 참고하여 클래스의 '멤버변수'를 작성한다.
 * </p>
 */
public class MemberVO {
	private String memId;
	private String memName;
	private String memTel;
	private String memAddr;
	private String regDtStr;
	private Date regDt;
	
	public String getRegDtStr() {
		return "오늘은 " + regDtStr + "입니다.";
	}

	public void setRegDtStr(String regDtStr) {
		this.regDtStr = regDtStr;
	}
	
	public MemberVO() {
		
	}
	
	public MemberVO(String memId, String memName, String memTel, String memAddr) {
		super();
		this.memId = memId;
		this.memName = memName;
		this.memTel = memTel;
		this.memAddr = memAddr;
	}

	public String getMemId() {
		return memId;
	}
	
	public void setMemId(String memId) {
		this.memId = memId;
	}
	
	public String getMemName() {
		return memName;
	}
	
	public void setMemName(String memName) {
		this.memName = memName;
	}
	
	public String getMemTel() {
		return memTel;
	}
	
	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}
	
	public String getMemAddr() {
		return memAddr;
	}
	
	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}
	
	public Date getRegDt() {
		return regDt;
	}
	
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	

	@Override
	public String toString() {
		return "MemberVo [memId=" + memId + ", memName=" + memName + ", memTel=" + memTel + ", memAddr=" + memAddr
				+ "]";
	}
	
}
