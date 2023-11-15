package com.care.am.service.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.care.am.dto.cancleBuyDTO;
import com.care.am.mapper.adminMapper;
import com.care.am.mapper.reservationMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
public class adminServiceImpl implements adminService{
	@Autowired adminMapper am;
	@Autowired reservationMapper rm;
	
	public int payResRegister(Map<String, Object> map) { // 병원 예약

		String year = map.get("rDate").toString().replace("년 ", "-");
		String month = year.replace("월 ", "-");
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
				am.resRegister(map);
				System.out.println(map.get("rNum"));
				int rNum = Integer.parseInt(String.valueOf(map.get("rNum")));
				result = am.payRegister(map.get("rNum").toString(), map.get("impUid").toString());
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
    	String impUid = am.getImpUid(rNum);
    	cancleBuy(impUid);
    	
    	//예약 테이블 삭제 기능
    }
    
    //결제 취소
    public void cancleBuy(String impUid) {
    	try {
    		String token = null;
    		token = getToken();
    		
    		HttpHeaders headers = new HttpHeaders();
    		headers.clear();
    		headers.add("Authorization", token);
    		
    		JSONObject body = new JSONObject();
    		body.clear();
    		body.put("imp_uid", impUid);
    		
    		HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(body, headers);
    		
    		RestTemplate restTemplate = new RestTemplate();
    		
    		cancleBuyDTO cancle = restTemplate.postForObject("https://api.iamport.kr/payments/cancel", entity, cancleBuyDTO.class);
    		System.out.println(cancle+ " full cancle");
    	} catch (Exception e) {
			e.printStackTrace();
		}
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
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

		bw.write(json.toString());
		bw.flush();
		bw.close();
		System.out.println("왔나");
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));			
		
	}
}
