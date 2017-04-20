package com.wendal.nutzbook.action;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;

public class BaseAction {

	@Inject
   protected Dao dao;
}
