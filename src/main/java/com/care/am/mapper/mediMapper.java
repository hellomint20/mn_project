package com.care.am.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.care.am.dto.mediDTO;


public interface mediMapper {
	 	  public mediDTO getMedi(String id);
		  public void keepLogin(Map<String, Object> map);
		  public int mediRegister(mediDTO dto);
		  public boolean mediIdCheck(String id);
		  public mediDTO getMediSession( String mSession );
		  public mediDTO mediSearchId(@Param("inputName") String inputName,@Param("inputTel") String inputTel);
		  public int mediModify(mediDTO dto);
		  public int mediPwdChg(mediDTO dto);
		  public int mediDelete(mediDTO dto);
		  public String getPhoto(String id);
}
