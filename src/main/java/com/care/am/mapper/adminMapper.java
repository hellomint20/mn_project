package com.care.am.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface adminMapper {
	public void resRegister(Map<String, Object> map); //병원 예약 
	public int payRegister(int rNum);
	public int payRegister(@Param(value = "rNum") String rNum, @Param(value = "payId") String payId);
	public String getImpUid(String rNum);
}
