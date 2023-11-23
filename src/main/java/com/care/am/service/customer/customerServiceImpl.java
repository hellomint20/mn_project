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

   public customerServiceImpl() { //암호화
      encoder = new BCryptPasswordEncoder();
   }

   public String register(customerDTO dto) { //회원가입
      int result = 0;

      dto.setcPw(encoder.encode(dto.getcPw()));
      try {
         result = cm.register(dto);
      } catch (Exception e) {
         e.printStackTrace();
      }
      if (result == 1) {
         return GetMessage.getMessage("회원가입 성공", "/am/customerLogin");
      }
      return GetMessage.getMessage("회원가입 실패", "/am/customerRegister");
   }

   public boolean idCheck(String id) { // 회원가입시 아이디 중복확인
      return cm.idCheck(id);
   }

   public customerDTO naverLogin(String apiResult) throws Exception { //네이버로그인
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
      if (dto == null) { // 네이버 아이디로 회원가입된 정보가 없다면
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

   public List<Map<String, String>> customerSearchId(String inputName, String inputEmail) { // 보호자 아이디 찾기
      return cm.customerSearchId(inputName, inputEmail);
    
   }

   public customerDTO customerSearchPw(String inputId, String inputName, String inputTel) { //보호자 비밀번호 찾기
      customerDTO dto = cm.getCustomer(inputId);
      if (dto != null) {
         if (inputName.equals(dto.getcName()) && inputTel.equals(dto.getcTel())) {
            return dto;
         }
      }
      return null;
   }

   public String makeRandomPw() { // 임시비밀번호 만들기
      StringBuffer sb = new StringBuffer();
      SecureRandom sr = new SecureRandom();
      char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&' };
      sr.setSeed(new Date().getTime());
      int idx = 0;
      int len = charSet.length;
      for (int i = 0; i < 8; i++) { // 8은 임시비밀번호 자리수
         idx = sr.nextInt(len);
         sb.append(charSet[idx]);
      }
      return sb.toString();
   }

   public int customerPwChg(String tempPwd, customerDTO dto) { // 보호자 비밀번호 변경
      dto.setcPw(encoder.encode(tempPwd));
      int result = cm.customerPwChg(dto);
      return result;
   }

   public int logChk(String id, String pw) { //보호자 로그인 체크
      customerDTO dto = cm.getCustomer(id);
      int result = 0;
      if (dto != null) {
         if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
            result = 1;
         }
      }
      return result;
   }

   public void keepLogin(String cSessionId, String cId) { // 자동로그인 유지
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("cSessionId", cSessionId);
      map.put("cId", cId);
      cm.keepLogin(map);
   }

   public customerDTO getCustomerInfo(String cId) { // 보호자 회원정보 가져오기
      customerDTO dto = cm.getCustomer(cId);
      return dto;
   }

   public customerDTO getCustomerSessionId(String cSessionId) { 
      return cm.getCustomerSessionId(cSessionId);
   }

   public String customerPwdChk(String id, String pw) { // 비밀번호 확인
      customerDTO dto = cm.getCustomer(id);
      if (dto != null) {
         if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
            String url = "/am/customerModify?id=" + id;
            return "<script>location.href='" + url + "';</script>";
         }
      }
      return GetMessage.getMessage("비밀번호가 틀렸습니다", "/am/customerPwdChk?id=" + id);
   }

   public String customerPwdChg(customerDTO dto, String pw, String newPw) { // 비밀번호 변경
      dto = cm.getCustomer(dto.getcId());
      int result = 0;
      if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
         dto.setcPw(encoder.encode(newPw));
         result = cm.customerPwdChg(dto);
         if (result == 1) {
            return GetMessage.getMessage("비밀번호가 변경되었습니다", "/am/customerInfo?id=" + dto.getcId());
         }
      }
      return GetMessage.getMessage("비밀번호가 틀렸습니다", "/am/customerPwdChg?id=" + dto.getcId());
   }

   public String customerModify(customerDTO dto) { // 보호자 회원정보 수정
      int result = 0;
      result = cm.customerModify(dto);
      if (result == 1) {
         return GetMessage.getMessage("정보가 수정되었습니다.", "/am/customerInfo?id=" + dto.getcId());
      }
      return GetMessage.getMessage("정보수정에 실패했습니다.", "/am/customerModify?id=" + dto.getcId());
   }

   public String customerDelete(customerDTO dto, String pw) { // 보호자 회원 탈퇴
      dto = cm.getCustomer(dto.getcId());
      int result = 0;
      if (encoder.matches(pw, dto.getcPw()) || pw.equals(dto.getcPw())) {
         result = cm.customerDelete(dto);
         if (result == 1) {
            return GetMessage.getMessage("탈퇴가 완료되었습니다", "/am");
         }
      }
      return GetMessage.getMessage("비밀번호가 틀렸습니다", "/am/customerPwdChk?id=" + dto.getcId());
   }

   public void addrecentlyView(String mediCookie) { // 최근 본 병원추가
      recentlyViewDTO rvDTO = new recentlyViewDTO();
      String cId = mediCookie.split("/")[0];
      String mId = mediCookie.split("/")[1];
      rvDTO.setcId(cId);
      rvDTO.setmId(mId);
      cm.addRecentlyView(rvDTO); // db 값 저장
   }
   
   public void delRecentlyView(String cId) { // 최근 본 병원 데이터 삭제
      cm.delRecentlyView(cId);
      
   }

   public List<Map<String, String>> getRecentlyView(List<String> recentlyViewed, String cId) { // 최근 본 병원 리스트 가져오기
      List<Map<String, String>> getView = new ArrayList<Map<String, String>>();
      if (recentlyViewed.size() != 0) {
         getView = cm.getRecentlyView(cId);
         return getView;
      }
      return null;
   }
}