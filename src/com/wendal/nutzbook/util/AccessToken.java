package com.wendal.nutzbook.util;

import java.util.Random;

public class AccessToken { 
	
	private String token;
	private long timeStamp;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	private final static String key="0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
	
	public static String generate() {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		Random random=new Random();
		for(int i=0;i<=60;i++){
			int factar=random.nextInt(key.length());
			sb.append(key.charAt(factar));
		}
		return sb.toString();
	}
	

}
