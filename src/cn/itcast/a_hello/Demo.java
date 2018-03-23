package cn.itcast.a_hello;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class Demo {
	@Test
	//Hello world => 连接并操作数据库
	public void fun1() throws Exception{
		//1 注册驱动
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//2 建立连接
		String url= "jdbc:mysql://localhost:3306/day15";
		Connection conn = DriverManager.getConnection(url, "root","111111");
		//3 执行sql,并返回结果
		Statement  st = conn.createStatement();
		
		String sql = "insert into dept values('50','GAMING','BeiJing')";
		
		int result = st.executeUpdate(sql);//1 
		
		System.out.println(result);
		//4 关闭资源
		st.close();
		conn.close();
	}
	
	@Test
	//Hello world => 连接并操作数据库
	public void fun2() throws Exception{
		//1 注册驱动
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		//2 建立连接
		String url= "jdbc:mysql://localhost:3306/day15";
		Connection conn = DriverManager.getConnection(url, "root","111111");
		//3 执行sql,并返回结果
		Statement  st = conn.createStatement();
		//定义sql
		String sql = "select * from dept ";
		//发送语句查询
		ResultSet  rs = st.executeQuery(sql);
		//遍历结果
		while(rs.next()){
			int deptno = rs.getInt("DEPTNO");
			String dname = rs.getString("DNAME");
			String loc = rs.getString("LOC");
			
			
			System.out.println(deptno+"=>"+dname+"=>"+loc);
		}
		
		
		
		//4 关闭资源
		rs.close();
		st.close();
		conn.close();
	}
}	
