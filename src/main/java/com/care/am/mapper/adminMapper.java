package com.care.am.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface adminMapper {
	public void resRegister(Map<String, Object> map); //���� ���� 
	public int payRegister(int rNum);
	public String getImpUid(String rNum);
}
