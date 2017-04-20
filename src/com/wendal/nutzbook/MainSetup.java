package com.wendal.nutzbook;

import java.util.Date;

import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import com.wendal.nutzbook.bean.User;

public class MainSetup implements Setup{

	

	public void init(NutConfig nc) {
		// TODO Auto-generated method stub
		Ioc ioc=nc.getIoc();
		Dao dao=ioc.get(Dao.class);
		Daos.createTablesInPackage(dao, "com.wendal.nutzbook.bean", false);
		
		  // 初始化默认根用户
        if (dao.count(User.class) == 0) {
            User user = new User();
            user.setName("admin");
            user.setPassword("123456");
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            dao.insert(user);
        }
	}
	public void destroy(NutConfig nc) {
		// TODO Auto-generated method stub
		
		
	}
}
