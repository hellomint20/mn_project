package com.care.am.service.loginLogic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.am.dto.customerDTO;
import com.care.am.mapper.customerMapper;
import com.care.am.service.customer.customerService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class loginLogicService {

	Logger logger = LoggerFactory.getLogger(loginLogicService.class);

	@Autowired customerMapper cm;
	@Autowired customerService cs;

	public String getKakaoAccessToken(String code) {
		
		try {
            String url = "https://kauth.kakao.com/oauth/token";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // ��û�� POST�� ����
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            // POST �Ķ���� ����
            String postParams = "grant_type=authorization_code" +
                    "&client_id=552b94427c4a76a3adae3c4f8183915b" +
                    "&redirect_uri=http://localhost:8090/am/kakaoCallback" +
                    "&code=" + code;

            // ��û �Ķ���͸� ����
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println(responseCode);
            
            // ������ �޾Ƽ� �б�
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // ��� ��ȯ
            return response.toString();
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


	public customerDTO createKakaoUser(String token) throws Exception {
	    customerDTO mVO = new customerDTO(); // �α��� �� ȸ�� ���� ���� VO

	    try {
	    	String reqURL = "https://kapi.kakao.com/v2/user/me";
	        URL obj = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

	        conn.setRequestMethod("POST");
	        conn.setDoOutput(true);
	        conn.setRequestProperty("Authorization", "Bearer " + token);

	        int responseCode = conn.getResponseCode();
	        System.out.println("create responseCode : " + responseCode);

	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuilder result = new StringBuilder();
	        String line;

	        while ((line = br.readLine()) != null) {
	            result.append(line);
	        }
	        br.close();

	        JsonParser parser = new JsonParser();
	        JsonObject jsonObject = parser.parse(result.toString()).getAsJsonObject();
	        JsonObject properties = jsonObject.getAsJsonObject("properties");
	        JsonObject kakao_account = jsonObject.getAsJsonObject("kakao_account");
	        String nickname = properties.get("nickname").getAsString();
	        String email = kakao_account.get("email").getAsString();

	        // �̹� ��ϵ� ȸ������ Ȯ��
	        customerDTO kakaoChk = cm.kakaoCheck(email);
	        if (kakaoChk == null) {
	            String member_code = cs.makeRandomPw();
	            mVO.setcId("k");
	            mVO.setcPw(member_code);
	            mVO.setcName(nickname);
	            mVO.setcTel("010-1111-1111");
	            mVO.setcEmail(email);

	            // ȸ�� ���
	            int registrationResult = cm.register(mVO);
	            if (registrationResult == 1) {
	                return cm.kakaoCheck(mVO.getcEmail());
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
