package com.care.am.service.admin;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import com.care.am.mapper.adminMapper;
import com.care.am.mapper.reservationMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
public class adminServiceImpl implements adminService{
	@Autowired adminMapper am;
	@Autowired reservationMapper rm;
	
	public int payResRegister(Map<String, Object> map) { // 蹂묒썝 �삁�빟

		String year = map.get("rDate").toString().replace("년", "-");
		String month = year.replace("월", "-");
		String day = month.replace("일", "");
		String time = map.get("rTime").toString().replace(":", "-");

		Map<String, Object> countMap = new HashMap<String, Object>();
		countMap.put("mId", map.get("mId"));
		countMap.put("rDate", day);
		countMap.put("rTime", time);

		int result = 0;
		Integer.parseInt(String.valueOf(rm.peopleCount(countMap).get("count(*)")));
		
		if(Integer.parseInt(String.valueOf(rm.peopleCount(countMap).get("count(*)"))) >= 3) { //�삁�빟 �씤�썝 �떎 李쇱쓣 寃쎌슦
			result = 99;
		} else {
			map.put("rDate", day);
			map.put("rTime", time);
			try {
				am.resRegister(map);
				System.out.println(map.get("rNum"));
				int rNum = Integer.parseInt(String.valueOf(map.get("rNum")));
				result = am.payRegister(rNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return result;
	}
	
    public static final String impKey = "1221163661378568";
    public static final String impSecret = "FsZsjC291mrfnLngPLepGgN3R5jxJukbmMmg9oG8dTuyvkjKQpiFEaiPED97F6r6nwNWttDYwBYUjs24";     

    //예약 취소
    public void orderCancle(String rNum) {
        int result = 0;
        //impuid 값 가져오는 거 필요
        String impUid = am.getImpUid(rNum);
       if(!impUid.equals("")) { //결제 취소
          String token = null;
          try {
             token = getToken();
          } catch (Exception e) {
             e.printStackTrace();
          } 
          Long price = (long)100;
          Long refundPrice = price ;
          try {
             payMentCancle(token, impUid, refundPrice+"", "환불");
          } catch (ExpressionException e) {
             e.printStackTrace();
          } catch (IOException e) {
             e.printStackTrace();
          }
       }
       
       //예약 리스트 취소
       //return result;
 }
     
    public String getToken() throws Exception {

       HttpsURLConnection conn = null;
       URL url = new URL("https://api.iamport.kr/users/getToken");

       conn = (HttpsURLConnection) url.openConnection();

       conn.setRequestMethod("POST");
       conn.setRequestProperty("Content-type", "application/json");
       conn.setRequestProperty("Accept", "application/json");
       conn.setDoOutput(true);
       JsonObject json = new JsonObject();

       json.addProperty("imp_key", impKey);
       json.addProperty("imp_secret", impSecret);
       
       BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
       
       bw.write(json.toString());
       bw.flush();
       bw.close();

       BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

       Gson gson = new Gson();

       String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();


       String token = gson.fromJson(response, Map.class).get("access_token").toString();

       br.close();
       conn.disconnect();
       System.out.println("왔나");
       return token;
    }
    
    public void payMentCancle(String access_token, String imp_uid, String amount,String reason) throws IOException, ExpressionException {
       System.out.println("imp_uid = " + imp_uid);
       HttpsURLConnection conn = null;
       URL url = new URL("https://api.iamport.kr/payments/cancel");

       conn = (HttpsURLConnection) url.openConnection();

       conn.setRequestMethod("POST");

       conn.setRequestProperty("Content-type", "application/json");
       conn.setRequestProperty("Accept", "application/json");
       conn.setRequestProperty("Authorization", access_token);

       conn.setDoOutput(true);
       
       JsonObject json = new JsonObject();

       json.addProperty("reason", reason);
       json.addProperty("imp_uid", imp_uid);
       json.addProperty("amount", amount);
       json.addProperty("checksum", amount);
       
       BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

       bw.write(json.toString());
       bw.flush();
       bw.close();
       System.out.println("왔나");
       BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));         
       
    }

	@Override
	public int orderCancle(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

}
