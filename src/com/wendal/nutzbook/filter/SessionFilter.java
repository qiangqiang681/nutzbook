package com.wendal.nutzbook.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;

public class SessionFilter implements ActionFilter{

	public View match(ActionContext arg0) {
		// TODO Auto-generated method stub
		HttpSession httpSession= Mvcs.getHttpSession(false);
		if(httpSession==null){
			return new View() {
				
				public void render(HttpServletRequest arg0, HttpServletResponse res,
						Object arg2) throws Throwable {
					// TODO Auto-generated method stub
					res.getWriter().write("{\n" +
			                "\"status\":\"9\",\n" +
			                "\"msg\":\"会话已过期\"\n" +
			                "}");
				}
			};
		}
		return null;
	}
	
	

}
