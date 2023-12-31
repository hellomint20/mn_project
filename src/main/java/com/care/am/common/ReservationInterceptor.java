package com.care.am.common;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class ReservationInterceptor extends HandlerInterceptorAdapter implements LoginSession {
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		
		//로그인 없이 페이지 접근

		if (session.getAttribute(cLOGIN) == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('로그인 후 이용 가능합니다.');" + "location.href='/am/customerLogin';</script>");

			return false;
		}
		
		return true;
	}
	
}
