var ioc = {
	// 读取配置文件
	conf : {
		type : "org.nutz.ioc.impl.PropertiesProxy",
		fields : {
			paths : [ "custom/" ]
		}
	},
	// 数据源
	dataSource : {
		factory : "$conf#make",
		args : [ "com.alibaba.druid.pool.DruidDataSource", "db." ],
		type : "com.alibaba.druid.pool.DruidDataSource",
		events : {
			create : "init",
			depose : 'close'
		}
	},
	// Dao
	dao : {
		type : 'org.nutz.dao.impl.NutDao',
		args : [ {
			refer : "dataSource"
		} ]
	}
};