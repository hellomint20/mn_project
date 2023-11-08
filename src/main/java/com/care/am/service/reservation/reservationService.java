package com.care.am.service.reservation;

import java.util.List;
import java.util.Map;
import com.care.am.dto.mediDTO;
import com.care.am.dto.petDTO;
import com.care.am.page.customerPagination;
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
	public List<Map<String, String>> customerResList( String id, customerPagination pag);
	public int mediSearch(String mName); //�˻��� �̸� ����Ʈ ���� ��������
	public List<Map<String, String>> mediSelectSearch(String mName, reservationPagination pag); //pag �ش��ϴ� ��ŭ �˻� ����Ʈ ��������
	public Map<String, String> reservationCheck(Map<String, String> map);
	public String reserCancel(String id, int num);
	public int reserState(int num, int state);
	public List<Map<String, String>> mediReservationList(String id);
	public List<Map<String, String>> mediReservationWaitList(String id);
	public Map<String, String> reservationInfo(int rNum);

}
