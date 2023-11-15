package com.care.am.service.admin;

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
				result = am.payRegister(rNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return result;
	}
	
    public static final String impKey = "1221163661378568";
    public static final String impSecret = "FsZsjC291mrfnLngPLepGgN3R5jxJukbmMmg9oG8dTuyvkjKQpiFEaiPED97F6r6nwNWttDYwBYUjs24";     

    public int orderCancle(Map<String, Object> map) throws Exception {
		if(!orderList.getImp_uid().equals("")) {
			String token = payService.getToken(); 
			Long price = orderList.getTotalPrice();
			Long refundPrice = price ;
			payService.payMentCancle(token, orderList.getImp_uid(), refundPrice+"", "환불");
		}
		
		return adminDAO.orderCancle((orderList.getOrderNum()));
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

		return token;
	}
	
  /*  public static final String IMPORT_TOKEN_URL = "https://api.iamport.kr/users/getToken"; 
    
    public static final String KEY = "1221163661378568";
    public static final String SECRET = "FsZsjC291mrfnLngPLepGgN3R5jxJukbmMmg9oG8dTuyvkjKQpiFEaiPED97F6r6nwNWttDYwBYUjs24";     
    
    public String getImportToken() { // 아임포트 인증(토큰)을 받아주는 함수 
        String result = ""; 
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(IMPORT_TOKEN_URL); 
        Map<String,String> m =new HashMap<String,String>(); 
        m.put("imp_key", KEY); 
        m.put("imp_secret", SECRET); 
        try { post.setEntity(new UrlEncodedFormEntity(convertParameter(m))); 
            HttpResponse res = client.execute(post); 
            ObjectMapper mapper = new ObjectMapper(); 
            String body = EntityUtils.toString(res.getEntity()); 
            JsonNode rootNode = mapper.readTree(body); 
            JsonNode resNode = rootNode.get("response"); 
            result = resNode.get("access_token").asText(); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        }         
        return result;
    }

	private List<? extends NameValuePair> convertParameter(Map<String, String> m) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
