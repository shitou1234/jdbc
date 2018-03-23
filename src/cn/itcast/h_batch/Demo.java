package cn.itcast.h_batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

import org.junit.Test;

import cn.itcast.utils.DBUtils;

public class Demo {
	@Test
	//statement对象演示批量执行sql
	public void fun1() throws Exception{
		
		//1 获得连接
			Connection conn = DBUtils.getConnection();
		//2 创建Statement对象
			Statement st = conn.createStatement();
		//3 书写sql
			String sql1 = "create table t_batch (" +
					"name varchar(20)," +
					"password varchar(20)" +
					")";
			String sql2 = "insert into t_batch values('tom','1234')";
			String sql3 = "insert into t_batch values('jerry','1234')";
			String sql4 = "insert into t_batch values('jack','1234')";
			String sql5 = "insert into t_batch values('rose','1234')";
			String sql6 = "delete from  t_batch where name = 'jack' ";
			String sql7 = "update  t_batch  set password='521' where name='rose'";
			
			st.addBatch(sql1);
			st.addBatch(sql2);
			st.addBatch(sql3);
			st.addBatch(sql4);
			st.addBatch(sql5);
			st.addBatch(sql6);
			st.addBatch(sql7);
			
			
			
		//4 执行sql
			int[] results = st.executeBatch();//0,1,1,1,1,1,1
			System.out.println(Arrays.toString(results));
		//5关闭资源
			DBUtils.close(null, st, conn);
	}
	
	@Test
	//prepareStatement演示批量执行sql
	public void fun2() throws Exception{
		
		//1 获得连接
			Connection conn = DBUtils.getConnection();
		//2 书写sql
			String sql = "insert into t_batch values(?,?) ";
		//3 创建PrepareStatement对象
			PreparedStatement ps = conn.prepareStatement(sql);
		//4 批量添加参数
			for(int i = 0 ; i < 1000 ;i++){
				ps.setString(1, "用户"+i);
				ps.setString(2, "1234");
				ps.addBatch();
			}
		//5 执行
			ps.executeBatch();
		//6关闭资源
			DBUtils.close(null, ps, conn);
	}
}	
