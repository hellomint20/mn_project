package com.care.am.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface paymentMapper {
	public int resRegister(Map<String, Object> map); //���� ���� 
	public int payRegister(@Param(value = "rNum") String rNum, @Param(value = "payId") String payId); //�������� ���
	public String getImpUid(String rNum);
}
