package com.care.am.dto;

public class boardDTO {
	int rvNo;
	String cId;
	String mName;
	String rvCont;
	String rvDate;
	String rDate;
	
	
	public String getRvDate() {
		return rvDate;
	}
	public void setRvDate(String rvDate) {
		this.rvDate = rvDate;
	}
	public String getrDate() {
		return rDate;
	}
	public void setrDate(String rDate) {
		this.rDate = rDate;
	}
	public int getRvNo() {
		return rvNo;
	}
	public void setRvNo(int rvNo) {
		this.rvNo = rvNo;
	}
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getRvCont() {
		return rvCont;
	}
	public void setRvCont(String rvCont) {
		this.rvCont = rvCont;
	}
	
	
}
