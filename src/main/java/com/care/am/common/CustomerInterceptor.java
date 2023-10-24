package com.care.am.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CustomerInterceptor extends HandlerInterceptorAdapter{
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if( session.getAttribute(LoginSession.LOGIN) == null) {
			
			String msg = "<script>alert('로그인 먼저 하세요');";
			msg += "location.href='/am/login';</script>";
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.print(msg);
		
			return false;
		}
		return true;
	}
	
}
