package com.care.am.dto;

public class reservationDTO {
	int rNum;
	String mId;
	String pNum;
	String cId;
	String rContent;
	String rDate;
	String rApply;
	String rName;
	String rTel;
	
	
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getpNum() {
		return pNum;
	}
	public void setpNum(String pNum) {
		this.pNum = pNum;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getrContent() {
		return rContent;
	}
	public void setrContent(String rContent) {
		this.rContent = rContent;
	}
	public String getrDate() {
		return rDate;
	}
	public void setrDate(String rDate) {
		this.rDate = rDate;
	}
	public String getrApply() {
		return rApply;
	}
	public void setrApply(String rApply) {
		this.rApply = rApply;
	}

	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getrTel() {
		return rTel;
	}
	public void setrTel(String rTel) {
		this.rTel = rTel;
	}
}
