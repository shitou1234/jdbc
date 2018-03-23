package cn.itcast.g_blob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import cn.itcast.utils.DBUtils;

public class Demo {
	@Test
	//演示插入blob类型数据
	// 前提: 必须使用PrepareStatement
	public void fun1() throws Exception{
		//1 获得连接
			Connection conn = DBUtils.getConnection();
		//2 书写sql
			String sql = "insert into myblob value(null,?) ";
		//3 创建PrepareStatement对象
			PreparedStatement ps = conn.prepareStatement(sql);
		//4 将sql语句的参数设置给ps
			
			File file = new File("src/sg.PNG");
			
			FileInputStream fis = new FileInputStream(file);
			
			ps.setBinaryStream(1, fis, (int)file.length());
		//5 执行sql
			int result = ps.executeUpdate();
			System.out.println(result);
		//6关闭资源
			DBUtils.close(null, ps, conn);
	}
}	
