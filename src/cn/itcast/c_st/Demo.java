package cn.itcast.c_st;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class Demo {
	@Test
	//execute => 执行语句
	//返回值: 表示语句执行之后是否有结果集返回
	//true => 有结果集	select 
	//false => 没有结果集    
	public void fun1() throws Exception{
		//1 注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//2 获得连接
		Connection conn = DriverManager.getConnection("jdbc:mysql:///day15", "root", "111111");
		//3 创建statement
		Statement st = conn.createStatement();
		//4 准备sql语句
		//String sql ="update dept set dname='JAVA' where deptno = 50 ";
		String sql ="select * from dept ";
		//5 使用st执行sql语句
		boolean result = st.execute(sql);
		//判断是否有结果集.
		if(result){
			//如果有遍历显示
			ResultSet rs = st.getResultSet();
			
			while(rs.next()){
				int deptno = rs.getInt("DEPTNO");
				String dname = rs.getString("DNAME");
				String loc = rs.getString("LOC");
				
				System.out.println(deptno+"=>"+dname+"=>"+loc);
			}
		}
		System.out.println(result);
		//6 关闭资源
		st.close();
		conn.close();
		
	}
}
