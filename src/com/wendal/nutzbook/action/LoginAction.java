package com.wendal.nutzbook.action;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wendal.nutzbook.bean.User;
import com.wendal.nutzbook.util.AccessToken;
import com.wendal.nutzbook.util.AccessTokenManager;
import com.wendal.nutzbook.util.SessionManager;

@IocBean
@At("/user")
@Ok("json")
@Fail("http:500")
//@AdaptBy(type= JsonAdaptor.class)
public class LoginAction extends BaseAction {

	@At
	public int count() {
		
		return dao.count(User.class);
	}
	
	@At("/login")
    public Object login(@Param("username")String name, @Param("password")String password, HttpSession session) {
        User user = dao.fetch(User.class, Cnd.where("name", "=", name).and("passwd", "=", password));
        
		HttpSession sessionT = Mvcs.getHttpSession(false);

        
        NutMap map = new NutMap();
        if (user == null) {
        	map.addv("status", 404);
        	map.addv("msg", "用户不存在，请重新登录");
        } else {
        	map.addv("status", 0);
        	NutMap dataMap=new NutMap();
        	AccessToken accessToken=AccessTokenManager.getInstance().generateAccessToken();
        	dataMap.addv("accesstoken",accessToken.getToken());
        	map.addv("data", dataMap);
        }
        
        if(SessionManager.getInstance().isLoginOtherDevice(name)){
        	SessionManager.getInstance().forceLogoutOtherDevice(name);
        }
        
        SessionManager.getInstance().attributeAdd(name, sessionT);
        
        return map;
    }
	
	@At("/validataAccessToken")
	public Object validataAccessToken(@Param("accesstoken") String token){
		boolean isTimeOut=AccessTokenManager.getInstance().validataTokenIsOut(token);
        NutMap map = new NutMap();

		if(isTimeOut){
			 map.addv("status", 400);
			 map.addv("msg", "令牌已过期，重新登录");
			return map;
		}
		
		AccessTokenManager.getInstance().updateAccessToken(token);
		NutMap dataMap=new NutMap();
		
    	map.addv("data", dataMap);
		
		return map;
	}
	
    @At
    @Ok(">>:/user/count")
    public void logout(HttpSession session) {
        session.invalidate();
    }

}
