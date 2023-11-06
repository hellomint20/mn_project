package com.care.am.service.reservation;

import java.util.List;
import java.util.Map;
import com.care.am.dto.mediDTO;
import com.care.am.dto.petDTO;
import com.care.am.dto.reservationDTO;
import com.care.am.dto.pageDTO;

public interface reservationService {
	
	public Map<String, Object> mediInfo(String mediName); //���� ������
	public List<String> mediTime(String name); //���� Time
	public List<Map<String , petDTO>> petList(String id); //����� pet lists
	public int reservationRegister(Map<String, Object> map); //���� ���� 
	public Map<String, String> reservationCount(Map<String, Object> map); ////�ð��� ������ �� Ȯ��
	
	public List<Map<String, String>> reservationList(String id);
	
	public String reserCancel(String id, int num);
	public int reserState(int num, int state);
	
	
	public Map<String, String> reservationInfo(int rNum); //���� �˾� ���� ����
	
	public List<Map<String, Object>> waitList(String mId, int page); //���� ���ο����� ����Ʈ
	public pageDTO waitListPaging(int page, String mId); //���� ���ο� ���� ����¡
	
	public List<Map<String, Object>> ACList(String mId, int page); //���� ������� ����Ʈ
	public pageDTO ACListPaging(int page, String mId); //���� ������� ����¡
}
