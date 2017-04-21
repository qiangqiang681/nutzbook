package com.wendal.nutzbook.util;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class SessionManager {
	
	HashMap<String, HttpSession> sessionMap=new HashMap<String, HttpSession>();
	
	private static SessionManager sessionManager;

	private SessionManager(){
		
	}
	
	public static SessionManager getInstance(){
		if(sessionManager==null)
			sessionManager=new SessionManager();
		return sessionManager;
	}
	
	public boolean isLoginOtherDevice(String user){
		return sessionMap.containsKey(user);
	}
	
	public void forceLogoutOtherDevice(String user){
		sessionMap.get(user).invalidate();
		sessionMap.remove(user);
	}
	
	public HashMap<String,HttpSession> getSessionMap(){
		return sessionMap;
	}

	public void attributeAdd(String name, HttpSession sessionT) {
		sessionMap.put(name, sessionT);
		
	}

}
