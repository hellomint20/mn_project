package com.care.am.service.customer;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.am.common.GetMessage;
import com.care.am.dto.customerDTO;
import com.care.am.dto.recentlyViewDTO;
import com.care.am.mapper.customerMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class customerServiceImpl implements customerService {

   @Autowired
   customerMapper cm;

   BCryptPasswordEncoder encoder;

   public customerServiceImpl() { //��ȣȭ
      encoder = new BCryptPasswordEncoder();
   }

   public String register(customerDTO dto) { //ȸ������
      int result = 0;

      dto.setcPw(encoder.encode(dto.getcPw()));
      try {
         result = cm.register(dto);
      } catch (Exception e) {
         e.printStackTrace();
      }
      if (result == 1) {
         return GetMessage.getMessage("ȸ������ ����", "/am/customerLogin");
      }
      return GetMessage.getMessage("ȸ������ ����", "/am/customerRegister");
   }

   public boolean idCheck(String id) { // ȸ�����Խ� ���̵� �ߺ�Ȯ��
      return cm.idCheck(id);
   }

   public customerDTO naverLogin(String apiResult) throws Exception { //���̹��α���
      ObjectMapper mapper = new ObjectMapper();
      HashMap<String, Object> map = new HashMap<>();
      Map<String, Object> data = mapper.readValue(apiResult, Map.class);
      JSONParser jsonParser = new JSONParser();

      JSONObject jsonObject = (JSONObject) jsonParser.parse(apiResult);
      jsonObject = (JSONObject) jsonObject.get("response");
      map.put("cEmail", jsonObject.get("email"));
      map.put("cName", jsonObject.get("name"));
      map.put("cTel", jsonObject.get("mobile"));

      String email = (String) ((Map<String, Object>) data.get("response")).get("email");
      String id = email.split("@")[0] + "_naver";
      customerDTO dto = new customerDTO();
      dto = cm.getCustomer(id);
      System.out.println("dto" + dto);
      String pwd = makeRandomPw();
      if (dto == null) { // ���̹� ���̵�� ȸ�����Ե� ������ ���ٸ�
         customerDTO ndto = new customerDTO();
         ndto.setcId(id);
         ndto.setcName(map.get("cName").toString());
         ndto.setcEmail(map.get("cEmail").toString());
         ndto.setcTel(map.get("cTel").toString());
         ndto.setcPw(encoder.encode(pwd));
         cm.register(ndto);
         return ndto;
      }
      return dto;
   }

   public List<Map<String, String>> customerSearchId(String inputName, String inputEmail) { // ��ȣ�� ���̵� ã��
      return cm.customerSearchId(inputName, inputEmail);
    
   }

   public customerDTO customerSearchPw(String inputId, String inputName, String inputTel) { //��ȣ�� ��й�ȣ ã��
      customerDTO dto = cm.getCustomer(inputId);
      if (dto != null) {
         if (inputName.equals(dto.getcName()) && inputTel.equals(dto.getcTel())) {
            return dto;
         }
      }
      return null;
   }

   public String makeRandomPw() { // �ӽú�й�ȣ �����
      StringBuffer sb = new StringBuffer();
      SecureRandom sr = new SecureRandom();
      char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&' };
      sr.setSeed(new Date().getTime());
      int idx = 0;
      int len = charSet.length;
      for (int i = 0; i < 8; i++) { // 8�� �ӽú�й�ȣ �ڸ���
         idx = sr.nextInt(len);
         sb.append(charSet[idx]);
      }
      return sb.toString();
   }

   public int customerPwChg(String tempPwd, customerDTO dto) { // ��ȣ�� ��й�ȣ ����
      dto.setcPw(encoder.encode(tempPwd));
      int result = cm.customerPwChg(dto);
      return result;
   }

   public int logChk(String id, String pw) { //��ȣ�� �α��� üũ
      customerDTO dto = cm.getCustomer(id);
      int result = 0;
      if (dto != null) {
         if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
            result = 1;
         }
      }
      return result;
   }

   public void keepLogin(String cSessionId, String cId) { // �ڵ��α��� ����
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("cSessionId", cSessionId);
      map.put("cId", cId);
      cm.keepLogin(map);
   }

   public customerDTO getCustomerInfo(String cId) { // ��ȣ�� ȸ������ ��������
      customerDTO dto = cm.getCustomer(cId);
      return dto;
   }

   public customerDTO getCustomerSessionId(String cSessionId) { 
      return cm.getCustomerSessionId(cSessionId);
   }

   public String customerPwdChk(String id, String pw) { // ��й�ȣ Ȯ��
      customerDTO dto = cm.getCustomer(id);
      if (dto != null) {
         if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
            String url = "/am/customerModify?id=" + id;
            return "<script>location.href='" + url + "';</script>";
         }
      }
      return GetMessage.getMessage("��й�ȣ�� Ʋ�Ƚ��ϴ�", "/am/customerPwdChk?id=" + id);
   }

   public String customerPwdChg(customerDTO dto, String pw, String newPw) { // ��й�ȣ ����
      dto = cm.getCustomer(dto.getcId());
      int result = 0;
      if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
         dto.setcPw(encoder.encode(newPw));
         result = cm.customerPwdChg(dto);
         if (result == 1) {
            return GetMessage.getMessage("��й�ȣ�� ����Ǿ����ϴ�", "/am/customerInfo?id=" + dto.getcId());
         }
      }
      return GetMessage.getMessage("��й�ȣ�� Ʋ�Ƚ��ϴ�", "/am/customerPwdChg?id=" + dto.getcId());
   }

   public String customerModify(customerDTO dto) { // ��ȣ�� ȸ������ ����
      int result = 0;
      result = cm.customerModify(dto);
      if (result == 1) {
         return GetMessage.getMessage("������ �����Ǿ����ϴ�.", "/am/customerInfo?id=" + dto.getcId());
      }
      return GetMessage.getMessage("���������� �����߽��ϴ�.", "/am/customerModify?id=" + dto.getcId());
   }

   public String customerDelete(customerDTO dto, String pw) { // ��ȣ�� ȸ�� Ż��
      dto = cm.getCustomer(dto.getcId());
      int result = 0;
      if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
         result = cm.customerDelete(dto);
         if (result == 1) {
            return GetMessage.getMessage("Ż�� �Ϸ�Ǿ����ϴ�", "/am");
         }
      }
      return GetMessage.getMessage("��й�ȣ�� Ʋ�Ƚ��ϴ�", "/am/customerPwdChk?id=" + dto.getcId());
   }

   public void addrecentlyView(String mediCookie) { // �ֱ� �� �����߰�
      recentlyViewDTO rvDTO = new recentlyViewDTO();
      String cId = mediCookie.split("/")[0];
      String mId = mediCookie.split("/")[1];
      rvDTO.setcId(cId);
      rvDTO.setmId(mId);
      cm.addRecentlyView(rvDTO); // db �� ����
   }
   
   public void delRecentlyView(String cId) { // �ֱ� �� ���� ������ ����
      cm.delRecentlyView(cId);
      
   }

   public List<Map<String, String>> getRecentlyView(List<String> recentlyViewed, String cId) { // �ֱ� �� ���� ����Ʈ ��������
      List<Map<String, String>> getView = new ArrayList<Map<String, String>>();
      if (recentlyViewed.size() != 0) {
         getView = cm.getRecentlyView(cId);
         return getView;
      }
      return null;
   }
}