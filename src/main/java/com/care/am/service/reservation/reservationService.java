package com.care.am.service.reservation;

import java.util.List;
import java.util.Map;
import com.care.am.dto.mediDTO;
import com.care.am.dto.petDTO;
import com.care.am.dto.reservationDTO;
import com.care.am.page.reservationPagination;

public interface reservationService {
	public List<Map<String, mediDTO>> mediList(); //���� ����Ʈ
	public Map<String, Object> mediInfo(String mediId); //���� ������
	public List<String> mediTime(String mediId); //���� Time
	public List<Map<String , petDTO>> petList(String id); //����� pet lists
	public int reservationRegister(Map<String, Object> map); //���� ���� 
	public Map<String, String> reservationCount(Map<String, Object> map); ////�ð��� ������ �� Ȯ��
	public List<Map<String, String>> reservationList(String id);
	public List<Map<String, String>> mediSelectList(reservationPagination pag);
}
