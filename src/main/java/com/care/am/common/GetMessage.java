package com.care.am.common;

public class GetMessage {
	
	   public static String getMessage(String msg, String url) {
		      String message = "<script>alert('"+msg+"');";
		      message += "location.href='"+url+"';</script>";
		      return message;
		   }

}
