package com.wendal.nutzbook.util;

import java.util.HashMap;

public class AccessTokenManager {
	
	static AccessTokenManager accessTokenManager;
	
	HashMap<String, AccessToken> tokens=new HashMap<String, AccessToken>();
	
	private static final long accessTokenTimeOut=1*30*1000l;

	public static AccessTokenManager getInstance(){
		if(accessTokenManager==null){
			accessTokenManager=new AccessTokenManager();
		}
		return accessTokenManager;
	}
	
	public AccessToken generateAccessToken(){
		AccessToken accessToken=new AccessToken();
		accessToken.setTimeStamp(System.currentTimeMillis());
		accessToken.setToken(AccessToken.generate());
		tokens.put(accessToken.getToken(), accessToken);
		return accessToken;
	}
	

	public boolean validataTokenIsOut(String token) {
		// TODO Auto-generated method stub
		AccessToken accessToken=tokens.get(token);
		
		if(accessToken==null)
			return true;
		
		long nowTime=System.currentTimeMillis();
		long timeout=nowTime-accessToken.getTimeStamp();
		
		if(timeout>accessTokenTimeOut)
			return true;
		return false;
	}

	public void updateAccessToken(String token) {
		// TODO Auto-generated method stub
		AccessToken accessToken=tokens.get(token);
		accessToken.setTimeStamp(System.currentTimeMillis());
		tokens.put(token, accessToken);
		
	}
}
