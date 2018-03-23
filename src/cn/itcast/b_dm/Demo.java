package cn.itcast.b_dm;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class Demo {
	@Test
	public void fun1() throws Exception{
		// DriverManager 
		// 1.注册驱动
		
		//如下注册方法,不推荐
			//1.实现类的静态代码块中已经注册过一次驱动了.我们这么注册实际上重复了
			//2.Class.forName注册方法,是使用驱动的完整类名字符串注册,可维护性提高. 
				//在需要换驱动时,代码无需更改,只要将配置文件更新即可(完整类名配置到配置文件中)
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//推荐的注册驱动方法
		Class.forName("com.mysql.jdbc.Driver");
		
		
		
	}
	@Test
	public void fun2() throws Exception{
		// DriverManager 
		  // 2.获得连接
		// 参数1: 连接的地址(ip地址,端口号,连接的库)
		// 参数2: 登录用户名
		// 参数3: 登录密码
		
		//地址的语法: jdbc协议:子协议://主机:端口号/库名?参数键=参数值&参数键=参数值
		String url = "jdbc:mysql://localhost:3306/day16?characterEncoding=utf8";
		
		DriverManager.getConnection(url, "root","1234");
		
		
		
		
	}
}
