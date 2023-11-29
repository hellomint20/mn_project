package com.care.am.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.care.am.dto.customerDTO;
import com.care.am.dto.recentlyViewDTO;

public interface customerMapper {
	public customerDTO getCustomer(String id); //��ȣ�� ������������
	public boolean idCheck(String id);	//��ȣ�� ���̵�Ȯ��
	public List<Map<String, String>> customerSearchId(@Param("inputName") String inputName,@Param("inputEmail") String inputEmail); // ��ȣ�� ���̵�ã��
	public void keepLogin(Map<String, Object> map); // �ڵ��α���
	public int register(customerDTO dto); // ��ȣ�� ȸ������
	public customerDTO getCustomerSessionId( String cSessionId ); //��ȣ�� ���ǰ� 
	public int customerPwdChg(customerDTO dto); // ��ȣ�� ��й�ȣ ����
	public int customerModify(customerDTO dto); // ��ȣ�� ���� ����
	public int customerDelete(customerDTO dto); // ��ȣ�� Ż��
	public int addRecentlyView(recentlyViewDTO rDto); // �ֱٺ� ���� �߰�
	public void delRecentlyView(String cId); // �ֱٺ� ���� ����
	public List<Map<String, String>> getRecentlyView(String cId); // �ֱٺ� ���� ����Ʈ��������
}




























