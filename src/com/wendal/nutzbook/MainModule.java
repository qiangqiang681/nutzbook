package com.wendal.nutzbook;

import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SessionBy;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.impl.session.NopSessionProvider;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@SetupBy(value=MainSetup.class)
@IocBy(type=ComboIocProvider.class, args={"*js", "ioc/",
    "*anno", "com.wendal.nutzbook",
    "*tx", // 事务拦截 aop
    "*async"}) // 异步执行aop

@Modules(scanPackage=true)
@SessionBy(NopSessionProvider.class)//使用容器原生的Session实现 == 等于什么都没做.
public class MainModule {

}
