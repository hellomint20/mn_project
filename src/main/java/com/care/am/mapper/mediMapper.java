package com.care.am.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.care.am.dto.mediDTO;


public interface mediMapper {
 	  public mediDTO getMedi(String id); // 병원 정보가져오기
	  public void keepLogin(Map<String, Object> map); // 병원 자동로그인
	  public int mediRegister(mediDTO dto); // 병원 회원가입
	  public boolean mediIdCheck(String id); //병원 아이디확인
	  public mediDTO getMediSession( String mSession ); // 병원 세션값
	  public List<Map<String, String>> mediSearchId(@Param("inputName") String inputName,@Param("inputTel") String inputTel); // 병원 아이디찾기
	  public int mediModify(mediDTO dto); // 병원 정보 수정
	  public int mediPwdChg(mediDTO dto); // 병원 비밀번호 수정
	  public int mediDelete(mediDTO dto); // 병원 탈퇴
	  public String getPhoto(String id); // 병원 사진가져오기
}
