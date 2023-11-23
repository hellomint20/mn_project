package com.care.am.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface paymentMapper {
	public int resRegister(Map<String, Object> map); //병원 예약 
	public int payRegister(@Param(value = "rNum") String rNum, @Param(value = "payId") String payId); //결제정보 등록
	public String getImpUid(String rNum);
}
