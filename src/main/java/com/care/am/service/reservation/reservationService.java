package com.care.am.service.reservation;

import java.util.List;
import java.util.Map;
import com.care.am.dto.mediDTO;
import com.care.am.dto.petDTO;
import com.care.am.dto.reservationDTO;

public interface reservationService {
	public List<Map<String, mediDTO>> mediList(); //���� ����Ʈ
	public Map<String, Object> mediInfo(String mediName); //���� ������
	public List<String> mediTime(String name); //���� Time
	public List<Map<String , petDTO>> petList(); //����� pet list
	public int reservationRegister(Map<String, Object> map); //���� ���� 
	public Map<String, String> reservationCount(Map<String, Object> map); ////�ð��� ������ �� Ȯ��
}
