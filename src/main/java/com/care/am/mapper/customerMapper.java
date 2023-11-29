package com.care.am.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.care.am.dto.customerDTO;
import com.care.am.dto.recentlyViewDTO;

public interface customerMapper {
	public customerDTO getCustomer(String id); //보호자 정보가져오기
	public boolean idCheck(String id);	//보호자 아이디확인
	public List<Map<String, String>> customerSearchId(@Param("inputName") String inputName,@Param("inputEmail") String inputEmail); // 보호자 아이디찾기
	public void keepLogin(Map<String, Object> map); // 자동로그인
	public int register(customerDTO dto); // 보호자 회원가입
	public customerDTO getCustomerSessionId( String cSessionId ); //보호자 세션값 
	public int customerPwdChg(customerDTO dto); // 보호자 비밀번호 변경
	public int customerModify(customerDTO dto); // 보호자 정보 수정
	public int customerDelete(customerDTO dto); // 보호자 탈퇴
	public int addRecentlyView(recentlyViewDTO rDto); // 최근본 병원 추가
	public void delRecentlyView(String cId); // 최근본 병원 삭제
	public List<Map<String, String>> getRecentlyView(String cId); // 최근본 병원 리스트가져오기
}




























