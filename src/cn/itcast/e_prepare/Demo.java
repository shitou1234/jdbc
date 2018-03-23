package cn.itcast.e_prepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import cn.itcast.utils.DBUtils;

public class Demo {
	@Test
	//演示sql注入问题
	public void fun1() throws Exception{
		String name = "xxx' OR 1=1 -- ";
		String password = "1234";
		//----------------------------------------------
		
		//1 获得连接
			Connection conn = DBUtils.getConnection();
		//2 创建Statement对象
			Statement st = conn.createStatement();
		//3 书写sql
			String sql = "SELECT * FROM user WHERE NAME = '"+name+"' AND PASSWORD='"+password+"';";
		//4 执行sql
				ResultSet rs
				 = st.executeQuery(sql);
				
				
		//5 验证是否查询到结果
				if(rs.next()){
					System.out.println("登录成功!");
				}else{
					System.out.println("登录失败!");
				}
		//6关闭资源
			DBUtils.close(rs, st, conn);
	}
	
	@Test
	//演示解决sql注入问题
	public void fun2() throws Exception{
//		String name = "xxx' OR 1=1 -- ";
		String name = "jerry";
		String password = "1234";
		//----------------------------------------------
		
		//1 获得连接
			Connection conn = DBUtils.getConnection();
		//2 书写sql
			String sql = "SELECT * FROM user WHERE NAME = ? AND PASSWORD=? ";
		//3 创建PrepareStatement对象
			PreparedStatement ps = conn.prepareStatement(sql);
		//4 将sql语句的参数设置给ps
			ps.setString(1, name);
			ps.setString(2, password);
		//5 执行sql
			ResultSet rs = ps.executeQuery();
				
		//5 验证是否查询到结果
				if(rs.next()){
					System.out.println("登录成功!");
				}else{
					System.out.println("登录失败!");
				}
		//6关闭资源
			DBUtils.close(rs, ps, conn);
	}
}	
