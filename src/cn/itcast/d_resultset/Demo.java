package cn.itcast.d_resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import org.junit.Test;

public class Demo {
	@Test
	//遍历resultSet
	public void fun1() throws Exception{
		//1 注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//2 建立连接
		String url= "jdbc:mysql://localhost:3306/day16";
		Connection conn = DriverManager.getConnection(url, "root","1234");
		//3 创建Statement对象
		Statement  st = conn.createStatement();
		//4 准备sql
		String sql = "select * from emp ";
		//5执行sql
		ResultSet rs = st.executeQuery(sql);
		//6遍历结果
		while(rs.next()){
			int empno = rs.getInt(1);
			
			String ename = rs.getString("ename");
			
			Date hireDate = rs.getDate(5);
			
			double sal = rs.getDouble("sal");
			
			System.out.println(empno+"==>"+ename+"==>"+hireDate+"==>"+sal);
			
		}
		//7 关闭资源
		rs.close();
		st.close();
		conn.close();
	}
	
		
		
}	
