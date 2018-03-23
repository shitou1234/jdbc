package cn.itcast.d_resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class Demo3 {
	@Test
	//使用resultset反向修改数据库(了解)
	//前提:
		//1.ResultSet.CONCUR_UPDATABLE
		//2.结果集中必须包含ID列
	public void fun1() throws Exception {
		//1 注册驱动
		Class.forName("com.mysql.jdbc.Driver");
		//2 获得连接
		Connection conn = DriverManager.getConnection("jdbc:mysql:///day16","root","1234");
		//3 创建Statement
		/*
		 * resultSetType - 结果集类型，它是
			 * 	 ResultSet.TYPE_FORWARD_ONLY 	只能向前滚动的结果集
			 * 	 ResultSet.TYPE_SCROLL_INSENSITIVE 	支持滚动 不敏感  	数据库中的数据变化不会影响结果集中的数据
			 *   ResultSet.TYPE_SCROLL_SENSITIVE 	支持滚吨 敏感  数据库中的数据变化会影响结果集中的数据
		   resultSetConcurrency - 并发类型；它是 
			   	ResultSet.CONCUR_READ_ONLY 	 只读
			   	ResultSet.CONCUR_UPDATABLE	可以修改的结果集
		 */
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 	ResultSet.CONCUR_UPDATABLE);
		//4 准备sql语句
		String sql = "select * from dept ";
		//5 执行sql
		ResultSet rs = st.executeQuery(sql);
		//6 根据resultset 修改数据库中的记录
		//将光标移动到第5行
		rs.absolute(5);
		//调用修改结果集方法修改数据
		rs.updateString("dname", "itcast");
		//调用提交修改方法提交修改
		rs.updateRow();
		//7 释放资源
		rs.close();
		st.close();
		conn.close();
	}
}
