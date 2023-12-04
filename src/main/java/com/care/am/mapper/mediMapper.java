package com.care.am.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.care.am.dto.mediDTO;


public interface mediMapper {
 	  public mediDTO getMedi(String id); // ���� ������������
	  public void keepLogin(Map<String, Object> map); // ���� �ڵ��α���
	  public int mediRegister(mediDTO dto); // ���� ȸ������
	  public boolean mediIdCheck(String id); //���� ���̵�Ȯ��
	  public mediDTO getMediSession( String mSession ); // ���� ���ǰ�
	  public List<Map<String, String>> mediSearchId(@Param("inputName") String inputName,@Param("inputTel") String inputTel); // ���� ���̵�ã��
	  public int mediModify(mediDTO dto); // ���� ���� ����
	  public int mediPwdChg(mediDTO dto); // ���� ��й�ȣ ����
	  public int mediDelete(mediDTO dto); // ���� Ż��
	  public String getPhoto(String id); // ���� ������������
}
