package cn.itcast.d_resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import org.junit.Test;

public class Demo2 {
	@Test
	//介绍resultSet中的了解方法
	//结果集的滚动 => 让结果集中的光标随意移动到任意一行
	public void fun1() throws Exception{
		//1 注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//2 建立连接
		String url= "jdbc:mysql://localhost:3306/day15";
		Connection conn = DriverManager.getConnection(url, "root","111111");
		//3 创建Statement对象
		Statement  st = conn.createStatement();
		//4 准备sql
		String sql = "select * from emp ";
		//5执行sql
		ResultSet rs = st.executeQuery(sql);
		//6结果集滚动
			
		// boolean isAfterLast()   判断光标是否在最后一行之后
		// boolean isBeforeFirst()   获取光标是否位于此 ResultSet 对象的第一行之前。
		// boolean isFirst() 	判断光标是否在第一行
        // boolean isLast()  	判断是否在最后一行

		// absolute(int row)   让光标移动到指定行
		// void afterLast() 	让光标移动到结果集最后一行之后
		// void beforeFirst()  让光标移动到第一行之前(初始位置)
		// boolean first()  	将光标移动到此 ResultSet 对象的第一行
		// boolean last()  	    将光标移动到此 ResultSet 对象的最后一行
		// boolean next()(经常使用 ) 
		// boolean previous() 	将光标向上移动一行
		// boolean relative(int rows) 按相对行数（或正或负）移动光标 
        
		//--------------------------------------------------------------------
		//倒着遍历一遍
		rs.afterLast();
		
		while(rs.previous()){
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
