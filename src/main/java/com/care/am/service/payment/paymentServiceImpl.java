package com.care.am.service.payment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.care.am.dto.cancleBuyDTO;
import com.care.am.mapper.paymentMapper;
import com.care.am.mapper.reservationMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
public class paymentServiceImpl implements paymentService{
	@Autowired paymentMapper pm;
	@Autowired reservationMapper rm;
	
	public int payResRegister(Map<String, Object> map) { // 병원 예약
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
		
		if(Integer.parseInt(String.valueOf(rm.peopleCount(countMap).get("count(*)"))) >= 3) { //예약 인원 다 찼을 경우
			result = 99;
		} else {
			map.put("rDate", day);
			map.put("rTime", time);
			try {
				pm.resRegister(map);
				result = pm.payRegister(map.get("rNum").toString(), map.get("impUid").toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return result;
	}
	
    public static final String impKey = "1221163661378568";
    public static final String impSecret = "FsZsjC291mrfnLngPLepGgN3R5jxJukbmMmg9oG8dTuyvkjKQpiFEaiPED97F6r6nwNWttDYwBYUjs24";     

    public String payCancle(String rNum, String pay) {
    	String cancle = "";
    	String impUid = pm.getImpUid(rNum);
    	try {
    		cancle = cancleBuy(impUid, pay); //결제 취소
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return cancle;
    }
    
    //결제 취소
    public String cancleBuy(String impUid, String pay) {
    	String result = "";
    	try {
    		String token = null;
    		token = getToken();
    		
    		HttpHeaders headers = new HttpHeaders();
    		headers.add("Authorization", token);
    		
    		JSONObject body = new JSONObject();
    		body.put("imp_uid", impUid);
    		body.put("amount", pay);
    		
    		HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(body, headers);
    		
    		RestTemplate restTemplate = new RestTemplate();
    		
    		cancleBuyDTO cancle = restTemplate.postForObject("https://api.iamport.kr/payments/cancel", entity, cancleBuyDTO.class);
    		result = cancle.getCode(); //0일때 성공
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return result;
    }
    
	public String getToken() throws Exception { //token 가져오기

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
		return token;
	}
}
