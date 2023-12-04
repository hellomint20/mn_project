package com.care.am.service.loginLogic;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.care.am.dto.customerDTO;
import com.care.am.mapper.customerMapper;
import com.care.am.service.customer.customerService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class loginLogicService{

   Logger logger = LoggerFactory.getLogger(loginLogicService.class);

   @Autowired customerMapper cm;
   @Autowired customerService cs;
   
   BCryptPasswordEncoder encoder;
   
   public loginLogicService() {
      encoder = new BCryptPasswordEncoder();
   }

   public String getKakaoAccessToken(String code) {
      
      try {
            String url = "https://kauth.kakao.com/oauth/token";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // 요청을 POST로 설정
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            // POST 파라미터 구성
            String postParams = "grant_type=authorization_code" +
                    "&client_id=552b94427c4a76a3adae3c4f8183915b" +
                    "&redirect_uri=http://localhost:8090/am/kakaoCallback" +
                  "&code=" + code;

            // 요청 파라미터를 전송
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println(responseCode);
            
            // 응답을 받아서 읽기
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 결과 반환
            return response.toString();
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


   public customerDTO createKakaoUser(String token) throws Exception {
       customerDTO mVO = new customerDTO(); // 사용자 정보를 담을 mVO

       try {
           String reqURL = "https://kapi.kakao.com/v2/user/me";
           URL obj = new URL(reqURL);
           HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

           // 요청을 GET으로 설정 (카카오 API 요청 시 GET 방식 사용)
           conn.setRequestMethod("GET");
           conn.setRequestProperty("Authorization", "Bearer " + token);
           int responseCode = conn.getResponseCode();
           System.out.println(responseCode);
           BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
           StringBuilder result = new StringBuilder();
           String line;

           while ((line = br.readLine()) != null) {
               result.append(line);
           }
           br.close();


           JsonParser parser = new JsonParser();
           JsonObject jsonObject = parser.parse(result.toString()).getAsJsonObject();
           JsonObject kakaoAccount = jsonObject.getAsJsonObject("kakao_account");
           String nickname = kakaoAccount.getAsJsonObject("profile").get("nickname").getAsString();
           String email = kakaoAccount.get("email").getAsString();
	   
           String[] k_mail = email.split("@");
           String kId = k_mail[0]+"_kakao";
           String pwd = cs.makeRandomPw();

           // 이미 등록된 회원인지 확인
           customerDTO kakaoChk = cm.getCustomer(kId);
           if (kakaoChk == null) {
               mVO.setcId(kId);
               mVO.setcPw(encoder.encode(pwd));
               mVO.setcName(nickname);
               mVO.setcTel("null");
               mVO.setcEmail(email);

               // 회원 등록
               int registrationResult = cm.register(mVO);
               if (registrationResult == 1) {
                   return mVO;
               }
           } else {
               return kakaoChk;
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       return null;
   }

}