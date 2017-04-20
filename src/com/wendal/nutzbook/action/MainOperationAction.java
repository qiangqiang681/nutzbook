package com.wendal.nutzbook.action;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wendal.nutzbook.filter.SessionFilter;

@IocBean
@At("/main")
@Ok("json")
@Fail("http:500")
//@AdaptBy(type= JsonAdaptor.class)
@Filters(@By(type = SessionFilter.class))
public class MainOperationAction extends BaseAction{

	@At("/mainopertion")
	public Object mainOpertion(@Param("mainopertion")String opertion){
		NutMap map=new NutMap();
		map.addv("status", "0");
		
		NutMap dataMap=new NutMap();
		
    	map.addv("data", dataMap);
		return map;
	}
}
